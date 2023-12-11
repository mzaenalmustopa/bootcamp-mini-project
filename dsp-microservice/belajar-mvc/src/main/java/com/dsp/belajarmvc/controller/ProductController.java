package com.dsp.belajarmvc.controller;

import com.dsp.belajarmvc.model.request.ProductRequest;
import com.dsp.belajarmvc.model.response.CategoryResponse;
import com.dsp.belajarmvc.model.response.ProductResponse;
import com.dsp.belajarmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/product/index");
        List<ProductResponse> data = this.service.getAll();
        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/product/add");
        List<CategoryResponse> ProductList = this.service.getCategory();
        view.addObject("categoryList", ProductList);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute ProductRequest request){
        Optional<ProductResponse> result = service.save(request);
        if(result.isEmpty()){
            ModelAndView view = new ModelAndView("pages/product/add");
            List<CategoryResponse> categoryList = this.service.getCategory();

            view.addObject("categoryList", categoryList);
            view.addObject("error", "Save failed");
            return view;
        }
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ProductResponse result = service.getById(id).orElse(null);

        if(result == null){
            return new ModelAndView("redirect:/product");
        }
        ModelAndView view = new ModelAndView("pages/product/edit");

        List<CategoryResponse> categoryList = this.service.getCategory();
        view.addObject("data", result);
        view.addObject("categoryList", categoryList);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute ProductRequest request){
        Optional<ProductResponse> result = service.update(request, request.getId());
        if(result.isEmpty()){
            ModelAndView view = new ModelAndView("pages/product/edit");
            view.addObject("error", "Save failed");

            List<CategoryResponse> categoryList = this.service.getCategory();
            view.addObject("categoryList", categoryList);
            view.addObject("data", result);
            return view;
        }
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        service.delete(id).orElse(null);
        return new ModelAndView("redirect:/product");
    }
}
