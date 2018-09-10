package org.ajc.project.child.config;

import org.ajc.project.child.dao.ContactRepository;
import org.ajc.project.child.model.Child;
import org.ajc.project.child.model.Contact;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	private final ContactRepository contactRepository;

	public SetupDataLoader(ContactRepository contactRepository) {
		super();
		this.contactRepository = contactRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;

		Child child = new Child("Leo", 5);
		Contact mama = new Contact("Xin", "WANG", "wangxin@gmail.com", "Maman", 36);
		mama.addChild(child);
		
		contactRepository.save(mama);
		alreadySetup = true;
	}
}
