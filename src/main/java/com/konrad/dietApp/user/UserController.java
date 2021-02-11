package com.konrad.dietApp.user;

import com.konrad.dietApp.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}

	@RequestMapping(value = "/users/save/")
	public RedirectView saveUser(@RequestParam("image") MultipartFile multipartFile) throws IOException {
		User user=userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("jestem w user/save "+user.getEmail()+user.getFirstName());
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		user.setPhotos(fileName);
		User savedUser = userRepo.save(user);
		String uploadDir = "user-photos/" + savedUser.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

	/*	System.out.println("funkcja sie wykonuje saveUser");
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		user.setPhotos(fileName);

		User savedUser = userRepo.save(user);

		String uploadDir = "user-photos/" + savedUser.getId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

*/
		return new RedirectView("/show_user", true);
	}

	@RequestMapping(value = "/show_user")
	public String users(Model model) {
		User user = userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("user", user);

		return "show_user";
	}

	@RequestMapping(value="/saveUser")
	public String updateUser(@ModelAttribute("user") User user){

		userRepo.save(user);

		return "redirect:/show_user";
	}



}
