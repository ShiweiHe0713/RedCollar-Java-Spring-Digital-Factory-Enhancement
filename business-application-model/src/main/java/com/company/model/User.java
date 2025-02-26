package com.company.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// entity could used for both jbpm and external uses;
// This data is used by two parts of the system
// How does the change in User db affect JBPM's db, making sure they're interacting
// TODO: marshalling strategy
// JPA marshaller and persistence unit configured in the kie-deployment-descriptor.xml file from 12.6 JBPM Doc

@javax.persistence.Entity  
@javax.persistence.Table(name = "Person")  
public class User extends org.drools.persistence.jpa.marshaller.VariableEntity  
implements java.io.Serializable {
// @Entity
@Table(name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "name"),
      @UniqueConstraint(columnNames = "email")
    })
public class User {
	//public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String name;

	@NotBlank
	@Size(max = 254)
	@Email
	private String email;

	public User() {}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
