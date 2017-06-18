package com.mondia.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the contingent database table.
 * 
 */
@Entity
@Getter @Setter
@NamedQuery(name = "Contingent.findAll", query = "SELECT c FROM Contingent c")
public class Contingent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int amount;

	private String type;

	@JsonBackReference("contingents")
	// bi-directional many-to-one association to SubscribtionType
	@ManyToOne
	@JoinColumn(name = "subscribtion_package_id")
	private SubscribtionType subscribtionType;

}