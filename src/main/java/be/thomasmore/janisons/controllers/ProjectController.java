package be.thomasmore.janisons.controllers;

import be.thomasmore.janisons.model.Project;
import be.thomasmore.janisons.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProjectController {

@Autowired
private ProjectRepository projectRepository;

    @GetMapping("/projectlist")
    public String projects(Model model){
        Iterable<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projectlist";
    }

    @GetMapping({"/projectdetails", "/projectdetails/{optionalIndex}"})
    public String projectDetails(Model model, @PathVariable Optional<Integer> optionalIndex){
        Project project = null;
        ArrayList<String> errors = new ArrayList<>();
        int projectIndex=1;

        if(optionalIndex.isPresent()){
            projectIndex=optionalIndex.get();
        }
        else{errors.add("No indexnumber found");}

        if (projectIndex<1 || projectIndex > projectRepository.count()){
            errors.add("Indexnumber is not found in database");
        }

        if(errors.isEmpty() && projectRepository.findById(projectIndex).isPresent()){
            project= projectRepository.findById(projectIndex).get();
        }

        model.addAttribute("index", projectIndex);
        model.addAttribute("count", projectRepository.count());
        model.addAttribute("errors", errors);
        model.addAttribute("project", project);

        return "projectdetails";

    }
}
