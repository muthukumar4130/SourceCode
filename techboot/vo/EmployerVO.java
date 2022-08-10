package com.techboot.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employer")
	
	public class EmployerVO {
		
		@Id
		@GeneratedValue		
		private long id;
		private String name;
		private String email;
		private long mobile;
		private String address;
		
		@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
		private List<EmployerAttendanceVO>employerAttendance;
		
		@ManyToMany()
		@JoinTable(name="emp_project",
		joinColumns=@JoinColumn(name="emp_id"),
		inverseJoinColumns=@JoinColumn(name="project_Id"))
		private List<ProjectVO> project;
		
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
		public long getMobile() {
			return mobile;
		}
		public void setMobile(long mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public List<EmployerAttendanceVO> getEmployerAttendance() {
			return employerAttendance;
		}
		public void setEmployerAttendance(List<EmployerAttendanceVO> employerAttendance) {
			this.employerAttendance = employerAttendance;
		}
		public List<ProjectVO> getProjects() {
			return project;
		}
		public void setProjects(List<ProjectVO> projects) {
			this.project = projects;
		}
		
		
}
