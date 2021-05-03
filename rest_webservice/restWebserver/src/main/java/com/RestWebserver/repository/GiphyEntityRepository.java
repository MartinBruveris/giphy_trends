package com.RestWebserver.repository;

import com.RestWebserver.datamodel.GiphyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiphyEntityRepository extends JpaRepository<GiphyEntity, Integer> {}
