package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attempt_option")
public class AttemptOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ao_id")
	private int id;

	@Column(name = "ao_selected")
	private Byte selected;

	@ManyToOne
	@JoinColumn(name = "ao_op_id")
	private Options options;

	@ManyToOne
	@JoinColumn(name = "ao_aq_id")
	private AttemptQuestion attemptQuestion;

	public int getId() {
		return id;
	}

	public AttemptOption() {

	}

	public AttemptOption(int id, Byte selected) {
		super();
		this.id = id;
		this.selected = selected;
	}

	public Byte getSelected() {
		return selected;
	}

	public void setSelected(Byte selected) {
		this.selected = selected;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public AttemptQuestion getAttemptQuestion() {
		return attemptQuestion;
	}

	public void setAttemptQuestion(AttemptQuestion attemptQuestion) {
		this.attemptQuestion = attemptQuestion;
	}

	@Override
	public String toString() {
		return "AttemptOption [id=" + id + ", selected=" + selected + "]";
	}

}
