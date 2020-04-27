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
@Table(name = "options")
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "op_id")
	private int id;

	@Column(name = "op_score")
	private Double score;

	@Column(name = "op_text")
	private String optionText;

	@ManyToOne
	@JoinColumn(name = "op_qt_id")
	private Question question;

	@OneToMany(mappedBy = "options")
	private Set<AttemptOption> attemptOptionList;

	public Options() {

	}

	public Options(Double score, String optionText) {
		super();
		this.score = score;
		this.optionText = optionText;
	}

	public int getId() {
		return id;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Set<AttemptOption> getAttemptOptionList() {
		return attemptOptionList;
	}

	public void setAttemptOptionList(Set<AttemptOption> attemptOptionList) {
		this.attemptOptionList = attemptOptionList;
	}

	@Override
	public String toString() {
		return "Options [score=" + score + ", optionText=" + optionText + "]";
	}

}
