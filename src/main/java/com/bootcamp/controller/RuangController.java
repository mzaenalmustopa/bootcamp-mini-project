package com.bootcamp.controller;

import com.bootcamp.model.RuangModel;
import com.bootcamp.service.RuangService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ruang")
public class RuangController {

    private RuangService ruangService;

    public RuangController(RuangService ruangService) {
        this.ruangService = ruangService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("ruang/index");
        List<RuangModel> data = ruangService.getAll();
        view.addObject("ruangList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("ruang/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute RuangModel request){
        this.ruangService.save(request);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        RuangModel model = this.ruangService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/ruang");
        }

        ModelAndView view = new ModelAndView("ruang/edit");
        view.addObject("ruang", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute RuangModel request){
        this.ruangService.update(request.getId(), request);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id")String id){
        RuangModel model = this.ruangService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/ruang");
        }

        ModelAndView view = new ModelAndView("ruang/detail");
        view.addObject("ruang", model);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@ModelAttribute RuangModel request){
        RuangModel model = ruangService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/ruang");
        }

        ruangService.delete(request.getId());
        return new ModelAndView("redirect:/ruang");
    }
}
