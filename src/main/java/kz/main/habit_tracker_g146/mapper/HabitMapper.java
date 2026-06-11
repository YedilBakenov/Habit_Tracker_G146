package kz.main.habit_tracker_g146.mapper;

import kz.main.habit_tracker_g146.dto.HabitDto;
import kz.main.habit_tracker_g146.model.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HabitMapper {

    @Mapping(source = "createdAt", target = "create")
    @Mapping(source = "updatedAt", target = "update")
    HabitDto toDto(Habit habit);

    @Mapping(source = "create", target = "createdAt")
    @Mapping(source = "update", target = "updatedAt")
    Habit toModel(HabitDto habitDto);

    List<HabitDto> toDtoList(List<Habit> habits);

    List<Habit> toModelList(List<HabitDto> habitDtos);
}
