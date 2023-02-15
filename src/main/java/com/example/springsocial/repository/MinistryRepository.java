package com.example.springsocial.repository;

import com.example.springsocial.entity.DbMinistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinistryRepository extends JpaRepository<DbMinistry, Long> {
}
