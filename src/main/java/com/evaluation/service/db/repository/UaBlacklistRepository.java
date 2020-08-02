package com.evaluation.service.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.service.db.model.UaBlacklist;

@Repository
public interface UaBlacklistRepository extends CrudRepository<UaBlacklist, String> {

}
