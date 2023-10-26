package com.bootcamp.controller;

import com.bootcamp.entity.LookUpEntity;
import com.bootcamp.model.DosenModel;
import com.bootcamp.model.KelasModel;
import com.bootcamp.model.MatakuliahModel;
import com.bootcamp.model.RuangModel;
import com.bootcamp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/kelas")
@RequiredArgsConstructor
public class KelasController {

    // inject from service
    private final KelasService kelasService;
    private final RuangService ruangService;
    private final MatakuliahService matakuliahService;
    private final DosenService dosenService;
    private final LookupService lookupService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/kelas/index");
        List<KelasModel> data = kelasService.getAll();

        view.addObject("kelasList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView andView = new ModelAndView("pages/kelas/add");
        List<RuangModel> ruang = ruangService.getAll();
        List<DosenModel> dosen = dosenService.getAll();
        List<MatakuliahModel> matakuliah = matakuliahService.getAll();

        andView.addObject("hariList",lookupService.getByGroups("HARI"));
        andView.addObject("semseterList",lookupService.getByGroups("SEMESTER"));
        andView.addObject("statusList",lookupService.getByGroups("ONLINE"));
        andView.addObject("onlineList",lookupService.getByGroups("BISAONLINE"));
        andView.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        andView.addObject("ruangList", ruang);
        andView.addObject("dosenList", dosen);
        andView.addObject("matakuliahList", matakuliah);
        andView.addObject("kelasList", new KelasModel());
        return andView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute KelasModel request){
        ModelAndView view = new ModelAndView("pages/kelas/add");

        view.addObject("hariList",lookupService.getByGroups("HARI"));
        view.addObject("semseterList",lookupService.getByGroups("SEMESTER"));
        view.addObject("statusList",lookupService.getByGroups("ONLINE"));
        view.addObject("onlineList",lookupService.getByGroups("BISAONLINE"));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        this.kelasService.save(request);
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        KelasModel model = this.kelasService.getById(id);
        if (model ==null){
            return new ModelAndView("redirect:/kelas");
        }

        List<RuangModel> ruang = this.ruangService.getAll();
        List<DosenModel> dosen = this.dosenService.getAll();
        List<MatakuliahModel> matkul = this.matakuliahService.getAll();

        ModelAndView view = new ModelAndView("pages/kelas/edit");
        view.addObject("hariList",lookupService.getByGroups("HARI"));
        view.addObject("semseterList",lookupService.getByGroups("SEMESTER"));
        view.addObject("statusList",lookupService.getByGroups("ONLINE"));
        view.addObject("onlineList",lookupService.getByGroups("BISAONLINE"));
        view.addObject("byPosition", Comparator.comparing(LookUpEntity::getPosition));
        view.addObject("kelas", model);
        view.addObject("ruangList", ruang);
        view.addObject("dosenList", dosen);
        view.addObject("matakuliahList", matkul);
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

        ModelAndView view = new ModelAndView("pages/kelas/detail");
        view.addObject("kelas", model);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute KelasModel request){
        KelasModel model = kelasService.getById(request.getId());
        if (model == null){
            return new ModelAndView("redirect:/kelas");
        }

        this.kelasService.delete(request.getId());
        return new ModelAndView("redirect:/kelas");
    }
}
