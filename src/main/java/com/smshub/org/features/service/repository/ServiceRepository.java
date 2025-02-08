package com.smshub.org.features.service.repository;

import com.smshub.org.features.service.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Integer> {
}
