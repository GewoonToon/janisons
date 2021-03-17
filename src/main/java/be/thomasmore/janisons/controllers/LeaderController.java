package be.thomasmore.janisons.controllers;

import be.thomasmore.janisons.model.Leader;
import be.thomasmore.janisons.model.Project;
import be.thomasmore.janisons.repositories.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class LeaderController {

    @Autowired
    private LeaderRepository leaderRepository;

    @GetMapping({"/leaderdetails", "/leaderdetails/{id}"})
    public String projectDetails(Model model, @PathVariable Optional<Integer> id){
        Leader leader = null;
        ArrayList<String> errors = new ArrayList<>();
        int leaderIndex=1;

        if(id.isPresent()){
            leaderIndex=id.get();
        }
        else{errors.add("No indexnumber found");}

        if (leaderIndex<1 || leaderIndex > leaderRepository.count()){
            errors.add("Indexnumber is not found in database");
        }

        Optional<Leader> optionalLeader = leaderRepository.findById(leaderIndex);

        if (errors.isEmpty() && optionalLeader.isPresent()){
            leader = optionalLeader.get();}


        model.addAttribute("index", leaderIndex);
        model.addAttribute("errors", errors);
        model.addAttribute("leader", leader);

        return "leaderdetails";

    }

}
