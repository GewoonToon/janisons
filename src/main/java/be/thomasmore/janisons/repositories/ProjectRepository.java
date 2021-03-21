package be.thomasmore.janisons.repositories;

import be.thomasmore.janisons.model.Leader;
import be.thomasmore.janisons.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    @Query("SELECT p from Project p where :leader is null or p.leader = :leader")
    Iterable<Project> findByLeader(@Param("leader") Leader leader);

    @Query("SELECT p FROM Project p WHERE " +
            "((:maxD is null or :maxD >= p.length_in_days) and (:minD is null or :minD <= p.length_in_days)) and " +
            "(:internal is null or :internal =(p.internal)) and " +
            "(:keyword is null or upper(p.city) LIKE upper(CONCAT('%',:keyword,'%')))")
    Iterable<Project> findByCriteria(@Param("minD") Integer minD, @Param("maxD") Integer maxD,
                                  @Param("internal")Boolean filterInternal, @Param("keyword") String keyword);
}
