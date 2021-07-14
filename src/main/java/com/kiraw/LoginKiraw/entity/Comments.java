package com.kiraw.LoginKiraw.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Entity
@Table(name="comments")

public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "El campo no debe ser vacio")
    @Column(name = "comment", nullable = false)
    private String comment;

    @JsonIgnoreProperties(value = {"comments","hibernateLazyInitializer", "handler"  },allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)

    private Provider provider;

    @JsonIgnoreProperties(value = {"comments","hibernateLazyInitializer", "handler"  },allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Clients clients;

    @JsonIgnore

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Publication publication;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date date_created;

    public Comments() {
    }

    public Comments(Integer id, @NotEmpty(message = "El campo no debe ser vacio") String comment, Provider provider, Clients clients, Publication publication, Date date_created) {
        this.id = id;
        this.comment = comment;
        this.provider = provider;
        this.clients = clients;
        this.publication = publication;
        this.date_created = date_created;
    }

    public Comments(String comment) {
        this.comment = comment;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Publication getPublication() {
        return publication;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @PrePersist
    public void prePersist() {
        date_created=new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
}
