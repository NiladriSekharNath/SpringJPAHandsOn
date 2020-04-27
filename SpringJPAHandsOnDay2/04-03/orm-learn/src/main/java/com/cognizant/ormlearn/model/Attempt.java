package com.cognizant.ormlearn.model;

import java.util.Date;
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
@Table(name = "attempt")
public class Attempt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "at_id")
	private int id;

	@Column(name = "at_date")
	private Date attemptDate;

	@Column(name = "at_score")
	private Double score;

	@ManyToOne
	@JoinColumn(name = "at_us_id")
	private User user;

	@OneToMany(mappedBy = "attempt")
	private Set<AttemptQuestion> attemptQuestionList;

	public Attempt() {
	}

	public Attempt(Date attemptDate, Double score) {
		super();
		this.attemptDate = attemptDate;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public Date getAttemptDate() {
		return attemptDate;
	}

	public void setAttemptDate(Date attemptDate) {
		this.attemptDate = attemptDate;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<AttemptQuestion> getAttemptQuestionList() {
		return attemptQuestionList;
	}

	public void setAttemptQuestionList(Set<AttemptQuestion> attemptQuestionList) {
		this.attemptQuestionList = attemptQuestionList;
	}

	@Override
	public String toString() {
		return "Attempt [id=" + id + ", attemptDate=" + attemptDate + ", score=" + score + "]";
	}

}
