package com.glue.repositories;

import com.glue.entities.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CriteriaRepository extends JpaRepository<Criteria, Integer> {

    @Query("select new com.glue.entities.Criteria(c.id,c.nameCriteria) from Criteria c where c.nameCriteria in :list")
    List<Criteria> findBynameCriteria(List<String> list);
}
