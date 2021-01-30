package vn.jewel.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import vn.jewel.shop.model.SessionHistory;
import vn.jewel.shop.repository.SessionHistoryRepository;

import java.util.List;

@Service
public class SessionHistoryService extends AbstractService<SessionHistory, Long>{

	 @Autowired
	    private SessionHistoryRepository sessionHistoryRepository;
	 
	@Override
	protected JpaRepository<SessionHistory, Long> getRepository() {

		return sessionHistoryRepository;
	}
	
//	public List<SessionHistory> getByUsername(String username, Integer page,Integer limit)
//	{
//		Pageable pageable = PageRequest.of(page - 1, limit, Sort.Direction.DESC, "created_at");
//		return sessionHistoryRepository.getByUsername(username,pageable);
//	}
	
	public List<SessionHistory> getByUsername(String username)
	{
		return sessionHistoryRepository.getByUsername(username);
	}


}
