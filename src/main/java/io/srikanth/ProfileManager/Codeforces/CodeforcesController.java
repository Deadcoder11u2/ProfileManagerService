package io.srikanth.ProfileManager.Codeforces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/codeforces")
@Controller
public class CodeforcesController {
    
    @GetMapping("/hello")
    public String hello() {
	return "Hello";
    }
}
