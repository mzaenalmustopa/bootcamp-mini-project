package com.bootcamp.controller;

import com.bootcamp.entity.FakultasEntity;
import com.bootcamp.model.FakultasModel;
import com.bootcamp.service.FakultasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/fakultas")
public class FakultasController {

    private final FakultasService fakultasService;

    public FakultasController(FakultasService fakultasService) {
        this.fakultasService = fakultasService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/fakultas/index");
        List<FakultasModel> data = fakultasService.getAll();

        view.addObject("fakultasList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/fakultas/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute FakultasModel request){
        this.fakultasService.save(request);
        return new  ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        FakultasModel model = this.fakultasService.getById(id);
        if (model ==  null){
            return new ModelAndView("redirect:/fakultas");
        }

        ModelAndView view = new ModelAndView("pages/fakultas/edit");
        view.addObject("fakultas", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute FakultasModel request){
        this.fakultasService.update(request.getId(), request);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        FakultasModel model = fakultasService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/fakultas");
        }

        ModelAndView view = new ModelAndView("pages/fakultas/detail");
        view.addObject("fakultas", model);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute FakultasModel request){
        FakultasModel model = fakultasService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/fakultas");
        }

        fakultasService.delete(request.getId());
        return new ModelAndView("redirect:/fakultas");
    }
}
