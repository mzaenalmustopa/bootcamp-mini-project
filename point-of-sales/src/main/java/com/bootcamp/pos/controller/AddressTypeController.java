package com.bootcamp.pos.controller;

import com.bootcamp.pos.model.request.AddressTypeRequest;
import com.bootcamp.pos.service.AddressTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address-type")
public class AddressTypeController {

    private final AddressTypeService AddressTypeService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view  = new ModelAndView("pages/address-type/index");
        List<AddressTypeRequest> address = this.AddressTypeService.getAll();

        view.addObject("addressTypeList", address);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/address-type/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("address-type") AddressTypeRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/address-type/add");

        if (result.hasErrors()){
            view.addObject("address-type", request);
        }

        this.AddressTypeService.save(request);
        return new ModelAndView("redirect:/address-type");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/address-type/edit");
        AddressTypeRequest data = this.AddressTypeService.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/address-type");
        }

        view.addObject("addressType", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update (@ModelAttribute AddressTypeRequest request){
        this.AddressTypeService.update(request, request.getId());
        return new ModelAndView("redirect:/address-type");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") String id){
        this.AddressTypeService.delete(id);
        return new ModelAndView("redirect:/address-type");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/address-type/detail");
        AddressTypeRequest address = this.AddressTypeService.getById(id).orElse(null);
        if (address == null){
            return new ModelAndView("redirect:/address-type");
        }

        view.addObject("addressType", address);
        return view;
    }
}
