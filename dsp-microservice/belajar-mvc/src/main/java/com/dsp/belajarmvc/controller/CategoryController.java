package com.dsp.belajarmvc.controller;

import com.dsp.belajarmvc.model.request.CategoryRequest;
import com.dsp.belajarmvc.model.response.CategoryResponse;
import com.dsp.belajarmvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView get(){
        List<CategoryResponse> data = service.getAll();
        ModelAndView view = new ModelAndView("pages/category/index");
        // send data to view
        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/category/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CategoryRequest request){
        Optional<CategoryResponse> result = service.save(request);
        if(result.isEmpty()){
            ModelAndView view = new ModelAndView("pages/category/add");
            view.addObject("error", "Save failed");
            return view;
        }
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        CategoryResponse result = service.getById(id).orElse(null);
        if(result == null){
            return new ModelAndView("redirect:/category");
        }

        ModelAndView view = new ModelAndView("pages/category/edit");
        view.addObject("data", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute CategoryRequest request){
        Optional<CategoryResponse> result = service.update(request, request.getId());
        if(result.isEmpty()){
            ModelAndView view = new ModelAndView("pages/category/edit");
            view.addObject("error", "Save failed");
            view.addObject("data", result);
            return view;
        }
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        service.delete(id).orElse(null);
        return new ModelAndView("redirect:/category");
    }
}
