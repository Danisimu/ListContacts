package com.example.ListContacts;

import com.example.ListContacts.ContactsService.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class MyController {

    private final ContactsService contactsService;


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("contacts", contactsService.findAll());
        return "index";
    }

    @GetMapping("/contact/create")
    public String showCreateForm(Model model){
        model.addAttribute("contact", new Contact());

        return "create";
    }

    @PostMapping("/contact/create")
    public String createContact(@ModelAttribute Contact contact){
        contact.setId(System.currentTimeMillis());
        contactsService.save(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id){
        contactsService.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/contact/edit/{id}")
    public String showEditForm(@PathVariable Long id,Model model){
        Contact contact = contactsService.findById(id);
        if (contact != null){
            model.addAttribute("contact", contact);
            return "edit";
        }

        return "redirect:/";
    }


    @PostMapping("/contact/edit")
    public String editContact(@ModelAttribute Contact contact){
        contactsService.update(contact);
        return "redirect:/";
    }


}
