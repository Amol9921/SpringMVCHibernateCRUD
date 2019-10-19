package com.jwt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.jwt.model.Employee;
import com.jwt.service.EmployeeService;



@Controller
public class EmployeeController {

	private static final Logger logger = Logger
			.getLogger(EmployeeController.class);

	public EmployeeController() {
		System.out.println("EmployeeController()");
	}

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/home")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("home");
		return model;
	}
	

	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
	
		Employee employee = new Employee();
		model.addObject("employee", employee);
		model.setViewName("EmployeeForm");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		if (employee.getId() == 0) { // if employee id is 0 then creating the
			// employee other updating the employee
			employeeService.addEmployee(employee);
		} else {
			employeeService.updateEmployee(employee);
		}
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		employeeService.deleteEmployee(employeeId);
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(employeeId);
		ModelAndView model = new ModelAndView("EmployeeForm");
		model.addObject("employee", employee);

		return model;
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("employee") Employee employee) {
	    ModelAndView mav = null;
	    
	    Employee employee1 = employeeService.validateUser(employee);
	    
	    if (employee1.getName()!=null) {
	    return new ModelAndView("redirect:/home");
	    } else {
	    mav = new ModelAndView("login");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("employee", new Employee());
	    return mav;
	  }
	
	 @RequestMapping(value = "/forgetpassword ", method = RequestMethod.GET)
	  ModelAndView verify(HttpServletRequest request, HttpServletResponse response)
	  { 
		ModelAndView mav = new ModelAndView("confirm"); 
        mav.addObject("employee", new Employee()); 
	   return mav;  
	   }
	 
	 @RequestMapping(value = "/verify", method = RequestMethod.POST)
	  public ModelAndView verify(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("employee") Employee employee) {
	   ModelAndView mav = null;	    
	    Employee employee1 = employeeService.verify(employee);
	    if (employee1.getName()!=null) {
	 
	    return new ModelAndView("redirect:/newpass?id="+employee1.getId()+"");
	    } else {
	    mav = new ModelAndView("confirm");
	    mav.addObject("message", "Please Enter correct Telephone !");
	    }
	    return mav;
	  }
	
		@RequestMapping(value = "/newpass")
		public ModelAndView viewNewPass( HttpServletRequest request) throws IOException {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			Employee employee = employeeService.getEmployee(employeeId);
			ModelAndView model = new ModelAndView("newPassword");
			model.addObject("employee", employee);
			return model;
		}
		

		@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
		public ModelAndView updatePassword(@ModelAttribute Employee employee ,ModelAndView mav) {
			
			if (employee.getPassword().equals(employee.getNewpassword())) {
				Employee employee1 = employeeService.getEmployee(employee.getId());
				
				employee.setAddress(employee1.getAddress());
				employee.setEmail(employee1.getEmail());
				employee.setName(employee1.getName());
				employee.setTelephone(employee1.getTelephone());			
				employeeService.updateEmployee(employee);
				
				return new ModelAndView("redirect:/");
			    } else {
			    mav = new ModelAndView("newPassword");
			    mav.addObject("message", "Enter Both Password identical!!");
			    }
			    return mav;
			
			
		}
	
	 	

}