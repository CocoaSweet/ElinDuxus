package com.cocoasweet.elinduxus.api.service;

import java.util.List;
import com.cocoasweet.elinduxus.api.dto.ComposicaoTimeDTO;
import com.cocoasweet.elinduxus.api.dto.RequestComposicaoTimeDTO;

public interface ComposicaoTime {
	
	void saveComposicaoTime(ComposicaoTimeDTO compTime);
	public List<RequestComposicaoTimeDTO> findCompByTimeId(Long id);
}
