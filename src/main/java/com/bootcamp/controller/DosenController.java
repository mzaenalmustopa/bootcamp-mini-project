package com.bootcamp.controller;

import com.bootcamp.model.DosenModel;
import com.bootcamp.service.DosenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/dosen")
public class DosenController {

    private DosenService dosenService;

    public DosenController(DosenService dosenService) {
        this.dosenService = dosenService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("dosen/index");
        List<DosenModel> data = dosenService.getAll();

        view.addObject("dosenList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("dosen/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute DosenModel request){
        this.dosenService.save(request);
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        DosenModel model = this.dosenService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/dosen");
        }

        ModelAndView view = new ModelAndView("dosen/edit");
        view.addObject("dosen",model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute DosenModel request){
        this.dosenService.update(request.getId(), request);
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        DosenModel model = dosenService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/dosen");
        }

        ModelAndView view = new ModelAndView("dosen/detail");
        view.addObject("dosen",model);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@ModelAttribute DosenModel request){
        DosenModel model = dosenService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/dosen");
        }

        dosenService.delete(request.getId());
        return new ModelAndView("redirect:/dosen");
    }
}
