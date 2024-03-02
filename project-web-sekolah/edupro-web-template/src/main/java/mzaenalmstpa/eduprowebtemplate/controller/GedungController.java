package mzaenalmstpa.eduprowebtemplate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mzaenalmstpa.eduprowebtemplate.model.request.GedungRequest;
import mzaenalmstpa.eduprowebtemplate.model.response.GedungResponse;
import mzaenalmstpa.eduprowebtemplate.model.response.Response;
import mzaenalmstpa.eduprowebtemplate.service.GedungService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/gedung")
@RequiredArgsConstructor
public class GedungController {

    private final GedungService gedungService;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pages/master/gedung/index");
    }


    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/gedung/add");
        view.addObject("gedung", new GedungRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("gedung") @Valid GedungRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/gedung/add");
        view.addObject("gedung", gedungService.getAll());

        if (result.hasErrors()){
            view.addObject("gedung", request);
            return view;
        }

        var response = gedungService.save(request);
        return new ModelAndView("redirect:/master/gedung");
    }

    @GetMapping("/edit/{kode}")
    public ModelAndView edit(@PathVariable("kode") String kode){
        ModelAndView view = new ModelAndView("pages/master/gedung/edit");
        var result = this.gedungService.getById(kode).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("gedung", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("gedung") @Valid GedungRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/gedung/edit");
        view.addObject("gedung", request);

        if (result.hasErrors()){
            view.addObject("gedung", request);
            return view;
        }

        var response = gedungService.update(request);
        return new ModelAndView("redirect:/master/gedung");
    }

    @GetMapping("/delete/{kode}")
    public ModelAndView delete(@PathVariable("kode") String kode){
        ModelAndView view = new ModelAndView("pages/master/gedung/delete");
        var result = this.gedungService.getById(kode).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("kelompok", result);
        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<GedungResponse> result = gedungService.getAll();
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(result.size())
                        .build()
        );
    }
}
