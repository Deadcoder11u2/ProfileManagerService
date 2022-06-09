package io.srikanth.ProfileManager.Codeforces;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeforcesController {

    @Autowired
    public CodeforcesService codeforcesService;

    @GetMapping("/hello")
    public String hello() {
	return "Hello";
    }

    @GetMapping("/contest.update")
    public Boolean contestUpdate() throws IOException {
	return codeforcesService.cacheContests();
    }
}
