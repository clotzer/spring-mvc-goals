package com.clotzer.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.clotzer.model.Goal;
import com.clotzer.model.Goal.Category;
import com.clotzer.service.GoalService;

@Controller
public class GoalController {

	@Autowired
	private GoalService service;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	@RequestMapping(value = "/list-goals", method = RequestMethod.GET)
	public String showGoalsList(ModelMap model, HttpSession httpSession) {
		
		String sessionKey = (String) httpSession.getAttribute("SessionData");

		if (sessionKey == null) {
			return "redirect:/";
		}
		
		String user = getLoggedInUserName();
		model.addAttribute("goals", service.retrieveGoals(user));
		List<Category> categoryList = Arrays.asList(Category.values());
		model.addAttribute("categories", categoryList);
		return "list-goals";
	}

	@RequestMapping(value = "/add-goal", method = RequestMethod.GET)
	public String showAddGoalPage(ModelMap model) {
		model.addAttribute("goal", new Goal());
		return "goal";
	}

	@RequestMapping(value = "/add-goal", method = RequestMethod.POST)
	public String addGoal(ModelMap model, @Valid Goal goal, BindingResult result) {

		if (result.hasErrors())
			return "goal";

		service.addGoal(getLoggedInUserName(), goal.getDescription(),
				goal.getDate(), false);
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-goals";
	}

	@RequestMapping(value = "/update-goal", method = RequestMethod.GET)
	public String showUpdateGoalPage(ModelMap model, @RequestParam int id) {
		model.addAttribute("goal", service.retrieveGoal(id));
		return "goal";
	}

	@RequestMapping(value = "/update-goal", method = RequestMethod.POST)
	public String updateGoal(ModelMap model, @Valid Goal goal,
			BindingResult result) {
		if (result.hasErrors())
			return "goal";

		goal.setUser(getLoggedInUserName());
		service.updateGoal(goal);

		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-goals";
	}

	@RequestMapping(value = "/delete-goal", method = RequestMethod.GET)
	public String deleteGoal(@RequestParam int id) {
		service.deleteGoal(id);

		return "redirect:/list-goals";
	}

}