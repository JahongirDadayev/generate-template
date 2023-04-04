package com.example.springsocial.repository;

import com.example.springsocial.entity.MinistryData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MinistryDataRepository extends JpaRepository<MinistryData, Long> {
    List<MinistryData> findByMinistry_Id(Long dbMinistry_id);
}
