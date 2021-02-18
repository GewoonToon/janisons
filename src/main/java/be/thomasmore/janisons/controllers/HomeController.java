package be.thomasmore.janisons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HomeController {

    private final String[] projectNames = {"Modernising the building", "Improving work efficiency", "Removing excess data", "Making an app"};

    @GetMapping({"/", "/home"})
    public String home(Model model){
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/projectlist")
    public String projects(Model model){
        model.addAttribute("projectNames", projectNames);
        return "projectlist";
    }

    @GetMapping({"/projectdetails", "/projectdetails/{optionalIndex}"})
    public String projectDetails(Model model, @PathVariable Optional<Integer> optionalIndex){
        String projectName = null;
        ArrayList<String> errors = new ArrayList<>();
        int projectIndex=0;

        if(optionalIndex.isPresent()){
            projectIndex=optionalIndex.get();
        }
        else{errors.add("No indexnumber found");}

        if (projectIndex<0 || projectIndex > projectNames.length-1){
            errors.add("Indexnumber is not found in database");
        }

        if(errors.isEmpty()){
            projectName= projectNames[projectIndex];
        }

        model.addAttribute("index", projectIndex);
        model.addAttribute("errors", errors);
        model.addAttribute("projectName", projectName);

        return "projectdetails";

    }

}
