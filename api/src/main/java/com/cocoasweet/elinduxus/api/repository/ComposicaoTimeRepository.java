package com.cocoasweet.elinduxus.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cocoasweet.elinduxus.api.entity.ComposicaoTimeEntity;

@Repository
public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTimeEntity, Long> {

	List<ComposicaoTimeEntity> findByTimeId(Long id);
}
