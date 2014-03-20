package nl.trifork.ngtest.angular;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/form.do")
public class FormController {

	static class FormData {
		public String firstName;
		public String age;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> postForm(@RequestBody FormData formData) {
		List<String> messages = new ArrayList<String>();
		messages.addAll(validateFirstName(formData.firstName));
		messages.addAll(validateAge(formData.age));
		if (messages.isEmpty()) {
			System.out.println("Processing validated form.");
			System.out.println("First name: " + formData.firstName);
			System.out.println("Age: " + formData.age);
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<String>>(messages, HttpStatus.BAD_REQUEST);
		}
	}

	private List<String> validateFirstName(String firstName) {
		List<String> messages = new ArrayList<String>();
		if (firstName == null || firstName.isEmpty()) {
			messages.add("First name is required.");
		} else if (firstName.length() < 3) {
			messages.add("First name must at least have 3 characters.");
		} else if (firstName.length() > 20) {
			messages.add("First name must have no more than 20 characters.");
		}
		return messages;
	}

	private List<String> validateAge(String age) {
		List<String> messages = new ArrayList<String>();
		if (age == null || age.isEmpty()) {
			messages.add("Age is required.");
		} else {
			try {
				Integer ageInt = Integer.parseInt(age);
				if (ageInt < 0) {
					messages.add("Age cannot be negative.");
				} else if (ageInt > 130) {
					messages.add("Age cannot be more than 130.");
				}
			} catch (NumberFormatException nfe) {
				messages.add("Age must be an integer number.");
			}
		}
		return messages;
	}
}
