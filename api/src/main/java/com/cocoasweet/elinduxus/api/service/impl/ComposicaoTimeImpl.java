package com.cocoasweet.elinduxus.api.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cocoasweet.elinduxus.api.dto.ComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.dto.RequestComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.entity.ComposicaoTimeEntity;
import com.cocoasweet.elinduxus.api.repository.ComposicaoTimeRepository;
import com.cocoasweet.elinduxus.api.service.ComposicaoTime;

@Service
public class ComposicaoTimeImpl implements ComposicaoTime{
	
	@Autowired
	private ComposicaoTimeRepository composicaoTimeRepository;
	
	@Override
	public void saveComposicaoTime(ComposicaoTimeDTO compTime) {
		for(Long id: compTime.getIdIntegrante()) {
			ComposicaoTimeEntity composicaoEntity = new ComposicaoTimeEntity(compTime.getIdTime(), id);
			composicaoTimeRepository.save(composicaoEntity);
		}
	}
	
	public List<RequestComposicaoTimeDTO> findCompByTimeId(Long id){
		List<ComposicaoTimeEntity> composicaoTimeEntity = composicaoTimeRepository.findByTimeId(id);
		return composicaoTimeEntity.stream().map(RequestComposicaoTimeDTO::new).toList();
	}

	
	

}
