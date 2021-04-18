package com.crud.bookshop.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

@Validated
@Entity
@Table(name="book")
@Getter
@Setter
public class Book implements Serializable{
		
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(max=100)
	private String title;
	
	@NotBlank
	@Size(max=50)
	private String author;
	
	private double money;
	
	private int stock;
	
	private String intro;
	
	private String cover;

}
