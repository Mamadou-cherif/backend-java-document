package com.smshub.org.features.bureau.repository;


import com.smshub.org.features.bureau.model.Bureau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BureauRepository extends JpaRepository<Bureau, Integer> {
}
