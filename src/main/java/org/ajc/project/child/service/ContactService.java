package org.ajc.project.child.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.ajc.project.child.dao.ContactRepository;
import org.ajc.project.child.model.Child;
import org.ajc.project.child.model.Contact;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContactService {

	private final ContactRepository contactRepository;

	public ContactService(ContactRepository contactRepository) {
		super();
		this.contactRepository = contactRepository;
	}

	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	public Contact getContactById(final Long id) {
		Contact contact = contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		contact.getChildren().size();
		return contact;
	}

	public Contact addNewChild(final Long id, final Child child) {
		Contact contact = contactRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		contact.addChild(child);
		contact.getChildren().size();
		return contact;
	}
}
