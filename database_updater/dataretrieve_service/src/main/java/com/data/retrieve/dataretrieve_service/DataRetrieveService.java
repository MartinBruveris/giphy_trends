package com.data.retrieve.dataretrieve_service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DataRetrieveService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //one minute in miliseconds
    final private int oneMinute = 60000; //miliseconds
    final private int initialTimerDelay = 20000; //miliseconds
    private int refreshRate;
    private String giphyAPIKey;
    private static final Logger log = LoggerFactory.getLogger(DataRetrieveService.class);
    
    public static void main(String[] args) {
        SpringApplication.run(DataRetrieveService.class, args);
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
            try {
                createTimer();
            }
            catch (Exception e) {
                log.info("Error, can't create timer");
            }
		};
	}

    @Bean
    ApplicationRunner applicationRunner(Environment environment) {
        return args -> {
            int refreshRateProperty = Integer.parseInt(environment.getProperty("data-refresh-rate"));
            String apiKeyProperty = environment.getProperty("giphy_api_key");
            log.info(">> Data refresh rate is set to " + refreshRateProperty + " minutes <<");
            log.info(">> Giphy API key is set to " + apiKeyProperty);
            refreshRate = refreshRateProperty;
            giphyAPIKey = apiKeyProperty;
        };
    }

    private Timer createTimer() {
        Timer timer = new Timer();
        log.info(">> REFRESH RATE EVERY "+refreshRate+" MINUTES");
        try {
            timer.schedule( new TimerTask() {
                public void run() {
                    log.info("PULLING NEW DATA");
                    getDataFromAPI();
                    log.info("LATEST RECORD TIMESTAMP: "+getLatestDataInDatabase().toString());
                }
            }, initialTimerDelay, (refreshRate * oneMinute));
        } catch (Exception e) {
            log.info("Can't create timer");
        }
        return timer;
    }

    private DateTime getLatestDataInDatabase() {
        final String sql = "SELECT latest_date FROM date_tracker WHERE ID = 1";
        String latestDateInDatabase = null;
        try {
            latestDateInDatabase = (String) jdbcTemplate.queryForObject(sql, String.class);
        } catch (Exception e) {
            log.info("Can't retrieve latest date in the database", e.getMessage());
        }
        return dateFormater(latestDateInDatabase);
    }

    private void getDataFromAPI() {
        final String url = "https://api.giphy.com/v1/gifs/trending?api_key="+giphyAPIKey+"&limit=10&rating=g";
        log.info(url);
        final String sql = "INSERT INTO giphy_main_table (giphy_id, giphy_type, embed_url, trending_datetime, title)"+
            " VALUES (?, ?, ?, ?, ?)";
        try {
            DateTime latestDataTime = getLatestDataInDatabase();
            Boolean newRecordsInserted = false;
            RestTemplate restTemplate = restTemplate();
            GiphyDataWrapper response = restTemplate.getForObject(url, GiphyDataWrapper.class);
            List<GiphyDTO> giphys = response.getData();

            for(GiphyDTO giphy : giphys){
                DateTime incommingDate = dateFormater(giphy.getTrending_datetime());
                if(latestDataTime.isBefore(incommingDate)) {
                    try {
                        int result = jdbcTemplate.update(
                        sql,
                        giphy.getId(),
                        giphy.getType(),
                        giphy.getEmbed_url(),
                        giphy.getTrending_datetime(),
                        giphy.getTitle());

                        if (result > 0) {
                            log.info(" !! NEW RECORD !! "+giphy.toString());
                            newRecordsInserted = true;
                        }
                    } catch(Exception e) {
                        log.info("Error connecting to the dataSource");
                    }
                }
            }

            if (!newRecordsInserted) log.info(" !!! NO NEW RECORDS INSERTED !!! ");

        } catch(Exception e) {
            log.error("Error reading data from API", e);
        }
    }

    private DateTime dateFormater(String dateToParse){
        DateTimeFormatter datetimeformat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = null;
        try {
            dateTime = datetimeformat.parseDateTime(dateToParse);
        } catch (Exception e) {
            log.info("Date parsing error", e.getMessage());
        }
        return dateTime;
    }
}