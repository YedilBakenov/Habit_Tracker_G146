package kz.main.habit_tracker_g146.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitDto {

    private Integer id;

    private String habitName;

    private int frequency;

    private Double duration;

    private LocalDate create;

    private LocalDate update;

}
