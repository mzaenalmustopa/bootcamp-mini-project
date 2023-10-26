package com.bootcamp.controller;


import com.bootcamp.entity.LookUpEntity;
import com.bootcamp.model.JurusanModel;
import com.bootcamp.model.MahasiswaModel;
import com.bootcamp.service.JurusanService;
import com.bootcamp.service.LookupService;
import com.bootcamp.service.MahasiswaService;
import com.bootcamp.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/mahasiswa")
@RequiredArgsConstructor
public class MahasiswaController {

    private final MahasiswaService mahasiswaService;
    private final JurusanService jurusanService;
    private final LookupService lookupService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/mahasiswa/index");
        List<MahasiswaModel> data = mahasiswaService.getAll();

        view.addObject("mahasiswaList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/mahasiswa/add");
        List<JurusanModel> jurusan = this.jurusanService.getAll();

        view.addObject("genderList", lookupService.getByGroups(Constants.GENDER));
        view.addObject("agamaList", lookupService.getByGroups(Constants.AGAMA));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        view.addObject("jurusanList", jurusan);
        view.addObject("mahasiswa", new MahasiswaModel());
        return view;
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
        List<JurusanModel> jurusan = this.jurusanService.getAll();

        ModelAndView view = new ModelAndView("pages/mahasiswa/edit");
        view.addObject("jurusanList", jurusan);
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

        ModelAndView view = new ModelAndView("pages/mahasiswa/detail");
        view.addObject("mahasiswa", model);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute MahasiswaModel request){
        MahasiswaModel model = mahasiswaService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/mahasiswa");
        }

        mahasiswaService.delete(request.getId());
        return new ModelAndView("redirect:/mahasiswa");
    }
}
