package be.thomasmore.janisons.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Project {

    @Id
    private int id;
    private String project_name, city, info;
    private boolean internal;
    private int length_in_days;
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Meeting> meetings;

    @ManyToOne(fetch = FetchType.LAZY)
    private Leader leader;

    public Project(){
    }

    public Project(int id) {
        this.id = id;
    }

    public Project(String project_name, String city, String info, boolean internal, int length_in_days, String imageUrl) {
        this.project_name = project_name;
        this.city = city;
        this.info = info;
        this.internal = internal;
        this.length_in_days = length_in_days;
        this.imageUrl = imageUrl;
    }

    public int getId(){return id;}

    public String getProject_name() {
        return project_name;
    }

    public String getCity() {
        return city;
    }

    public String getInfo() {
        return info;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean getInternal() {
        return internal;
    }

    public int getLength_in_days() {
        return length_in_days;
    }

    public String isInternal(){
        return (internal) ? "yes" : "no";
    }



    public Collection<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Collection<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }
}
