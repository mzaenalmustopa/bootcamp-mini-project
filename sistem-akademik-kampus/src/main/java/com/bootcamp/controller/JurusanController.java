package com.bootcamp.controller;


import com.bootcamp.model.FakultasModel;
import com.bootcamp.model.JurusanModel;
import com.bootcamp.service.FakultasService;
import com.bootcamp.service.JurusanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/jurusan")
@RequiredArgsConstructor
public class JurusanController {

    private final JurusanService jurusanService;
    // inject from service
    private final FakultasService fakultasService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/jurusan/index");
        List<JurusanModel> data = jurusanService.getAll();

        view.addObject("jurusanList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/jurusan/add");
        List<FakultasModel> result = fakultasService.getAll();

        view.addObject("fakultasList", result);
        view.addObject("jurusan", new JurusanModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute JurusanModel request){
        ModelAndView view = new ModelAndView("pages/jurusan/add");
        List<FakultasModel> data = fakultasService.getAll();

        view.addObject("fakultasList", data);
        this.jurusanService.save(request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        JurusanModel jurusan = this.jurusanService.getById(id);
        if (jurusan == null){
            return new ModelAndView("redirect:/jurusan");
        }

        List<FakultasModel> fakultas = fakultasService.getAll();

        ModelAndView view = new ModelAndView("pages/jurusan/edit");

        view.addObject("jurusan", jurusan);
        view.addObject("fakultasList", fakultas);
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

        ModelAndView view = new ModelAndView("pages/jurusan/detail");
        view.addObject("jurusan", model);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute JurusanModel request){
        JurusanModel model = jurusanService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/jurusan");
        }

        this.jurusanService.delete(request.getId());
        return new ModelAndView("redirect:/jurusan");
    }

}
