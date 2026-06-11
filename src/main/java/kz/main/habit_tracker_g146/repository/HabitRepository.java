package kz.main.habit_tracker_g146.repository;

import kz.main.habit_tracker_g146.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> {
}
