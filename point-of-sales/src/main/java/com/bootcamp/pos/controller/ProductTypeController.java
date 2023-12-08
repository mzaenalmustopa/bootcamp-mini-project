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
@RequestMapping("/product-type")
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("/pages/product-type/index");
        List<ProductTypeRequest> productType = this.productTypeService.getAll();

        view.addObject("productTypeList", productType);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/product-type/add");
    }

    @PostMapping("/save")
    public ModelAndView save (@ModelAttribute ProductTypeRequest request){
        this.productTypeService.save(request);
        return new ModelAndView("redirect:/product-type");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/product-type/edit");
        ProductTypeRequest productType = this.productTypeService.getById(id).orElse(null);
            if (productType == null){
                return new ModelAndView("redirect:/product-type");
            }

            view.addObject("productType", productType);
            return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute ProductTypeRequest request){
        this.productTypeService.update(request, request.getId());
        return new ModelAndView("redirect:/product-type");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.productTypeService.delete(id);
        return new ModelAndView("redirect:/product-type");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/product-type/detail");
        ProductTypeRequest productType = this.productTypeService.getById(id).orElse(null);
        if (productType == null){
            return new ModelAndView("redirect:/product-type");
        }

        view.addObject("productType", productType);
        return view;
    }
}
