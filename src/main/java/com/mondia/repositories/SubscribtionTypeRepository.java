package com.mondia.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.mondia.entities.SubscribtionType;

@Transactional
public interface SubscribtionTypeRepository extends CrudRepository<SubscribtionType, Integer> {
//	public List<Sub> findBySubscribtionType(SubscribtionType subscribtionType);
//	@Query("Select st from SubscribtionType st , Contingent c where ? st.id = c.subscribtionType and c.type in :1")
//	public List<SubscribtionType> findByContingentType(@Param("contingentTypes") List<String> contingentTypes);
	public List<SubscribtionType> findDistinctByContingentsTypeIn(List<String> contingentTypes);
}
