package com.bootcamp.controller;

import com.bootcamp.model.KelasModel;
import com.bootcamp.service.KelasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/kelas")
public class KelasController {

    private KelasService kelasService;

    public KelasController(KelasService kelasService) {
        this.kelasService = kelasService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("kelas/index");
        List<KelasModel> data = kelasService.getAll();

        view.addObject("kelasList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("kelas/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute KelasModel request){
        this.kelasService.save(request);
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        KelasModel model = this.kelasService.getById(id);
        if (model ==null){
            return new ModelAndView("redirect:/kelas");
        }

        ModelAndView view = new ModelAndView("kelas/edit");
        view.addObject("kelas", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute KelasModel request){
        this.kelasService.update(request.getId(), request);
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        KelasModel model = this.kelasService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/kelas");
        }

        ModelAndView view = new ModelAndView("kelas/detail");
        view.addObject("kelas", model);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@ModelAttribute KelasModel request){
        KelasModel model = kelasService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/kelas");
        }

        this.kelasService.delete(request.getId());
        return new ModelAndView("redirect:/kelas");
    }
}
