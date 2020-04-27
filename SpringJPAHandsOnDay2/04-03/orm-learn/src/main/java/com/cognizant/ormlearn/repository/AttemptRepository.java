package com.cognizant.ormlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.ormlearn.model.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt,Integer>{
	
	
	public Attempt getAttempt(int userId,int attemptId);

}
