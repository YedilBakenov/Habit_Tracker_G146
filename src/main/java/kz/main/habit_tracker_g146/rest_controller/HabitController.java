package kz.main.habit_tracker_g146.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.main.habit_tracker_g146.dto.ErrorDto;
import kz.main.habit_tracker_g146.dto.HabitDto;
import kz.main.habit_tracker_g146.mapper.HabitMapper;
import kz.main.habit_tracker_g146.model.Habit;
import kz.main.habit_tracker_g146.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tracker")
@RequiredArgsConstructor
@Tag(name = "Habit Tracker", description = "API для работы с трекером привычек")
public class HabitController {

    private final HabitService habitService;
    private final HabitMapper habitMapper;

    @GetMapping(value = "/habits")
    @Operation(summary = "Получение всех привычек", description = "Получение списка привычек" +
            " по персонализированным параметрам 'habitName' и 'duration'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список привычек получен", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неправильный ввод параметров", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 500,
                                        "message": "Internal error",
                                        "details": "Внутрeнняя ошибка"
                                    }
                                    """)
                    }, schema = @Schema(implementation = ErrorDto.class))
            })
    })
    ResponseEntity<List<HabitDto>> getAllHabits(@RequestParam(required = false)String habitName,
                                                @RequestParam(required = false) Double duration){

        List<Habit> habits = habitService.getAllHabits();

        return ResponseEntity.ok(habitMapper.toDtoList(habits));
    }


}
