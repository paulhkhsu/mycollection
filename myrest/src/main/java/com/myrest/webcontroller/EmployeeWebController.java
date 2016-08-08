package com.myrest.webcontroller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myrest.exception.RecordNotFoundException;
import com.myrest.pojo.Employee;
import com.myrest.service.EmployeeService;
import com.myrest.validation.EmployeeValidator;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeWebController extends WebMvcConfigurerAdapter {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String newEmployeePage(Model model) {
		System.out.println("create get");
		model.addAttribute("employee", new Employee());
		return "employee-new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createNewEmployee(@ModelAttribute @Valid Employee employee,
			BindingResult result, final RedirectAttributes redirectAttributes,
			Map<String, Object> model) {

		if (result.hasErrors()) 
			return "employee-new";
		
		employeeService.create(employee);
		String message = "New employee " + employee.getName()
				+ " was successfully created.";

		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/index.html";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String shopListPage(Model model) {
		List<Employee> l = employeeService.findAll();
		model.addAttribute("empList", l);
		return "employee-list";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editShopPage(@PathVariable Integer id,
			Model model) {
		Employee emp = employeeService.findById(id);
		model.addAttribute("employee", emp);
		return "employee-edit";
	}

//	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
//	public ModelAndView editShop(@ModelAttribute @Valid Employee emp,
//			BindingResult result, @PathVariable Integer id,
//			final RedirectAttributes redirectAttributes)
//			throws RecordNotFoundException {
//
//		if (result.hasErrors())
//			return new ModelAndView("employee-edit");
//
//		ModelAndView mav = new ModelAndView("redirect:/index.html");
//		String message = "Employee was successfully updated.";
//
//		employeeService.update(emp);
//
//		redirectAttributes.addFlashAttribute("message", message);
//		return mav;
//	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editShop(@ModelAttribute @Valid Employee employee,
			BindingResult result, @PathVariable Integer id,
			final RedirectAttributes redirectAttributes)
			throws RecordNotFoundException {

		if (result.hasErrors())
			return "employee-edit";


		employeeService.update(employee);

		String message = "Employee " + id.toString() + " was successfully updated.";
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/index.html";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteShop(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes)
			throws RecordNotFoundException {

		Employee emp = employeeService.delete(id);
		String message = "The employee " + emp.getName()
				+ " was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/index.html";
	}

}
