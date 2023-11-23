package com.example.FirstSpringApplication.Controller;

import com.example.FirstSpringApplication.Services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @RequestMapping({"","{id}"})
    public String page(@PathVariable(required = false) Integer id,
                       Model model){
        directorService.page(model, id == null ? 0 : id - 1);
        return "director";
    }
}
