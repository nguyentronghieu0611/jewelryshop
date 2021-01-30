package vn.jewel.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.jewel.shop.model.SessionHistory;

import java.util.List;

@Repository
public interface SessionHistoryRepository extends JpaRepository<SessionHistory, Long> {
	
//	@Query(value ="SELECT * FROM sessionhistory  WHERE username=:username", countQuery ="SELECT COUNT(*) FROM sessionhistory  WHERE username=:username", nativeQuery = true)
//	List<SessionHistory> getByUsername(@Param("username") String username,Pageable pageable);
	
	@Query("SELECT a FROM SessionHistory a  WHERE a.username=:username")
    List<SessionHistory> getByUsername(@Param("username") String username);
	
	
	

}
