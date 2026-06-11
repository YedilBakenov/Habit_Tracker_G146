package kz.main.habit_tracker_g146.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "habits", schema = "g146")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String habitName;

    private int frequency;

    private Double duration;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @PrePersist
    public void create(){
        createdAt = LocalDate.now();
    }

    @PreUpdate
    public void update(){
        updatedAt = LocalDate.now();
    }


}
