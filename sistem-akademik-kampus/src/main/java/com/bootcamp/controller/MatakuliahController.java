package com.bootcamp.controller;

import com.bootcamp.model.MatakuliahModel;
import com.bootcamp.service.MatakuliahService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/matakuliah")
public class MatakuliahController {

    private MatakuliahService matakuliahService;

    public MatakuliahController(MatakuliahService matakuliahService) {
        this.matakuliahService = matakuliahService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/matakuliah/index");
        List<MatakuliahModel> data = matakuliahService.getAll();

        view.addObject("matakuliahList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/matakuliah/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MatakuliahModel request){
        this.matakuliahService.save(request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MatakuliahModel model = this.matakuliahService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/matakuliah");
        }

        ModelAndView view = new ModelAndView("pages/matakuliah/edit");
        view.addObject("matakuliah", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MatakuliahModel request){
        this.matakuliahService.update(request.getId(), request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        MatakuliahModel model = matakuliahService.getById(id);
        if (model == null){
            return new ModelAndView();
        }

        ModelAndView view = new ModelAndView("pages/matakuliah/detail");
        view.addObject("matakuliah", model);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute MatakuliahModel request){
        MatakuliahModel model = matakuliahService.getById(request.getId());
        if (model == null){
            return new ModelAndView();
        }

        matakuliahService.delete(request.getId());
        return new ModelAndView("redirect:/matakuliah");
    }
}
