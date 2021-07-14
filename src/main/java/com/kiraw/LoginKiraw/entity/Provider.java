package com.kiraw.LoginKiraw.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "provider")
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	

	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "name", nullable = false)
	private String name;

	@NotEmpty(message = "El campo no debe ser vacio")
	@Size(min = 9, max = 9, message = "Ingrese un numero valido")
	@Column(name = "phone", length = 9)
	private String phone;

	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "city", nullable = false)
	private String city;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "address")
	private String address;



	@Column(name = "img_profile", columnDefinition = "varchar(255) default 'https://d500.epimg.net/cincodias/imagenes/2016/07/04/lifestyle/1467646262_522853_1467646344_noticia_normal.jpg '")
	private String imgProfile;
		
	@Column(name = "img_background", columnDefinition = "varchar(255) default 'https://www.mactualidad.com/wp-content/uploads/2013/06/fondo-pantalla-mavericks.jpg'")
	private String imgBackground;
	
	@Column(name = "last_update")
	@Temporal(TemporalType.DATE)	
	private Date lastUpdate;

	@JsonIgnoreProperties(value = {"provider","hibernateLazyInitializer", "handler"  },allowSetters = true)
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id", nullable = false, referencedColumnName = "id",unique = true)
	private Users users;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "provider",cascade = CascadeType.ALL)
	private List<Publication> publication;

	@JsonIgnoreProperties({"provider","hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "provider",cascade = CascadeType.ALL)
	private List<Product> products;

	@JsonIgnoreProperties({"provider","hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "provider",cascade = CascadeType.MERGE)
	private List<Comments> comments;


	public Provider(){
		this.comments=new ArrayList<>();
		this.products=new ArrayList<>();
		this.publication=new ArrayList<>();

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}



	@PrePersist
	public void prePersist() {
		lastUpdate=new Date();
	}
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImgProfile() {
		return imgProfile;
	}

	public void setImgProfile(String imgProfile) {
		this.imgProfile = imgProfile;
	}

	public String getImgBackground() {
		return imgBackground;
	}

	public void setImgBackground(String imgBackground) {
		this.imgBackground = imgBackground;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<Publication> getPublication() {
		return publication;
	}

	public void setPublication(List<Publication> publication) {
		this.publication = publication;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
