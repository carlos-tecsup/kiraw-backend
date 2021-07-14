package com.kiraw.LoginKiraw.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "clients")
public class Clients implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	

	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "name", nullable = false)
	private String name;


	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "surname", nullable = false)
	private String surname;

	@NotEmpty(message = "El campo no debe ser vacio")
	@Size(min = 9, max = 9, message = "Ingrese un numero valido")
	@Column(name = "phone", length = 9)
	private String phone;

	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "occupation")
	private String occupation;
	
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

	@JsonIgnoreProperties({"clients","hibernateLazyInitializer", "handler"  })
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "clients",cascade = CascadeType.MERGE)
	private List<Comments> comments;

	@JsonIgnoreProperties(value = {"clients","hibernateLazyInitializer", "handler"  },allowSetters = true)
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id", nullable = false, referencedColumnName = "id",unique = true)
	private Users users;




	@PrePersist
	public void prePersist() {
		lastUpdate=new Date();
		String strDateFormat = "hh: mm: ss a dd-MMM-aaaa";
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
	}
	public Integer getId() {
		return id;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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


	
	
	

}
