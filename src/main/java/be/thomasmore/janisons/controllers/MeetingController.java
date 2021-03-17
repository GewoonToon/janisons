package be.thomasmore.janisons.controllers;

import be.thomasmore.janisons.model.Meeting;
import be.thomasmore.janisons.model.Project;
import be.thomasmore.janisons.repositories.MeetingRepository;
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
public class MeetingController {

@Autowired
private MeetingRepository meetingRepository;

    private Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @GetMapping("/meetinglist")
    public String projects(Model model){
        Iterable<Meeting> meetings = meetingRepository.findAll();
        model.addAttribute("meetings", meetings);
        return "meetinglist";
    }

    @GetMapping({"/meetingdetails", "/meetingdetails/{id}"})
    public String projectDetails(Model model, @PathVariable Optional<Integer> id){
        Meeting meeting = null;
        ArrayList<String> errors = new ArrayList<>();
        int meetingIndex=1;

        if(id.isPresent()){
            meetingIndex=id.get();
        }
        else{errors.add("No indexnumber found");}

        if (meetingIndex<1 || meetingIndex > meetingRepository.count()){
            errors.add("Indexnumber is not found in database");
        }

        Optional<Meeting> optionalMeeting = meetingRepository.findById(meetingIndex);

        if (errors.isEmpty() && optionalMeeting.isPresent()){
            meeting = optionalMeeting.get();}



        model.addAttribute("index", meetingIndex);
        model.addAttribute("count", meetingRepository.count());
        model.addAttribute("errors", errors);
        model.addAttribute("meeting", meeting);

        return "meetingdetails";

    }
}
