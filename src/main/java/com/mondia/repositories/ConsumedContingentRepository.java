package com.mondia.repositories;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DefaultValue;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mondia.entities.ConsumedContingent;

@Transactional
public interface ConsumedContingentRepository extends CrudRepository<ConsumedContingent, Integer> {

	List<ConsumedContingent> findDistinctByTypeAndSubscribtionUserIdAndAmountGreaterThan(@Param("type") String type, @Param("id") Integer id, @Param("threshold") int threshold);

	@Modifying
	@Query("UPDATE ConsumedContingent c SET c.amount = :amount WHERE c.id = :consumtionId")
	void updateAfterConsumingArticle(@Param("consumtionId") int consumtionId , @Param("amount")int amount);
}
