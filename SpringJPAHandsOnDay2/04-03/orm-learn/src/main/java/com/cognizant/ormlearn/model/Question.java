package com.cognizant.ormlearn.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qt_id")
	private int id;

	@Column(name = "qt_text")
	private String questionText;

	@OneToMany(mappedBy = "question")
	private Set<Options> optionsList;

	@OneToMany(mappedBy = "question")
	private Set<AttemptQuestion> attemptQuestionList;

	public Question() {

	}

	public Question(String questionText) {
		super();
		this.questionText = questionText;
	}

	public int getId() {
		return id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Set<Options> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(Set<Options> optionList) {
		this.optionsList = optionList;
	}

	public Set<AttemptQuestion> getAttemptQuestionList() {
		return attemptQuestionList;
	}

	public void setAttemptQuestionList(Set<AttemptQuestion> attemptQuestionList) {
		this.attemptQuestionList = attemptQuestionList;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + "]";
	}

}
