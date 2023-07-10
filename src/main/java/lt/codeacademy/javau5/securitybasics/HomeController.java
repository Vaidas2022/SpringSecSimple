package lt.codeacademy.javau5.securitybasics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/anonymous")
	public String helloGuest() {
		return "Hello guest";
	}
	
	@GetMapping("/admin/a")
	public String helloAdmin() {
		return "Hello admin";
	}
	
	@GetMapping("/index")
	public String helloUser() {
		return "Hello User";
	}
	
	
	
}
