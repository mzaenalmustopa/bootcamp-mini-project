package com.mznalmstpa.springjpa.controller;

import com.mznalmstpa.springjpa.model.CategoryModel;
import com.mznalmstpa.springjpa.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("category/index");
        // get from service
        List<CategoryModel> data = categoryService.gets();
        // send  data to view
        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("category/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CategoryModel request){
        // call save from service
        categoryService.save(request);
        // redirect to index
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("category/edit");
        CategoryModel data = categoryService.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/category");
        }
        view.addObject("data", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute CategoryModel request){
        // call update form service
        categoryService.update(request, request.getId());
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("category/detail");
        // get data from service
        CategoryModel data = categoryService.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/category");
        }
        // send data to view
        view.addObject("data", data);
        return view;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        categoryService.delete(id);
        return "redirect:/category";
    }
}
