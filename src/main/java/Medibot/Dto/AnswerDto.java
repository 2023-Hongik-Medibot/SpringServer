package Medibot.Dto;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@Builder
@NoArgsConstructor
public class AnswerDto {

    private int intent;
    private String entity;
    private JSONObject answer;

    @Builder
    public AnswerDto(int intent, String entity, JSONObject answer) {
        this.intent = intent;
        this.entity = entity;
        this.answer = answer;
    }
}
