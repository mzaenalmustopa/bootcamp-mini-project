package com.bootcamp.pos.controller;

import com.bootcamp.pos.model.request.AddressRequest;
import com.bootcamp.pos.model.request.SupplierLocRequest;
import com.bootcamp.pos.model.request.SupplierRequest;
import com.bootcamp.pos.service.AddressService;
import com.bootcamp.pos.service.SupplierLocService;
import com.bootcamp.pos.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/supplier-loc")
public class SupplierLocController {

    private final SupplierLocService supplierLocService;
    private final AddressService addressService;
    private final SupplierService supplierService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view  = new ModelAndView("pages/supplier-loc/index");
        List<SupplierLocRequest> supplierLoc = this.supplierLocService.getAll();

        view.addObject("supplierLocList", supplierLoc);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/supplier-loc/add");
        List<AddressRequest> address = this.addressService.getAll();
        List<SupplierRequest> supplier = this.supplierService.getAll();

        view.addObject("addressList", address);
        view.addObject("supplierList", supplier);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute SupplierLocRequest request){
        ModelAndView view = new ModelAndView("pages/supplier-loc/add");
        List<AddressRequest> address = this.addressService.getAll();
        List<SupplierRequest> supplier = this.supplierService.getAll();

        view.addObject("addressList", address );
        view.addObject("supplierList", supplier );
        this.supplierLocService.save(request);
        return new ModelAndView("redirect:/supplier-loc");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/supplier-loc/edit");
        SupplierLocRequest data = this.supplierLocService.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/supplier-loc");
        }

        List<AddressRequest> address = this.addressService.getAll();
        List<SupplierRequest> supplier = this.supplierService.getAll();

        view.addObject("addressList", address );
        view.addObject("supplierList", supplier );
        view.addObject("supplierLoc", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update (@ModelAttribute SupplierLocRequest request){
        this.supplierLocService.update(request, request.getId());
        return new ModelAndView("redirect:/supplier-loc");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") String id){
        this.supplierLocService.delete(id);
        return new ModelAndView("redirect:/supplier-loc");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/supplier-loc/detail");
        SupplierLocRequest supplierLoc = this.supplierLocService.getById(id).orElse(null);
        if (supplierLoc == null){
            return new ModelAndView("redirect:/supplier-loc");
        }

        view.addObject("supplierLoc", supplierLoc);
        return view;
    }
}
