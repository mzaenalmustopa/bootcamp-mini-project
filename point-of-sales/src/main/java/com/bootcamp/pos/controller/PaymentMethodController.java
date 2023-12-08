package com.bootcamp.pos.controller;

import com.bootcamp.pos.model.request.PaymentMethodRequest;
import com.bootcamp.pos.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view  = new ModelAndView("pages/payment-method/index");
        List<PaymentMethodRequest> paymentMethod = this.paymentMethodService.getAll();

        view.addObject("paymentMethodList", paymentMethod);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("/pages/payment-method/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute PaymentMethodRequest request){
        this.paymentMethodService.save(request);
        return new ModelAndView("redirect:/payment-method");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/payment-method/edit");
        PaymentMethodRequest data = this.paymentMethodService.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/payment-method");
        }

        view.addObject("paymentMethod", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update (@ModelAttribute PaymentMethodRequest request){
        this.paymentMethodService.update(request, request.getId());
        return new ModelAndView("redirect:/payment-method");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") String id){
        this.paymentMethodService.delete(id);
        return new ModelAndView("redirect:/payment-method");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/payment-method/detail");
        PaymentMethodRequest paymentMethod = this.paymentMethodService.getById(id).orElse(null);
        if (paymentMethod == null){
            return new ModelAndView("redirect:/payment-method");
        }

        view.addObject("paymentMethod", paymentMethod);
        return view;
    }
}
