package com.cognizant.ormlearn.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "attempt_question")
public class AttemptQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attempt_question")
	private int id;

	@ManyToOne
	@JoinColumn(name = "aq_at_id")
	private Attempt attempt;

	@ManyToOne
	@JoinColumn(name = "aq_qt_id")
	private Question question;
	
	@OneToMany(mappedBy="attemptQuestion")
	private Set<AttemptOption> attemptOptionList;

	public AttemptQuestion() {

	}

	public Attempt getAttempt() {
		return attempt;
	}

	public void setAttempt(Attempt attempt) {
		this.attempt = attempt;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "AttemptQuestion [id=" + id + "]";
	}

}
