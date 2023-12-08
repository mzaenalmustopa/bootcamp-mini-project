package com.bootcamp.northwind.controller;

import com.bootcamp.northwind.model.request.CategoryRequest;
import com.bootcamp.northwind.model.request.SupplierRequest;
import com.bootcamp.northwind.service.CategoryService;
import com.bootcamp.northwind.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/category/index");
        List<CategoryRequest> category = this.categoryService.getAll();

        view.addObject("categoryList", category);
        return view;
    }

    @GetMapping("/add-modal")
    public ModelAndView addModal(){
        ModelAndView view = new ModelAndView("pages/category/_addPartial");
        List<SupplierRequest> supplier = this.supplierService.getAll();

        view.addObject("supplierList", supplier);
        return view;
    }

    @GetMapping("/add-products/{index}")
    public ModelAndView addProducts(@PathVariable("index") int index){
        ModelAndView view = new ModelAndView("pages/category/_addProducts");
        List<SupplierRequest> supplier = this.supplierService.getAll();

        view.addObject("supplierList", supplier);
        view.addObject("index", index);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CategoryRequest request){
        this.categoryService.save(request);
        return new ModelAndView("redirect:/category");
    }
}
