package com.bootcamp.controller;

import com.bootcamp.entity.MahasiswaEntity;
import com.bootcamp.model.MahasiswaModel;
import com.bootcamp.service.MahasiswaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    private MahasiswaService mahasiswaService;

    public MahasiswaController(MahasiswaService mahasiswaService) {
        this.mahasiswaService = mahasiswaService;
    }


    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("mahasiswa/index");
        List<MahasiswaModel> data = mahasiswaService.getAll();
        view.addObject("mahasiswaList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("mahasiswa/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MahasiswaModel request){
        this.mahasiswaService.save(request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MahasiswaModel model = this.mahasiswaService.getById(id);
        if (model ==null){
            return new ModelAndView("redirect:/mahasiswa");
        }
        ModelAndView view = new ModelAndView("mahasiswa/edit");
        view.addObject("mahasiswa", model);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MahasiswaModel request){
        this.mahasiswaService.update(request.getId(), request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        MahasiswaModel model = mahasiswaService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/mahasiswa");
        }

        ModelAndView view = new ModelAndView("mahasiswa/detail");
        view.addObject("mahasiswa", model);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@ModelAttribute MahasiswaModel request){
        MahasiswaModel model = mahasiswaService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/mahasiswa");
        }

        mahasiswaService.delete(request.getId());
        return new ModelAndView("redirect:/mahasiswa");
    }
}
