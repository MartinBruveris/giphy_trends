# giphy_trends
App using giphy API to show trending giphys. Docker, Vue, Java, Spring test app.

# To run the project:

1. Install docker-compose if you don't have it already installed ---> https://docs.docker.com/compose/install/
2. Head over to https://support.giphy.com/hc/en-us/articles/360020283431-Request-A-GIPHY-API-Key and get an API key.
3. Clone the repository and cd into the repo and navigate to `database_updater\dataretrieve_service\src\main\resources\application.properties`
4. Update the properties with the API key you got from GIPHY.
5. CD back to the root of the repository.
6. Run docker-compose build(this will take some time).
7. Run docker-compose up(This will take some time).
8. In your browser head over to http://127.0.0.1:8080 to see the data(Some time will be required before data appears on the frontend(based on the data availability from the GIPHY API))



TODO:

There is no test coverage whatsover for this project, this could be a task for near future.
Reload data on the frontend once new records are added to the database.
