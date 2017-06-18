package com.mondia.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "user_article_consumbtion_history")
public class UserArticleConsumbtionHistory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Date consumbtionDate;

	@OneToOne(optional = false)
	@JoinColumn(name = "user_id ")
	private User user;

	@OneToOne(optional = false)
	@JoinColumn(name = "article_id ")
	private Article article;

}
