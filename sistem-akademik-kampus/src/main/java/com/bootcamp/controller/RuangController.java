package com.bootcamp.controller;

import com.bootcamp.model.GedungModel;
import com.bootcamp.model.RuangModel;
import com.bootcamp.service.GedungService;
import com.bootcamp.service.RuangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ruang")
@RequiredArgsConstructor
public class RuangController {

    private final RuangService ruangService;
    //inject from service
    private final GedungService gedungService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/ruang/index");
        List<RuangModel> data = ruangService.getAll();

        view.addObject("ruangList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/ruang/add");
        List<GedungModel> data = gedungService.getAll();

        view.addObject("gedungList", data);
        view.addObject("ruang", new GedungModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute RuangModel request){
        ModelAndView view = new ModelAndView("pages/ruang/add");
        List<GedungModel> data = gedungService.getAll();

        view.addObject("gedungList", data);
        this.ruangService.save(request);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        RuangModel ruang = this.ruangService.getById(id);
        if (ruang == null){
            return new ModelAndView("redirect:/ruang");
        }

        List<GedungModel> gedung = gedungService.getAll();

        ModelAndView view = new ModelAndView("pages/ruang/edit");
        view.addObject("ruang", ruang);
        view.addObject("gedungList", gedung);
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

        ModelAndView view = new ModelAndView("pages/ruang/detail");
        view.addObject("ruang", model);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute RuangModel request){
        RuangModel model = ruangService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/ruang");
        }

        ruangService.delete(request.getId());
        return new ModelAndView("redirect:/ruang");
    }
}
