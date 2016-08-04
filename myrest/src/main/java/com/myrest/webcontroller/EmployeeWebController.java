package com.myrest.webcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myrest.exception.RecordNotFoundException;
import com.myrest.pojo.Employee;
import com.myrest.service.EmployeeService;
import com.myrest.validation.EmployeeValidator;

@RestController
@Controller
@RequestMapping(value = "/employee")
public class EmployeeWebController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeValidator employeeValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(employeeValidator);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newEmployeePage() {
		ModelAndView mav = new ModelAndView("employee-new", "emp",
				new Employee());
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewEmployee(@ModelAttribute @Valid Employee emp,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("employee-new");

		ModelAndView mav = new ModelAndView();
		String message = "New employee " + emp.getName()
				+ " was successfully created.";

		employeeService.create(emp);
		mav.setViewName("redirect:/index.html");

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView shopListPage() {
		ModelAndView mav = new ModelAndView("employee-list");
		List<Employee> l = employeeService.findAll();
		mav.addObject("empList", l);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editShopPage(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("employee-edit");
		Employee emp = employeeService.findById(id);
		mav.addObject("emp", emp);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editShop(@ModelAttribute @Valid Employee emp,
			BindingResult result, @PathVariable Integer id,
			final RedirectAttributes redirectAttributes)
			throws RecordNotFoundException {

		if (result.hasErrors())
			return new ModelAndView("employee-edit");

		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Employee was successfully updated.";

		employeeService.update(emp);

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteShop(@PathVariable Integer id,
			final RedirectAttributes redirectAttributes)
			throws RecordNotFoundException {

		ModelAndView mav = new ModelAndView("redirect:/index.html");

		Employee emp = employeeService.delete(id);
		String message = "The employee " + emp.getName()
				+ " was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

}
