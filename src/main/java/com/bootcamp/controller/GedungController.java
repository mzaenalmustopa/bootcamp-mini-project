package com.bootcamp.controller;

import com.bootcamp.model.GedungModel;
import com.bootcamp.service.GedungService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/gedung")
public class GedungController {

    private GedungService gedungService;

    public GedungController(GedungService gedungService) {
        this.gedungService = gedungService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("gedung/index");
        List<GedungModel> data = gedungService.getAll();
        view.addObject("gedungList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("gedung/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute GedungModel request){
        this.gedungService.save(request);
        return new ModelAndView("redirect:/gedung");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        GedungModel model = this.gedungService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/gedung");
        }

        ModelAndView view = new ModelAndView("gedung/edit");
        view.addObject("gedung", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute GedungModel request){
        this.gedungService.update(request.getId(), request);
        return new ModelAndView("redirect:/gedung");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        GedungModel model = this.gedungService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/gedung");
        }

        ModelAndView view = new ModelAndView("gedung/detail");
        view.addObject("gedung", model);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@ModelAttribute GedungModel request){
        GedungModel model = this.gedungService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/gedung");
        }

        gedungService.delete(request.getId());
        return new ModelAndView("redirect:/gedung");
    }
}
