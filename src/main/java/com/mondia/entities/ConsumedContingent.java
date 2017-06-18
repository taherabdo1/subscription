package com.mondia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "consumed_contingents")
public class ConsumedContingent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String type;

	private int amount;

	@JsonIgnore
//	@JsonBackReference("consumed_contingents")
	// bi-directional many-to-one association to SubscribtionType
	@ManyToOne
	@JoinColumn(name = "subscribtion_id")
	private Subscribtion subscribtion;

}
