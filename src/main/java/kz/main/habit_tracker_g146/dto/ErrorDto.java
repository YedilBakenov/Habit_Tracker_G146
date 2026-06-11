package kz.main.habit_tracker_g146.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ErrorDtoSchema")
public class ErrorDto {

    @Schema(example = "400", description = "Код ошибки")
    private int status;

    @Schema(example = "Неправильный ввод параметров", description = "Краткое описание ошибки")
    private String message;

    @Schema(example = "Поле id неверное", description = "Подробности ошибки")
    private String details;
}
