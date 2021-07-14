package com.kiraw.LoginKiraw.entity;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class  Users implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Email
	@NotEmpty(message = "El campo no debe ser vacio")
	@Column(name = "email", nullable = false,unique=true)
	private String email;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@NotEmpty(message = "El campo no debe ser vacio")
	@Size(min = 5, max = 12, message = "El numero de caracteres no es permitido, debe estar entre 8-12")
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	
		@Column(name = "enable")
		private boolean enable=true;
	
	@Column(name = "date_created")
	@CreationTimestamp
	
	private Date dateCreated;
	@JsonIgnoreProperties(value = { "users","hibernateLazyInitializer", "handler"  },allowSetters = true)

	@OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
	private Clients clients;


	@JsonIgnoreProperties(value={ "users","hibernateLazyInitializer", "handler" },allowSetters=true)
	@OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
	private Provider provider;

	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "user_authorities",joinColumns = @JoinColumn(name="users_id"),inverseJoinColumns =@JoinColumn(name="roles_id") )
	@Column(length = 20,columnDefinition = "varchar(20) default 'ROLE_USER'")
	public List<Roles> roles;
	

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
		
	}
	
	
	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	


	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", password=" + password + ", username=" + username
				+ ", enable=" + enable + ", dateCreated=" + dateCreated + ", clients=" + clients + ", roles=" + roles
				+ "]";
	}



}
