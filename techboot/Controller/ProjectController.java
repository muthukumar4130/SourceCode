package com.techboot.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techboot.BO.ProjectBO;
import com.techboot.Service.EmployerService;

@Controller
public class ProjectController {
	
	@Autowired
	EmployerService employerservice;
	
	@RequestMapping(value="/createproject", method=RequestMethod.GET)
	public String createproject(HttpServletRequest request,Model model) {
		
		ProjectBO projectBO=new ProjectBO();
		model.addAttribute("projectBO",projectBO);
		
		return "create-project";	
	}
	
	@RequestMapping(value="/createproject", method=RequestMethod.POST)
	public String createproject(@ModelAttribute("projectBO") ProjectBO projectBO,
			HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		 
		long status=employerservice.createproject(projectBO);
		if(status>0) {
		model.addAttribute("message","success");
		}else {
			model.addAttribute("message","Fail");
		}
		return"redirect:/view-project";
		
	}
	@RequestMapping(value="/view-project",method=RequestMethod.GET)
	public String viewproject(HttpServletRequest request,Model model)throws Exception{
		
		List<ProjectBO> list=employerservice.viewproject();
		model.addAttribute("list",list);
		return "view-project";
	}

}
