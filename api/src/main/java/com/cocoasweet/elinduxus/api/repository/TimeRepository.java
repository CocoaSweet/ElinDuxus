package com.cocoasweet.elinduxus.api.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cocoasweet.elinduxus.api.entity.TimeEntity;

@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long>{

	List<TimeEntity> findByData(LocalDate data);
	List<TimeEntity> findAllByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}