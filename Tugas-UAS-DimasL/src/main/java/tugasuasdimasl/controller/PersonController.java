package tugasuasdimasl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tugasuasdimasl.model.PersonModel;
import tugasuasdimasl.service.PersonService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/person/index");
        List<PersonModel> data = personService.getAll();

        view.addObject("personList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/person/add");
        return view;
    }

   @PostMapping("/save")
    public ModelAndView save(@ModelAttribute PersonModel request){
        personService.save(request);
        return new ModelAndView("redirect:/person");
    }

    @GetMapping("/delete")
    public ModelAndView delete(@ModelAttribute PersonModel request){
       Optional <PersonModel> model = personService.getById(request.getId());
       if (model == null){
           return new ModelAndView("redirect:/person");
       }

       personService.delete(request.getId());
       return new ModelAndView("redirect:/person");
    }
}
