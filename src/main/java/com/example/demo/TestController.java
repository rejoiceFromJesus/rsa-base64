package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@PostMapping("/login")
	public String login(@RequestBody LoginBo loginBo) {
		System.out.println(loginBo.getPassword());
		return RSAUtil.decode(loginBo.getPassword());
	}
	
	@GetMapping("/public-key")
	public String publicKey() {
		return RSAUtil.publicKey;
	}
}
