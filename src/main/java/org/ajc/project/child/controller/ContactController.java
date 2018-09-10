package org.ajc.project.child.controller;

import java.util.Map;

import org.ajc.project.child.model.Child;
import org.ajc.project.child.model.Contact;
import org.ajc.project.child.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

	private final ContactService contactService;

	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String getContactPage() {
		return "contact";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String getContactWithChild(@RequestParam Map<String, String> requestParams, Model model) {
		Long id = Long.parseLong(requestParams.get("id"));
		Contact contact = contactService.getContactById(id);
		model.addAttribute("contact", contact);
		return "contact";
	}
	
	@RequestMapping(value = "/addchild", method = RequestMethod.GET)
	public String getAddChildPage() {
		return "addchild";
	}
	
	@RequestMapping(value = "/addchild", method = RequestMethod.POST)
	public String addNewChild(@RequestParam Map<String, String> requestParams, Model model) {
		Long id = Long.parseLong(requestParams.get("id"));
		String fname = requestParams.get("fname");
		int age = Integer.parseInt(requestParams.get("age"));
		
		Contact contact = contactService.addNewChild(id, new Child(fname, age));
		model.addAttribute("contact", contact);
		return "addchild";
	}
}
