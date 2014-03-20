package nl.trifork.ngtest.pohf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/form.do")
public class FormController {

	@RequestMapping(method = RequestMethod.GET)
	public String getForm() {
		return "form";
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String postForm(Model model,
			@RequestParam("firstName") String firstName,
			@RequestParam("age") String age) {
		
		List<String> messages = new ArrayList<String>();
		messages.addAll(validateFirstName(firstName));
		messages.addAll(validateAge(age));
		if(messages.isEmpty()) {
			System.out.println("Processing validated form.");
			System.out.println("First name: " + firstName);
			System.out.println("Age: " + age);
			return "confirm";
		} else {
			model.addAttribute("messages", messages);
			model.addAttribute("firstName", firstName);
			model.addAttribute("age", age);
			return "form";
		}
	}	
	
	private List<String> validateFirstName(String firstName) {
		List<String> messages = new ArrayList<String>();
		if(firstName == null || firstName.isEmpty()) {
			messages.add("First name is required.");
		} else if(firstName.length() < 3) {
			messages.add("First name must at least have 3 characters.");
		} else if(firstName.length() > 20) {
			messages.add("First name must have no more than 20 characters.");
		}
		return messages;
	}
	
	private List<String> validateAge(String age) {
		List<String> messages = new ArrayList<String>();		
		if(age == null || age.isEmpty()) {
			messages.add("Age is required.");
		} else {
			try {
				Integer ageInt = Integer.parseInt(age);
				if(ageInt < 0) {
					messages.add("Age cannot be negative.");
				} else if(ageInt > 130) {
					messages.add("Age cannot be more than 130.");
				}
			} catch(NumberFormatException nfe) {
				messages.add("Age must be an integer number.");
			}
		}
		return messages;
	}
}
