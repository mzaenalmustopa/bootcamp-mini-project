package com.bootcamp.controller;


import com.bootcamp.model.JurusanModel;
import com.bootcamp.service.JurusanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/jurusan")
public class JurusanController {

    private final JurusanService jurusanService;

    public JurusanController(JurusanService jurusanService) {
        this.jurusanService = jurusanService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("jurusan/index");
        List<JurusanModel> data = jurusanService.getAll();

        view.addObject("jurusanList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("jurusan/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute JurusanModel request){
        this.jurusanService.save(request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        JurusanModel model = this.jurusanService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/jurusan");
        }

        ModelAndView view = new ModelAndView("jurusan/edit");
        view.addObject("jurusan", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute JurusanModel request){
        this.jurusanService.update(request.getId(), request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        JurusanModel model = jurusanService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/jurusan");
        }

        ModelAndView view = new ModelAndView("jurusan/detail");
        view.addObject("jurusan", model);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@ModelAttribute JurusanModel request){
        JurusanModel model = jurusanService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/jurusan");
        }

        jurusanService.delete(request.getId());
        return new ModelAndView("redirect:/jurusan");
    }

}
