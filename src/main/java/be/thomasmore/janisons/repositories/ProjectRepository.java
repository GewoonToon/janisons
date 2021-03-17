package be.thomasmore.janisons.repositories;

import be.thomasmore.janisons.model.Leader;
import be.thomasmore.janisons.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    @Query("SELECT p from Project p where :leader is null or p.leader = :leader")
    Iterable<Project> findByLeader(@Param("leader") Leader leader);
}
