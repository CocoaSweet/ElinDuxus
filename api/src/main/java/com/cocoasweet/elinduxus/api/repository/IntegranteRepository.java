package com.cocoasweet.elinduxus.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cocoasweet.elinduxus.api.entity.IntegranteEntity;

@Repository
public interface IntegranteRepository extends JpaRepository<IntegranteEntity, Long> {

}
