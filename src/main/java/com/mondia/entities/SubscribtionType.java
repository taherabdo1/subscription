package com.mondia.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the subscribtion_type database table.
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "subscribtion_type")
@NamedQuery(name = "SubscribtionType.findAll", query = "SELECT s FROM SubscribtionType s")
public class SubscribtionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private float price;
	@JsonManagedReference("contingents")
	// bi-directional many-to-one association to Contingent
	@OneToMany(mappedBy = "subscribtionType")
	private List<Contingent> contingents;

	@JsonIgnore
	// bi-directional many-to-one association to Subscribtion
	@OneToMany(mappedBy = "subscribtionType")
	private List<Subscribtion> subscribtions;

	public SubscribtionType() {
	}

}