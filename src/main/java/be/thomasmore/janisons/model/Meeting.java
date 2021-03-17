package be.thomasmore.janisons.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Meeting {

    @Id
    private int id;
    private String name, bio;
    @Temporal(TemporalType.DATE)
    Date date;



    public Meeting() {
    }

    public Meeting(int id, String name, String topic, String bio, Date date) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    @ManyToMany(mappedBy = "meetings")
    private Collection<Project> projects;

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }
}
