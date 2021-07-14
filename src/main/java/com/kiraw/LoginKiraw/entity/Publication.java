package com.kiraw.LoginKiraw.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "publication")
public class Publication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "image_publication")
	private String image_publication;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "title_publication")
	private String title_publication;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "title_description")
	private String title_description;
	@OrderBy("id DESC")

	@JsonIgnoreProperties({"comments","hibernateLazyInitializer", "handler"  })
	@OneToMany(fetch = FetchType.LAZY,mappedBy ="publication",cascade = CascadeType.MERGE )
	private List<Comments> comments;
	@CreationTimestamp
	@Column(name = "creation_date")

	private Date creation_date;
	public Publication() {
		this.comments=new ArrayList<>();
	}







	@PrePersist
	public void prePersist() {
		creation_date=new Date();
	}


	public Date getCreation_date() {
		return creation_date;
	}




	@JsonIgnoreProperties({"publication","hibernateLazyInitializer","handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Provider provider;

	
	

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}


	public List<Comments> getCommentsWithIdSortedInDesc() {
		return comments;
	}



	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getImage_publication() {
		return image_publication;
	}


	public void setImage_publication(String image_publication) {
		this.image_publication = image_publication;
	}


	public String getTitle_publication() {
		return title_publication;
	}


	public void setTitle_publication(String title_publication) {
		this.title_publication = title_publication;
	}


	public String getTitle_description() {
		return title_description;
	}


	public void setTitle_description(String title_description) {
		this.title_description = title_description;
	}


	


	public Provider getProvider() {
		return provider;
	}


	public void setProvider(Provider provider) {
		this.provider = provider;
	}



	
	
	
}
