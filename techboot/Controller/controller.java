package com.techboot.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techboot.BO.EmployerBO;
import com.techboot.Service.EmployerService;


@Controller
public class controller {
	
	
	@Autowired
      EmployerService employerservice;
	
	@RequestMapping("/")
	public String showHompage() {
		return "index";
	}

	@RequestMapping(value ="/welcome", method =RequestMethod.GET)
	public String login(HttpServletRequest request,Model model) throws ServletException, IOException{
		
		if(null!=request.getParameter("message")) {
			String message=request.getParameter("message");
			model.addAttribute("message",message);
		}
		return"home";
		}
	
	@RequestMapping(value ="/createemployer", method = RequestMethod.GET)
	public String createemployer(HttpServletRequest request,Model model) {
		
		EmployerBO employerBO =new EmployerBO();
		
		model.addAttribute("employerBO", employerBO);
		
		return "create-employer";
	}
	
	
	@RequestMapping (value="/createemployer",method = RequestMethod.POST)
	
	public String CreateEmployer (@ModelAttribute("employerBO")EmployerBO employerBO,
			HttpServletRequest request,HttpServletResponse response,Model model) throws Exception   {
		
		long status=0;
		if(employerBO.getId()==0) {
	
		status=employerservice.createEmployer(employerBO);
	}
		else {
			status=employerservice.updateEmployer(employerBO);
		}
		
		if(status >0) {
			model.addAttribute("message","success");
					}else {
						model.addAttribute("message","Fail");
					}
		return"redirect:/view-employer";
		
		
	}
	@RequestMapping (value ="/view-employer",method=RequestMethod.GET)
	public String viewEmployer(HttpServletRequest request,Model model) throws ServletException, IOException{
		
		try {
		List<EmployerBO> list=employerservice.viewEmployer();
		model.addAttribute("list",list);}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(null!=request.getParameter("message")) {
			String message=request.getParameter("message");
			model.addAttribute("message",message);
		}
		
		return "view-employer";
	}
	
	
	@RequestMapping (value ="/edit-employer",method=RequestMethod.GET)
	public String editEmployer(@RequestParam("id")long id,HttpServletRequest request,Model model) throws Exception{
		
		EmployerBO employerBO=new EmployerBO();
		employerBO.setId(id);
		EmployerBO emp=employerservice.editEmployer(employerBO);
	
	model.addAttribute("employerBO", emp);
	
	return "create-employer";
	}
	
	@RequestMapping (value ="/delete-employer",method=RequestMethod.GET)
	public String deleteEmployer(HttpServletRequest request,Model model) throws Exception{
		
		String ids=request.getParameter("id");
		long id =Long.parseLong(ids);
		EmployerBO employerBO=new EmployerBO();
		employerBO.setId(id);
		long status1=employerservice.deleteEmployer(employerBO);
		
		model.addAttribute("employerBO", employerBO);
		
		return "redirect:/view-employer";
		}
	}
		
