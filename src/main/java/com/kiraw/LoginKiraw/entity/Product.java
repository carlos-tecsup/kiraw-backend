package com.kiraw.LoginKiraw.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import java.lang.reflect.Type;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "product_name", nullable = false)
	private String name;



	@NotNull
	@Column(name = "price", nullable = false)
	private Double price;

	@NotNull
	@Column(name = "stock", nullable = false)
	private int stock;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "description", nullable = false)
	private String description;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "product_image", nullable = false)
	private String image;
	@Column(name = "date_created")
	@CreationTimestamp
	private Date date_created;

	@JsonIgnoreProperties({"products","hibernateLazyInitializer", "handler"  })
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Provider	 provider;



	@JsonIgnoreProperties({"products","hibernateLazyInitializer", "handler"  })
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Category category;

	public Product() {
	}

	public Product(Category category) {
		this.category = category;
	}

	@PrePersist
	public void prePersist() {
		date_created=new Date();
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Integer getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
}
