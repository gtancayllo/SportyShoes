package com.sportyshoes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.entity.User;
import com.sportyshoes.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/index")
	public String home(User user) {
		return "index";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "index";					
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request,User user, BindingResult result,Model model) {
		HttpSession session = request.getSession(true);
		User existingUser = userService.findUserLogged(user);
		if(existingUser!=null) {
			session.setAttribute("CurrentUser", existingUser);
			return "home";
		}else {
			result.rejectValue("username", null, "The user is not registered.");
			model.addAttribute("user",user);
			return "index";
		}					
	}

	// handler method to handle user registration form request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "register";
	}
	
	@GetMapping("/changePassword")
	public String showPwdChange() {
		return "changePwd";
	}
	
	@PostMapping("/register/changePwd")
	public String changePassword(HttpServletRequest request,@RequestParam("newPassword") String newPassword) {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("CurrentUser")!=null)
		{
			User user = (User)session.getAttribute("CurrentUser");
			user.setPassword(newPassword);
			userService.saveUser(user);
			return "index";
		}
		else
			return "changePwd";
	}
	
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		User existingUser = userService.findUserByEmail(user.getEmail());

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
		}

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "/register";
		}

		userService.saveUser(user);
		return "redirect:/register?success";
	}

}
