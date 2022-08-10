package com.techboot.Controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techboot.BO.EmployerAttendanceBO;
import com.techboot.BO.EmployerBO;
import com.techboot.Service.EmployerService;

@Controller
public class EmployerAttendanceController {
	
	@Autowired
	EmployerService employerservice;
	
	@RequestMapping(value="/create-employer-attendance",method=RequestMethod.GET)
	public String createEmployer(HttpServletRequest request,Model model) {
		
		List<EmployerBO> list=employerservice.viewEmployer();
		model.addAttribute("employerList",list);
		model.addAttribute("employerAttendanceBO",new EmployerAttendanceBO());
		
		return "create-employer-attendance";
	}
	
	@RequestMapping(value="/create-employer-attendance",method=RequestMethod.POST)
	public String createEmployer(@ModelAttribute("employerAttendanceBO")@Valid EmployerAttendanceBO employerAttendanceBO,
			BindingResult result,HttpServletRequest request,HttpServletResponse response,Model model) {
			
		if(result.hasErrors()) {
			model.addAttribute("employerAttendanceBO",employerAttendanceBO);
			return "create-employer-attendance";
		}
		long status=0;
		status=employerservice.createEmployerAttendance(employerAttendanceBO);
		
		if(status>0) {
			model.addAttribute("message", "Success");
		}else {
			model.addAttribute("message", "Fail");
		}
		
		return "redirect:/login";
		}
	@RequestMapping(value="/view-employer-attendance",method=RequestMethod.GET)
	public String viewEmployerAttendance(HttpServletRequest request,Model model)throws ServletException{
		
		List<EmployerAttendanceBO> list=employerservice.viewEmployerAttendance();
		model.addAttribute("list",list);
		return"view-employer-attendance";
	}
	
	}


