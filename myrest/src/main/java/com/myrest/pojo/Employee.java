package com.myrest.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "employee")
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value="emp")  // need Jackson2ObjectMapperBuilder to work 
public class Employee {

	@Id
	@GeneratedValue
	@XmlElement
	private Integer id;

	@Size(min = 5, max = 15)
	@XmlElement
	private String name;

	@NotNull
	@Max(100)
	@XmlElement
	@Column(name = "employees_number")
	private Integer emplNumber;

	public Integer getId() {
		return id;
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

	public Integer getEmplNumber() {
		return emplNumber;
	}

	public void setEmplNumber(Integer emplNumber) {
		this.emplNumber = emplNumber;
	}

}
