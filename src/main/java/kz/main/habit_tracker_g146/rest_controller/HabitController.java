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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

        List<Habit> habits = habitService.getAllHabits(habitName, duration);

        return ResponseEntity.ok(habitMapper.toDtoList(habits));
    }

    @GetMapping(value = "/get-habit/{id}")
    @Operation(summary = "Поиск привычки", description = "Поиск привычки по параметру 'id'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список привычек получен", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "message": "Not Found",
                                        "details": "Привычка не найдена"
                                    }
                                    """)
                    }, schema = @Schema(implementation = ErrorDto.class))
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
    ResponseEntity<HabitDto> getHabitById(@PathVariable int id){

        Habit habit = habitService.getHabitById(id);

        return ResponseEntity.ok(habitMapper.toDto(habit));
    }

    @PostMapping(value = "/add-habit")
    @Operation(summary = "Добавление привычки", description = "Добавление привычки в базу данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Привычка добавлена", content = {
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
    ResponseEntity<HabitDto> addHabit(@RequestBody HabitDto habitDto){

        Habit habit = habitService.addHabit(habitMapper.toModel(habitDto));

        return ResponseEntity.ok(habitMapper.toDto(habit));
    }


    @PutMapping(value = "/update-habit")
    @Operation(summary = "Обновление привычки", description = "Обновление привычки в базе данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка обновлена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неправильный ввод параметров", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "message": "Not Found",
                                        "details": "Привычка не найдена"
                                    }
                                    """)
                    }, schema = @Schema(implementation = ErrorDto.class))
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
    ResponseEntity<HabitDto> updateHabit(@RequestBody HabitDto habitDto){

        Habit habit = habitService.updateHabit(habitMapper.toModel(habitDto));

        return ResponseEntity.ok(habitMapper.toDto(habit));
    }


    @DeleteMapping(value = "/delete-habit")
    @Operation(summary = "Удаление привычки", description = "Удаление привычки в базе данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Привычка удалена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "message": "Not Found",
                                        "details": "Привычка не найдена"
                                    }
                                    """)
                    }, schema = @Schema(implementation = ErrorDto.class))
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
    ResponseEntity<Void> deleteHabit(@RequestParam int id){

        habitService.deleteHabit(id);

        return ResponseEntity.noContent().build();
    }



}
