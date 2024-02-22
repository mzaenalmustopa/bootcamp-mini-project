package com.demo.quickcount.controller;

import com.demo.quickcount.model.request.SurveyDetailRequest;
import com.demo.quickcount.model.request.SurveyRequest;
import com.demo.quickcount.model.response.SurveyResponse;
import com.demo.quickcount.repository.SurveyRepo;
import com.demo.quickcount.service.SurveyDetailService;
import com.demo.quickcount.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final SurveyService surveyService;
    private final SurveyDetailService detailService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("home/index");
        Map<String, SurveyResponse> map = surveyService.getMap();
        view.addObject("data1", map.get("PASLON 01"));
        view.addObject("data2", map.get("PASLON 02"));
        view.addObject("data3", map.get("PASLON 03"));

        return view;
    }

    @GetMapping("/survey")
    public ModelAndView surveyAdd(){
        ModelAndView view = new ModelAndView("home/survey-add");
        Map<String, SurveyResponse> map = surveyService.getMap();
        view.addObject("data1", map.get("PASLON 01"));
        view.addObject("data2", map.get("PASLON 02"));
        view.addObject("data3", map.get("PASLON 03"));
        return view;
    }

    @PostMapping("/survey-save")
    public ModelAndView surveySave(@ModelAttribute SurveyDetailRequest request) {
        var result = detailService.save(request).orElse(null);
        if (result != null) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("home/survey-add");
    }
}
