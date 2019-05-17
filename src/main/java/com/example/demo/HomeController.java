package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller

public class HomeController {

    @Autowired
    MessagesRepository messagesRepository;

    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("messages", messagesRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String messageform(Model model){
        model.addAttribute("message", new Message());
        return"messageform";
    }
    @PostMapping("/processform")
    public String processmessageform(@Valid Message message, BindingResult result){
        if(result.hasErrors()){
            return "messageform";
        }
        messagesRepository.save(message);
        return "redirect:/";
    }
    @RequestMapping("/view/{id}")
    public String showMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messagesRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messagesRepository.findById(id).get());
        return "messageform";
    }
    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id){
       messagesRepository.deleteById(id);
       return "redirect:/";
    }

}
