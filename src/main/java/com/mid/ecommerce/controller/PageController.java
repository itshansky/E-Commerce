package com.mid.ecommerce.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/page")
public class PageController {
	@GetMapping("/public")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('user') or hasAuthority('mod') or hasAuthority('admin')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasAuthority('mod')")
	public String moderatorAccess() {
		return "Moderator Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('admin')")
	public String adminAccess() {
		return "Admin Content.";
	}
}
