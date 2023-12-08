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
@RequestMapping("/basket-items")
public class BasketItemsController {

    private final BasketItemService basketItemService;
    private final CustomerService customerService;
    private final ShoppingBasketService shoppingBasketService;
    private final ProductsService productsService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view  = new ModelAndView("pages/basket-items/index");
        List<BasketItemsRequest> basketItem = this.basketItemService.getAll();

        view.addObject("basketItemsList", basketItem);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/basket-items/add");
        List<CustomerRequest> customer = this.customerService.getAll();
        List<ShoppingBasketRequest> shopping = this.shoppingBasketService.getAll();
        List<ProductsRequest> products = this.productsService.getAll();

        view.addObject("customerList", customer);
        view.addObject("shoppingList", shopping);
        view.addObject("productsList", products);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute BasketItemsRequest request){
        ModelAndView view = new ModelAndView("pages/basket-items/add");
        List<CustomerRequest> customer = this.customerService.getAll();
        List<ShoppingBasketRequest> shopping = this.shoppingBasketService.getAll();
        List<ProductsRequest> products = this.productsService.getAll();

        view.addObject("customerList", customer);
        view.addObject("shoppingList", shopping);
        view.addObject("productsList", products);
        this.basketItemService.save(request);
        return new ModelAndView("redirect:/basket-items");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/basket-items/edit");
        BasketItemsRequest basketItems = this.basketItemService.getById(id).orElse(null);
        if (basketItems == null){
            return new ModelAndView("redirect:/basket-items");
        }

        List<CustomerRequest> customers = this.customerService.getAll();
        List<ShoppingBasketRequest> shoppingBasket = this.shoppingBasketService.getAll();
        List<ProductsRequest> products = this.productsService.getAll();

        view.addObject("customerList", customers);
        view.addObject("shoppingList", shoppingBasket);
        view.addObject("productsList", products);
        view.addObject("basketItems", basketItems);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update (@ModelAttribute BasketItemsRequest request){
        this.basketItemService.update(request, request.getId());
        return new ModelAndView("redirect:/basket-items");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") String id){
        this.basketItemService.delete(id);
        return new ModelAndView("redirect:/basket-items");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/basket-items/detail");
        BasketItemsRequest basketItems = this.basketItemService.getById(id).orElse(null);
        if (basketItems == null){
            return new ModelAndView("redirect:/basket-items");
        }

        view.addObject("basketItems", basketItems);
        return view;
    }
}
