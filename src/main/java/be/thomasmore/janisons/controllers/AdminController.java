package be.thomasmore.janisons.controllers;

import be.thomasmore.janisons.model.Leader;
import be.thomasmore.janisons.model.Project;
import be.thomasmore.janisons.repositories.LeaderRepository;
import be.thomasmore.janisons.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    LeaderRepository leaderRepository;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);


    @GetMapping("/projectedit/{id}")
    public String projectedit(Model model, @PathVariable Optional<Integer> id){
        ArrayList<String> errors = new ArrayList<>();
        int projectIndex=1;
        if(id.isPresent()){
            projectIndex = id.get();
        }

        else{errors.add("Geef een nummer");}

        if (projectIndex<1 || projectIndex > projectRepository.count()){
            errors.add("Geef een nummer dat bestaat");
        }

        model.addAttribute("leaders", leaderRepository.findAll());
        model.addAttribute("errors", errors);
        return "admin/projectedit";
    }

    @PostMapping("/projectedit/{id}")
    public String projectEditPost(Model model, @PathVariable Optional<Integer> id,
                                @ModelAttribute Project project,
                                @RequestParam Integer leaderId){
        logger.info("projectEditPost "+id);

        int projectIndex=1;

        if(id.isPresent()){
            projectIndex = id.get();
        }

        if(leaderId!=null && leaderId != project.getLeader().getId()){
            project.setLeader(new Leader(leaderId));
        }

        projectRepository.save(project);

        return "redirect:/projectdetails/"+projectIndex;
    }

    @GetMapping("/projectadd")
    public String projectAdd(Model model){
        ArrayList<String> errors = new ArrayList<>();
        model.addAttribute("errors", errors);
        model.addAttribute("project", new Project());
        model.addAttribute("leaders", leaderRepository.findAll());

        return "admin/projectadd";
    }

    @PostMapping("/projectadd")
    public String ProjectAddPost(Model model,
                               @ModelAttribute("project") Project project,
                               @RequestParam int leaderId){
        project.setLeader(new Leader(leaderId));
        projectRepository.save(project);
        return "redirect:/projectdetails/" + project.getId();
    }

    @ModelAttribute("project")
    public Project findProject(@PathVariable (required = false) Integer id){
        if(id==null){return new Project();}
        logger.info("findproject "+id);
        Optional<Project> optionalProject =projectRepository.findById(id);
        if(optionalProject.isPresent()){
            return optionalProject.get();
        }
        return null;

    }
}
