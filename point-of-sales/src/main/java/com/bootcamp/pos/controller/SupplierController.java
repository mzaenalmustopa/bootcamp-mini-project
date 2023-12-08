package com.bootcamp.pos.controller;

import com.bootcamp.pos.model.request.SupplierRequest;
import com.bootcamp.pos.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/supplier/index");
        List<SupplierRequest> supplier = this.supplierService.getAll();

        view.addObject("supplierList", supplier);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add (){
        return new ModelAndView("pages/supplier/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute SupplierRequest request){
        this.supplierService.save(request);
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/supplier/edit");
        SupplierRequest supplier = this.supplierService.getById(id).orElse(null);
        if (supplier == null){
            return new ModelAndView("redirect:/supplier");
        }

        view.addObject("supplier", supplier);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update (@ModelAttribute SupplierRequest request){
        this.supplierService.update(request, request.getId());
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") String id){
        this.supplierService.delete(id);
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail (@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/supplier/detail");
        SupplierRequest supplier = this.supplierService.getById(id).orElse(null);
            if (supplier == null){
                return new ModelAndView("redirect:/supplier");
            }

            view.addObject("supplier", supplier);
            return view;
    }
}
