package com.masai.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.app.model.CurrentUserSession;

@Repository
public interface SessionDao extends JpaRepository<CurrentUserSession, String> {
	public CurrentUserSession findByUuid(String uuid);

	public List<CurrentUserSession> findByUserId(Integer userId);
}
