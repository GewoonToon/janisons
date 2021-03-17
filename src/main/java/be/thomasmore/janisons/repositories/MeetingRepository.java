package be.thomasmore.janisons.repositories;

import be.thomasmore.janisons.model.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Integer> {
}
