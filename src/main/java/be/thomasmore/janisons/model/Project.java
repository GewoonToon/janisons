package be.thomasmore.janisons.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {

    @Id
    private int id;
    private String project_name, city, info;
    private boolean internal;
    private int length_in_days;

    public Project(){

    }

    public Project(String project_name, String city, String info, boolean internal, int length_in_days) {
        this.project_name = project_name;
        this.city = city;
        this.info = info;
        this.internal = internal;
        this.length_in_days = length_in_days;
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

    public boolean getInternal() {
        return internal;
    }

    public int getLength_in_days() {
        return length_in_days;
    }

    public String isInternal(){
        return (internal) ? "yes" : "no";
    }
}
