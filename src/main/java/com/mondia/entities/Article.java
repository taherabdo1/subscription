package com.mondia.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the articles database table.
 * 
 */
@Entity
@Getter
@Setter
@Table(name = "articles")
@NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String type;

	@OneToOne(optional = true, mappedBy = "article")
	UserArticleConsumbtionHistory userArticleConsumbtionHistory;

}