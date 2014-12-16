package net.eduwill.prospring.ch17.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import net.eduwill.prospring.ch17.domain.Contact;
import net.eduwill.prospring.ch17.service.ContactService;
import net.eduwill.prospring.ch17.web.form.ContactGrid;
import net.eduwill.prospring.ch17.web.form.Message;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

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
	public String update(@Valid Contact contact, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) Part file) {

		logger.info("Updating contact");
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("contact_save_fail",  new Object[]{}, locale)));
			uiModel.addAttribute("contact", contact);
			return "contacts/update";
		}

		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("contact_save_success",  new Object[]{}, locale)));

		// Process file upload
		if (file != null) {
			logger.info("File name: " + file.getName());
			logger.info("File size: " + file.getSize());
			logger.info("File content type: " + file.getContentType());

			byte[] fileContent = null;

			try {
				InputStream inputStream = file.getInputStream();
				if (inputStream == null) {
					logger.info("File inputstream is null");
				} else {
					fileContent = IOUtils.toByteArray(inputStream);
					contact.setPhoto(fileContent);
				}
			} catch(IOException io) {
				logger.error("Error saving uploaded file");
			}

		}

		contactService.save(contact);

		return "redirect:/contacts/" + net.eduwill.prospring.ch17.web.util.UrlUtil.encodeUrlPathSegment(contact.getId().toString(), httpServletRequest);


	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/{id}", params = "form", method=RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("contact", contactService.findById(id));

		return "contacts/update";
	}

	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid Contact contact, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) Part file) {
		logger.info("Creating contact");

		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("contact_save_fail", new Object[]{}, locale)));
			uiModel.addAttribute("contact", contact);

			return "contacts/create";
		}

		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("contact_save_success", new Object[]{}, locale)));
		logger.info("contact id: " + contact.getId());


		// Process file upload
		if (file != null) {
			logger.info("File name: " + file.getName());
			logger.info("File size: " + file.getSize());
			logger.info("File content type: " + file.getContentType());

			byte[] fileContent = null;

			try {
				InputStream inputStream = file.getInputStream();
				if (inputStream == null) {
					logger.info("File inputstream is null");
				} else {
					fileContent = IOUtils.toByteArray(inputStream);
					contact.setPhoto(fileContent);
				}
			} catch(IOException io) {
				logger.error("Error saving uploaded file");
			}

		}
		contactService.save(contact);
		return "redirect:/contacts/" + net.eduwill.prospring.ch17.web.util.UrlUtil.encodeUrlPathSegment(contact.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") Long id) {
		Contact contact = contactService.findById(id);

		if(contact.getPhoto() != null) {
			logger.info("Downloading photo for id: {} width size {}", contact.getId(), contact.getPhoto().length);
		}

		return contact.getPhoto();
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "form", method=RequestMethod.GET)
	public String createForm(Model uiModel) {
		Contact contact = new Contact();
		uiModel.addAttribute("contact", contact);

		return "contacts/create";
	}

	@RequestMapping(value="/listgrid", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ContactGrid listGrid(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order) {

		logger.info("Listing contacts for grid with page: {}, rows: {}", page, rows);
		logger.info("Listing contacts for grid with sort: {}, order: {}", sortBy, order);

		// Process order by
		Sort sort = null;
		String orderBy = sortBy;

		if (orderBy != null && "birthDateString".equals(orderBy)) {
			orderBy = "birthDate";
		}

		if (orderBy != null && order != null) {
			if (order.equals("desc")) {
				sort = new Sort(Sort.Direction.DESC, orderBy);
			} else {
				sort = new Sort(Sort.Direction.ASC, orderBy); 
			}
		}

		PageRequest pageRequest = null;

		if(sort != null) {
			pageRequest = new PageRequest(page - 1, rows, sort);
		} else {
			pageRequest = new PageRequest(page - 1, rows);
		}

		Page<Contact> contactPage = contactService.findAllByPage(pageRequest);

		ContactGrid contactGrid = new ContactGrid();

		contactGrid.setCurrentPage(contactPage.getNumber() + 1);
		contactGrid.setTotalPages(contactPage.getTotalPages());
		contactGrid.setTotalRecords(contactPage.getTotalElements());

		contactGrid.setContactData(Lists.newArrayList(contactPage.iterator()));

		return contactGrid;
	}
}
