package com.mondia.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Getter @Setter
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;
	
	private String password;

	
	@JsonManagedReference
	//bi-directional many-to-one association to Subscribtion
	@OneToMany(mappedBy="user")
	private List<Subscribtion> subscribtions;
}