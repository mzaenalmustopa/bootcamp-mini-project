package com.bootcamp.pos.controller;

import com.bootcamp.pos.model.request.AddressRequest;
import com.bootcamp.pos.model.request.InventoryLocRequest;
import com.bootcamp.pos.model.request.ProductsRequest;
import com.bootcamp.pos.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inventory-loc")
public class InventoryLocController {

    private final InventoryLocService inventoryLocService;
    private final ProductsService productsService;
    private final AddressService addressService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view  = new ModelAndView("pages/inventory-loc/index");
        List<InventoryLocRequest> inventoryLoc = this.inventoryLocService.getAll();

        view.addObject("inventoryList", inventoryLoc);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/inventory-loc/add");
        List<ProductsRequest> products = this.productsService.getAll();
        List<AddressRequest> address = this.addressService.getAll();

        view.addObject("productList", products);
        view.addObject("addressList", address);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute InventoryLocRequest request){
        ModelAndView view = new ModelAndView("pages/inventory-loc/add");
        List<ProductsRequest> products = this.productsService.getAll();
        List<AddressRequest> address = this.addressService.getAll();

        view.addObject("productList", products);
        view.addObject("addressList", address);
        this.inventoryLocService.save(request);
        return new ModelAndView("redirect:/inventory-loc");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/inventory-loc/edit");
        InventoryLocRequest data = this.inventoryLocService.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/inventory-loc");
        }

        List<ProductsRequest> products = this.productsService.getAll();
        List<AddressRequest> address = this.addressService.getAll();

        view.addObject("productList", products);
        view.addObject("addressList", address);
        view.addObject("inventory", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update (@ModelAttribute InventoryLocRequest request){
        this.inventoryLocService.update(request, request.getId());
        return new ModelAndView("redirect:/inventory-loc");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") String id){
        this.inventoryLocService.delete(id);
        return new ModelAndView("redirect:/inventory-loc");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/inventory-loc/detail");
        InventoryLocRequest customer = this.inventoryLocService.getById(id).orElse(null);
        if (customer == null){
            return new ModelAndView("redirect:/inventory-loc");
        }

        view.addObject("inventory", customer);
        return view;
    }
}
