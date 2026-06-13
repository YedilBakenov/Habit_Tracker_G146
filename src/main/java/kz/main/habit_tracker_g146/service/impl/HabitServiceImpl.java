package kz.main.habit_tracker_g146.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.main.habit_tracker_g146.model.Habit;
import kz.main.habit_tracker_g146.repository.HabitRepository;
import kz.main.habit_tracker_g146.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final EntityManager entityManager;

    @Override
    public List<Habit> getAllHabits(String habitName, Double duration) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Habit> query = cb.createQuery(Habit.class);
        Root<Habit> root = query.from(Habit.class);

        List<Predicate> predicates = new ArrayList<>();

        if(habitName!=null && !habitName.isEmpty()){
            predicates.add(cb.like(cb.lower(root.get("habitName")), habitName));
            query.select(root).where(predicates.toArray(new Predicate[0]));

        }

        if(duration!=null){
            predicates.add(cb.equal(root.get("duration"), duration));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }


        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public Habit addHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    @Override
    public Habit getHabitById(int id) {
        return habitRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteHabit(int id) {
        habitRepository.deleteById(id);
    }

    @Override
    public Habit updateHabit(Habit habit) {
        return habitRepository.save(habit);
    }
}
