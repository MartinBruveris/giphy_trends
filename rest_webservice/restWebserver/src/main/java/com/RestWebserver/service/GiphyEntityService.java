package com.RestWebserver.service;

import java.util.List;

import javax.transaction.Transactional;

import com.RestWebserver.datamodel.GiphyEntity;
import com.RestWebserver.repository.GiphyEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GiphyEntityService {
    @Autowired
    private GiphyEntityRepository giphyEntityRepository;

    public List<GiphyEntity> getGiphys() {
        return giphyEntityRepository.findAll();
    }

    public void deleteGiphy(Integer id) {
        giphyEntityRepository.deleteById(id);
    }
}
