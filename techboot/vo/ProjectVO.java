package com.techboot.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")

public class ProjectVO {
	
	@Id
	@GeneratedValue	
	private long projectId;
	private String projectName;
	private String startDate;
	private String endDate;
	private String projectReview;
	private String projectType;
	private String employerId;
	
	@ManyToMany(mappedBy="project",cascade=CascadeType.ALL)
	private List<EmployerVO>listEmployer;
	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getProjectReview() {
		return projectReview;
	}
	public void setProjectReview(String projectReview) {
		this.projectReview = projectReview;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public List<EmployerVO> getListEmployer() {
		return listEmployer;
	}
	public void setListEmployer(List<EmployerVO> listEmployer) {
		this.listEmployer = listEmployer;
	}
	public String getEmployerId() {
		return employerId;
	}
	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}
	
	
}
