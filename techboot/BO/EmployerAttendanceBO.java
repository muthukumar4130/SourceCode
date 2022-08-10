package com.techboot.BO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class EmployerAttendanceBO {
	
	private long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String date;
	private String inTime;
	private String outTime;
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z\\\\s]*$",message="validation")
	private String status;
	
	private EmployerBO employerBO;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EmployerBO getEmployerBO() {
		return employerBO;
	}

	public void setEmployerBO(EmployerBO employerBO) {
		this.employerBO = employerBO;
	}
	
	
   
}
