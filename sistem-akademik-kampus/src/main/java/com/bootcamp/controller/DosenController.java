package com.bootcamp.controller;

import com.bootcamp.entity.LookUpEntity;
import com.bootcamp.model.DosenModel;
import com.bootcamp.service.DosenService;
import com.bootcamp.service.LookupService;
import com.bootcamp.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/dosen")
public class DosenController {

    private final DosenService dosenService;
    private final LookupService lookupService;

    public DosenController(DosenService dosenService, LookupService lookupService) {
        this.dosenService = dosenService;
        this.lookupService = lookupService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/dosen/index");
        List<DosenModel> data = dosenService.getAll();

        view.addObject("dosenList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/dosen/add");
        view.addObject("genderList", lookupService.getByGroups(Constants.GENDER));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute DosenModel request){
        ModelAndView view = new ModelAndView("pages/dosen/add");
        view.addObject("genderList", lookupService.getByGroups(Constants.GENDER));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        view.addObject("dosen", request);
        this.dosenService.save(request);
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        DosenModel model = this.dosenService.getById(id);
        if (model == null){
            return new ModelAndView("redirect:/dosen");
        }

        ModelAndView view = new ModelAndView("pages/dosen/edit");
        view.addObject("genderList", lookupService.getByGroups(Constants.GENDER));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
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

        ModelAndView view = new ModelAndView("pages/dosen/detail");
        view.addObject("dosen",model);
        return view;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@ModelAttribute DosenModel request){
        DosenModel model = dosenService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/dosen");
        }

        dosenService.delete(request.getId());
        return new ModelAndView("redirect:/dosen");
    }
}
