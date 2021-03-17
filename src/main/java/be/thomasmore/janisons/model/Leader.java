package be.thomasmore.janisons.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Leader {

    @Id
    private int id;
    private String name, position, email;

    public Leader(int id, String name, String position, String email) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.email = email;
    }

    public Leader() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }
}
