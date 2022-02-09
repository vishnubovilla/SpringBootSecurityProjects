package in.dminc.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

	@GetMapping("/home")
	public String homePage() {
		return "home";
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
	}

	@GetMapping("/user")
	public String userPage() {
		return "user";
	}
	
	@GetMapping("/403")
	public String accessDeniedPage() {
		return "error/403";
	}
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
}
