package com.bootcamp.northwind.controller;

import com.bootcamp.northwind.model.request.CustomerRequest;
import com.bootcamp.northwind.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/customer/index");
        List<CustomerRequest> customer = this.customerService.getAll();

        view.addObject("customerList", customer);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/customer/add");
    }

    @GetMapping("/add-modal")
    public ModelAndView addModal(){
        return new ModelAndView("pages/customer/_addPartial");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CustomerRequest request){
        this.customerService.save(request);
        return new ModelAndView("redirect:/customer");
    }
}
