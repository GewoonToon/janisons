package be.thomasmore.janisons.controllers;

import be.thomasmore.janisons.model.Meeting;
import be.thomasmore.janisons.model.Project;
import be.thomasmore.janisons.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping("/projectlist")
    public String projects(Model model){
        Iterable<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projectlist";
    }

    @GetMapping({"/projectdetails", "/projectdetails/{id}"})
    public String projectDetails(Model model, @PathVariable Optional<Integer> id){
        Project project = null;
        ArrayList<String> errors = new ArrayList<>();
        int projectIndex=1;

        if(id.isPresent()){
            projectIndex=id.get();
        }
        else{errors.add("No indexnumber found");}

        if (projectIndex<1 || projectIndex > projectRepository.count()){
            errors.add("Indexnumber is not found in database");
        }

        Optional<Project> optionalProject = projectRepository.findById(projectIndex);

        if (errors.isEmpty() && optionalProject.isPresent()){
            project = optionalProject.get();}


        model.addAttribute("index", projectIndex);
        model.addAttribute("count", projectRepository.count());
        model.addAttribute("errors", errors);
        model.addAttribute("project", project);

        return "projectdetails";

    }
}
