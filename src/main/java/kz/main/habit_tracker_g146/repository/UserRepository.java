package kz.main.habit_tracker_g146.repository;

import kz.main.habit_tracker_g146.model.Habit;
import kz.main.habit_tracker_g146.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
