package com.bootcamp.pos.controller;

import com.bootcamp.pos.model.request.CustomerRequest;
import com.bootcamp.pos.model.request.ShoppingBasketRequest;
import com.bootcamp.pos.service.CustomerService;
import com.bootcamp.pos.service.ShoppingBasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping-basket")
public class ShoppingBasketController {

    private final ShoppingBasketService shoppingBasketService;
    private final CustomerService customerService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view  = new ModelAndView("pages/shopping-basket/index");
        List<ShoppingBasketRequest> shoppingBasket = this.shoppingBasketService.getAll();

        view.addObject("shoppingList", shoppingBasket);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/shopping-basket/add");
        List<CustomerRequest> customer = this.customerService.getAll();

        view.addObject("customerList", customer);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute ShoppingBasketRequest request){
        ModelAndView view = new ModelAndView("pages/shopping-basket/add");
        List<CustomerRequest> customer = this.customerService.getAll();

        view.addObject("customerList", customer);
        this.shoppingBasketService.save(request);
        return new ModelAndView("redirect:/shopping-basket");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/shopping-basket/edit");
        ShoppingBasketRequest data = this.shoppingBasketService.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/shopping-basket");
        }

        List<CustomerRequest> customer = this.customerService.getAll();

        view.addObject("customerList", customer);
        view.addObject("shopping", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update (@ModelAttribute ShoppingBasketRequest request){
        this.shoppingBasketService.update(request, request.getId());
        return new ModelAndView("redirect:/shopping-basket");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") String id){
        this.shoppingBasketService.delete(id);
        return new ModelAndView("redirect:/shopping-basket");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/shopping-basket/detail");
        ShoppingBasketRequest shoppingBasket = this.shoppingBasketService.getById(id).orElse(null);
        if (shoppingBasket == null){
            return new ModelAndView("redirect:/shopping-basket");
        }

        view.addObject("shopping", shoppingBasket);
        return view;
    }
}
