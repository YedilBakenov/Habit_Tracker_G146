package kz.main.habit_tracker_g146.service;

import kz.main.habit_tracker_g146.model.Habit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HabitService {

    List<Habit>getAllHabits();

    Habit addHabit(Habit habit);

    Habit getHabitById(int id);

    void deleteHabit(int id);

    Habit updateHabit(Habit habit);
}
