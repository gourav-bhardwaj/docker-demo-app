package com.sp.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Employee {
	
	@Id
	private String empId;
	private String empName;
	private String empEmail;
	private BigDecimal empSalary;
	private LocalDate empDoj;
	private String environment;

}
