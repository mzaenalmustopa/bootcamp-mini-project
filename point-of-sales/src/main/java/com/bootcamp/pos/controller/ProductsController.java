package com.bootcamp.pos.controller;

import com.bootcamp.pos.model.request.ProductTypeRequest;
import com.bootcamp.pos.model.request.ProductsRequest;
import com.bootcamp.pos.service.ProductTypeService;
import com.bootcamp.pos.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    private final ProductTypeService productTypeService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/pages/products/index");
        List<ProductsRequest> products = this.productsService.getAll();

        view.addObject("productsList", products);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/products/add");
        List<ProductTypeRequest> productType = this.productTypeService.getAll();

        view.addObject("productTypeList", productType);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save (@ModelAttribute ProductsRequest request){
        ModelAndView view = new ModelAndView("pages/products/add");
        List<ProductTypeRequest> productType = this.productTypeService.getAll();

        view.addObject("productTypeList", productType);
        this.productsService.save(request);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/products/edit");
        ProductsRequest products = this.productsService.getById(id).orElse(null);
            if (products == null){
                return new ModelAndView("redirect:/products");
            }

            List<ProductTypeRequest> productType = this.productTypeService.getAll();

            view.addObject("productTypeList", productType);
            view.addObject("products", products);
            return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute ProductsRequest request){
        this.productsService.update(request, request.getId());
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.productsService.delete(id);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/products/detail");
        ProductsRequest products = this.productsService.getById(id).orElse(null);
        if (products == null){
            return new ModelAndView("redirect:/products");
        }

        view.addObject("products", products);
        return view;
    }
}
