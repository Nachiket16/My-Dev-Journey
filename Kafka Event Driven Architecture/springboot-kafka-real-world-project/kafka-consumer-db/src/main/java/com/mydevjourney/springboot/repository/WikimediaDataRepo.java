package com.mydevjourney.springboot.repository;

import com.mydevjourney.springboot.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepo extends JpaRepository<WikimediaData,Long> {

}
