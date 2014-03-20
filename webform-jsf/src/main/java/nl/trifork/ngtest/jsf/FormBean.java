package nl.trifork.ngtest.jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "form")
@ViewScoped
public class FormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private Integer age;

	public Object submit() {
		System.out.println("Processing validated form.");
		System.out.println("First name: " + firstName);
		System.out.println("Age: " + age);
		return "/confirm.xhtml";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
