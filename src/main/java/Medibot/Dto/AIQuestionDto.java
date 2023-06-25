package Medibot.Dto;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AIQuestionDto {

    private int intent;
    private JSONObject entity;

    public AIQuestionDto(int intent, JSONObject entity){
        this.intent = intent;
        this.entity = entity;
    }

}
