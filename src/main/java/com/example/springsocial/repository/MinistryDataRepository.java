package com.example.springsocial.repository;

import com.example.springsocial.entity.DbMinistryData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MinistryDataRepository extends JpaRepository<DbMinistryData, Long> {
    List<DbMinistryData> findByDbMinistry_Id(Long dbMinistry_id);
}
