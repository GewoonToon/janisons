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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProjectController {

@Autowired
private ProjectRepository projectRepository;

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping({"/projectlist", "/projectlist/{optfilter}"})
    public String projects(Model model, @PathVariable Optional<String> optfilter,
                           @RequestParam(required=false) String keyword,
                           @RequestParam(required=false) Integer minDays,
                           @RequestParam(required=false) Integer maxDays,
                           @RequestParam(required=false) String filterinternal){

        logger.info(filterinternal);
        logger.info(keyword);
        logger.info(String.format("projectList -- minD%d", minDays));
        logger.info(String.format("projectList -- maxD%d", maxDays));

        ArrayList<String> errors = new ArrayList<>();
        ArrayList<Project> projects = new ArrayList<>();
        boolean filter = false;
        Boolean internal = null;

        if(optfilter.isPresent() && optfilter.get().equals("filter")){
            filter = true;
        }
        else{errors.add("Geef een filter");}

        if(filterinternal!=null && filterinternal.equals("yes")){internal = true;}
        if(filterinternal!=null && filterinternal.equals("no")){internal = false;}

        for(Project project : projectRepository.findByCriteria(minDays, maxDays, internal, keyword)){
            projects.add(project);
        }

        model.addAttribute("internal", (filterinternal==null) ? "all" : filterinternal);
        model.addAttribute("maxD", maxDays);
        model.addAttribute("minD", minDays);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filter", filter);
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
