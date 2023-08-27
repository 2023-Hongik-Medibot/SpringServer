package Medibot.Dto;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.*;

import java.util.List;

@Data
@Getter
@Builder
@NoArgsConstructor
public class AIQuestionDto {

    private int intent;
    private JSONObject entity;

    public AIQuestionDto(int intent, JSONObject entity){
        this.intent = intent;
        this.entity = entity;
    }

}
