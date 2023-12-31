package com.bootcamp.controller;

import com.bootcamp.entity.LookUpEntity;
import com.bootcamp.model.KelasDetailModel;
import com.bootcamp.model.KelasModel;
import com.bootcamp.model.MahasiswaModel;
import com.bootcamp.service.KelasDetailService;
import com.bootcamp.service.KelasService;
import com.bootcamp.service.LookupService;
import com.bootcamp.service.MahasiswaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/kelasdetail")
@RequiredArgsConstructor
public class KelasDetailController {
    private final KelasDetailService kelasDetailService;
    private final KelasService kelasService;
    private final MahasiswaService mahasiswaService;
    private final LookupService lookupService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/kelasdetail/index");
        List<KelasDetailModel> kelasDetail = this.kelasDetailService.getAll();

        view.addObject("kelasDetailList", kelasDetail);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add (){
        ModelAndView view = new ModelAndView("pages/kelasdetail/add");
        List<KelasModel> kelas = this.kelasService.getAll();
        List<MahasiswaModel> mahasiswa = this.mahasiswaService.getAll();

        view.addObject("statusList",lookupService.getByGroups("ONLINE"));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        view.addObject("kelasList", kelas);
        view.addObject("mahasiswaList", mahasiswa);
        view.addObject("kelasDetailList", new KelasDetailModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute KelasDetailModel request){
        ModelAndView view = new ModelAndView("pages/kelasdetail/add");
        if (request == null){
            return new ModelAndView("redirect:/kelasdetail");
        }

        if (request.getStatus().isEmpty()){
            return new ModelAndView("redirect:/kelasdetail");
        }

        List<KelasModel> kelas = this.kelasService.getAll();
        List<MahasiswaModel> mahasiswa = this.mahasiswaService.getAll();

        view.addObject("statusList",lookupService.getByGroups("ONLINE"));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        view.addObject("kelasList", kelas);
        view.addObject("mahasiswaList", mahasiswa);
        this.kelasDetailService.save(request);
        return new ModelAndView("redirect:/kelasdetail");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        KelasDetailModel kelasDetail = this.kelasDetailService.getById(id);
        if (kelasDetail == null){
            return new ModelAndView("redirect:/kelasdetail");
        }

        List<KelasModel> kelas = this.kelasService.getAll();
        List<MahasiswaModel> mahasiswa = this.mahasiswaService.getAll();

        ModelAndView view = new ModelAndView("pages/kelasdetail/edit");
        view.addObject("statusList",lookupService.getByGroups("ONLINE"));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        view.addObject("kelasList", kelas);
        view.addObject("mahasiswaList", mahasiswa);
        view.addObject("kelasDetail", kelasDetail);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute KelasDetailModel request){
        kelasDetailService.update(request.getId(), request);
        return new ModelAndView("redirect:/kelasdetail");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        KelasDetailModel kelasDetail = this.kelasDetailService.getById(id);
        if (kelasDetail == null){
            return new ModelAndView("redirect:/kelasdetail");
        }

        ModelAndView view = new ModelAndView("pages/kelasdetail/detail");
        view.addObject("kelasDetailList", kelasDetail);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute KelasDetailModel request){
        KelasDetailModel model = this.kelasDetailService.getById(request.getId());
            if (model == null){
                return new ModelAndView("redirect:/kelasdetail");
            }

            kelasDetailService.delete(request.getId());
            return new ModelAndView("redirect:/kelasdetail");
    }
}
