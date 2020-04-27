package com.cognizant.ormlearn.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.SkillRepository;
import com.cognizant.ormlearn.service.exception.SkillNotFoundException;

@Service
public class SkillService {
	@Autowired
	private SkillRepository skillRepository;

	@Transactional
	public Skill get(int id) throws SkillNotFoundException {

		Optional<Skill> findById = skillRepository.findById(id);
		if (!findById.isPresent())
			throw new SkillNotFoundException("Skill Not Found for Id");
		return findById.get();

	}

	@Transactional
	public void save(Skill skill) {
		skillRepository.save(skill);

	}

}
