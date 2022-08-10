package com.techboot.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Employerattendance")
public class EmployerAttendanceVO {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String date;
	private String inTime;
	private String outTime;
	private String status;
	@ManyToOne
	@JoinColumn(name="emp_id")
	private EmployerVO employerVO;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public EmployerVO getEmployerVO() {
		return employerVO;
	}
	public void setEmployerVO(EmployerVO employerVO) {
		this.employerVO = employerVO;
	}
   
	
}
