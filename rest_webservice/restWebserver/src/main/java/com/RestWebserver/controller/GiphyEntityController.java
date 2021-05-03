package com.RestWebserver.controller;

import java.util.List;

import com.RestWebserver.datamodel.GiphyEntity;
import com.RestWebserver.service.GiphyEntityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/giphys")
public class GiphyEntityController {
    private static final Logger log = LoggerFactory.getLogger(GiphyEntityController.class);
    @Autowired
    GiphyEntityService giphyEntityService;

    @GetMapping("")
    @CrossOrigin(origins = "http://127.0.0.1:8080")
    public List<GiphyEntity> list() {
        log.info("REST call to retrieve all giphys was made");
        return giphyEntityService.getGiphys();
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:8080")
    public void delete(@PathVariable Integer id) {
        log.info("REST call to delete record with id: "+id+" from the datbase");
        giphyEntityService.deleteGiphy(id);
    }
}
