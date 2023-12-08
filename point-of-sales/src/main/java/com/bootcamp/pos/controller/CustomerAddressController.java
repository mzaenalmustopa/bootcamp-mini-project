package com.bootcamp.pos.controller;

import com.bootcamp.pos.model.request.*;
import com.bootcamp.pos.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer-address")
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final AddressTypeService addressTypeService;
    private final SupplierLocService supplierLocService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view  = new ModelAndView("pages/customer-address/index");
        List<CustomerAddressRequest> customerAddress = this.customerAddressService.getAll();

        view.addObject("customerAddressList", customerAddress);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/customer-address/add");
        List<CustomerRequest> customer = this.customerService.getAll();
        List<AddressRequest> address = this.addressService.getAll();
        List<AddressTypeRequest> addressType = this.addressTypeService.getAll();
        List<SupplierLocRequest> supplierLoc = this.supplierLocService.getAll();

        view.addObject("customerList", customer);
        view.addObject("addressList", address);
        view.addObject("addressTypeList", addressType);
        view.addObject("supplierLocList", supplierLoc);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CustomerAddressRequest request){
        ModelAndView view = new ModelAndView("pages/customer-address/add");
        List<CustomerRequest> customer = this.customerService.getAll();
        List<AddressRequest> address = this.addressService.getAll();
        List<AddressTypeRequest> addressTypeRequests = this.addressTypeService.getAll();
        List<SupplierLocRequest> supplierLoc = this.supplierLocService.getAll();

        view.addObject("customerList", customer);
        view.addObject("addressList", address);
        view.addObject("addressTypeList", addressTypeRequests);
        view.addObject("supplierLocList", supplierLoc);
        this.customerAddressService.save(request);
        return new ModelAndView("redirect:/customer-address");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/customer-address/edit");
        CustomerAddressRequest data = this.customerAddressService.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/customer-address");
        }

        List<CustomerRequest> customer = this.customerService.getAll();
        List<AddressRequest> address = this.addressService.getAll();
        List<AddressTypeRequest> addressTypeRequests = this.addressTypeService.getAll();
        List<SupplierLocRequest> supplierLocRequests = this.supplierLocService.getAll();

        view.addObject("customerList", customer);
        view.addObject("addressList", address);
        view.addObject("addressTypeList", addressTypeRequests);
        view.addObject("supplierLocList", supplierLocRequests);
        view.addObject("customerAddress", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update (@ModelAttribute CustomerAddressRequest request){
        this.customerAddressService.update(request, request.getId());
        return new ModelAndView("redirect:/customer-address");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") String id){
        this.customerAddressService.delete(id);
        return new ModelAndView("redirect:/customer-address");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/customer-address/detail");
        CustomerAddressRequest customerAddress = this.customerAddressService.getById(id).orElse(null);
        if (customerAddress == null){
            return new ModelAndView("redirect:/customer-address");
        }

        view.addObject("customerAddress", customerAddress);
        return view;
    }
}
