package com.mznalmstpa.springjpa.controller;

import com.mznalmstpa.springjpa.model.CustomerAddressModel;
import com.mznalmstpa.springjpa.service.CustomerAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer_address")
public class CustomerAddressController {
    private final CustomerAddressService service;

    public CustomerAddressController(CustomerAddressService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("customerAddress/index");
        // get from service
        List<CustomerAddressModel> data = service.gets();
        // send data to view
        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("customerAddress/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CustomerAddressModel request){
        // call save from service
        service.save(request);
        // redirect to index
        return new ModelAndView("redirect:/customer_address");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")Long id){
        ModelAndView view = new ModelAndView("customerAddress/edit");
        CustomerAddressModel data = service.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/customer_address");
        }

        view.addObject("data", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute CustomerAddressModel request){
        // call update from service
        service.update(request, request.getId());
        return new ModelAndView("redirect:/customer_address");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id")Long id){
        ModelAndView view = new ModelAndView("customerAddress/detail");
        // get data from service
        CustomerAddressModel data = service.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/customer_address");
        }

        view.addObject("data", data);
        return view;
    }
}
