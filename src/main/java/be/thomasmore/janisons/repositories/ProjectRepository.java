package be.thomasmore.janisons.repositories;

import be.thomasmore.janisons.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
