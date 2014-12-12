package net.eduwill.prospring.ch17.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.eduwill.prospring.ch17.domain.Contact;
import net.eduwill.prospring.ch17.service.ContactService;
import net.eduwill.prospring.ch17.web.form.Message;

import org.apache.tiles.util.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.jndi.toolkit.url.UrlUtil;

@RequestMapping(value="/contacts")
@Controller
public class ContactController {

	private final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model uiModel) {
		
		logger.info("Listing contacts");
		
		List<Contact> contacts = contactService.findAll();
		
		uiModel.addAttribute("contacts",  contacts);
		
		logger.info("No. of contacts:" + contacts.size());
		return "contacts/list";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		Contact contact = contactService.findById(id);
		uiModel.addAttribute("contact", contact);
		
		return "contacts/view";
	}
	
	/*
	 * BindingResult나 Error등은 해당 유효체크할 파라미터 바로 뒤에 나와야한다. 엉뚱한데 두지 말자.
	 * 그러니 아래에서는 Contact contact의 유효성 체크만 하는 것..
	 * 
	 * Model은 ModelMap과 같다고 보면 된다. 정확히는 Model은 인터페이스, ModelMap은 클래스.
	 */
	@RequestMapping(value="/{id}", params = "form", method=RequestMethod.POST)
	public String update(@Valid Contact contact, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		
		logger.info("Updating contact");
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("contact_save_fail",  new Object[]{}, locale)));
			uiModel.addAttribute("contact", contact);
			return "contacts/update";
		}
		
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("contact_save_success",  new Object[]{}, locale)));
		contactService.save(contact);
		
		return "redirect:/contacts/" + net.eduwill.prospring.ch17.web.util.UrlUtil.encodeUrlPathSegment(contact.getId().toString(), httpServletRequest);
		
		
	}
	
	@RequestMapping(value="/{id}", params = "form", method=RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("contact", contactService.findById(id));
		
		return "contacts/update";
	}
	
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid Contact contact, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		logger.info("Creating contact");
		
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("contact_save_fail", new Object[]{}, locale)));
			uiModel.addAttribute("contact", contact);
			
			return "contacts/create";
		}
		
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("contact_save_success", new Object[]{}, locale)));
		logger.info("contact id: " + contact.getId());
		
		contactService.save(contact);
		return "redirect:/contacts/" + net.eduwill.prospring.ch17.web.util.UrlUtil.encodeUrlPathSegment(contact.getId().toString(), httpServletRequest);
	}
	
	@RequestMapping(params = "form", method=RequestMethod.GET)
	public String createForm(Model uiModel) {
		Contact contact = new Contact();
		uiModel.addAttribute("contact", contact);
		
		return "contacts/create";
	}
}
