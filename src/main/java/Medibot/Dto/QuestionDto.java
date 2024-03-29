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
public class QuestionDto {

    private int intent;
    private JSONObject entity;

    public QuestionDto(int intent, JSONObject entity){
        this.intent = intent;
        this.entity = entity;
    }

}
