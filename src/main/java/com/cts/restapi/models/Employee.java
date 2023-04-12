package com.cts.restapi.models;

import com.cts.restapi.custom.annotations.MinDataValue;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="employee")
public class Employee {

	@Id
	private Integer empId;
	@NotEmpty(message = "can not be empty")
	private String empName;
	private Double empSal;
//	@Min(5)
	@MinDataValue(value = 6,message = "can not be less than")
	private int age;
	
}
