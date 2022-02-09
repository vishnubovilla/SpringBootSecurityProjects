package in.dminc.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String getHomePage() {
		return "homePage";
	}

	@GetMapping("/welcome")
	public String getWelcomePage() {
		return "welcomePage";
	}

	@GetMapping("/admin")
	public String getAdminPage() {
		return "adminPage";
	}

	@GetMapping("/emp")
	public String getEmployeePage() {
		return "empPage";
	}

	@GetMapping("/manager")
	public String getManagerPage() {
		return "managerPage";
	}

	@GetMapping("/common")
	public String getCommonPage() {
		return "commonPage";
	}

	@GetMapping("/accessDenied")
	public String getAccessDenied() {
		return "accessDeniedPage";
	}
}
