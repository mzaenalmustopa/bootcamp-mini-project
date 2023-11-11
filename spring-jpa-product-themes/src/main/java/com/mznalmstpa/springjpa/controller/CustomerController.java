package com.mznalmstpa.springjpa.controller;

import com.mznalmstpa.springjpa.entity.CountryEntity;
import com.mznalmstpa.springjpa.entity.CustomerEntity;
import com.mznalmstpa.springjpa.model.CustomerAddressModel;
import com.mznalmstpa.springjpa.model.CustomerModel;
import com.mznalmstpa.springjpa.service.CountryService;
import com.mznalmstpa.springjpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;
    private final CountryService countryService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/customer/index");
        //get from service
        List<CustomerModel> data = service.gets();
        // send data to view
        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/customer/add");
        List<CountryEntity> country = countryService.getCountry();
        // send country to view
        view.addObject("country", country);
        return view;
    }

    @GetMapping("/add-address/{index}")
    public ModelAndView addCustomer(@PathVariable("index") int index){
        ModelAndView view = new ModelAndView("pages/customer/_address");
        view.addObject("index", index);

        List<CountryEntity> country = countryService.getCountry();
        // send country to view;
        view.addObject("country", country);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CustomerModel request){
        // call save from service
        service.save(request);
        //redirect to index
        return new ModelAndView("redirect:/customer");
    }

    // edit method get
    @GetMapping("/edit/{id}")
    public ModelAndView edit (@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/customer/edit");
        CustomerModel data = service.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/customer");
        }
        view.addObject("data",data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute CustomerModel request){
        // call update form service
       service.update(request, request.getId());
       return new ModelAndView("redirect:/customer");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("pages/customer/detail");
        // get data from service
        CustomerModel data = service.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/customer");
        }
        // send data to view
        view.addObject("data", data);
        return view;
    }

    /**
     * address custom
     */

    @GetMapping("/address/new/{id}")
    public ModelAndView addressNew(@PathVariable("id")Long id){
        ModelAndView view = new ModelAndView("pages/customer/_address-new");
        List<CountryEntity> country = countryService.getCountry();
        //send to view
        view.addObject("country", country);
        view.addObject("customerId", id);
        return view;
    }

    @GetMapping("/address/save")
    public ModelAndView saveCustomerAddress(@ModelAttribute CustomerAddressModel request){
        // call save from service
        service.saveAddress(request);
        // redirect
        return new ModelAndView("redirect:/customer/edit/"+request.getCustomerId());
    }
}
