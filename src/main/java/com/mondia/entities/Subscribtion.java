package com.mondia.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the subscription database table.
 * 
 */
@Entity
@Getter @Setter
//@Cacheable(false)
@NamedQuery(name="Subscribtion.findAll", query="SELECT s FROM Subscribtion s")
public class Subscribtion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to SubscribtionType
	@ManyToOne
	@JoinColumn(name="subscribtion_type_id")
	private SubscribtionType subscribtionType;

	@JsonBackReference
	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;
	
//	@JsonManagedReference("consumed_contingents")
	// bi-directional many-to-one association to Contingent
	@OneToMany(mappedBy = "subscribtion")
	private List<ConsumedContingent> consumedContingents;

}