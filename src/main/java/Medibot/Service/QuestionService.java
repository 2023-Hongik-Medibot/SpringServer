package Medibot.Service;

import Medibot.Dto.AIQuestionDto;
import Medibot.Dto.QuestionDto;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class QuestionService {

    private static final String AI_SERVER_HOST  = "http://ec2-3-38-149-188.ap-northeast-2.compute.amazonaws.com:8000/ask/first/";

    public QuestionDto getIntentEntity(String question){
//    public JSONObject getIntentEntity(String question){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        URI url = URI.create(AI_SERVER_HOST);
        URI uri = UriComponentsBuilder
                .fromUriString(AI_SERVER_HOST)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject body = new JSONObject();
        body.put("ask", question);

        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

        resultMap.put("request url", AI_SERVER_HOST);
        resultMap.put("request body", body.toString());

        ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
        int intent = Integer.parseInt(response.getBody().get("intent").toString());

        JSONObject entities = new JSONObject();
        entities.appendField("entity", response.getBody().get("entity"));
        QuestionDto questionDto = new QuestionDto(intent, entities);
        return questionDto;

//         JSONObject jsonObject = new JSONObject();
//         jsonObject.appendField("intent", intent);
//         jsonObject.appendField("entity", entities);
//
//         return jsonObject;

    }


}
