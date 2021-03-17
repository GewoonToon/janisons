package be.thomasmore.janisons.repositories;

import be.thomasmore.janisons.model.Leader;
import org.springframework.data.repository.CrudRepository;

public interface LeaderRepository extends CrudRepository<Leader, Integer> {
}
