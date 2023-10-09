package Medibot.Controller;

import Medibot.Dto.*;
import Medibot.KakaoRestApi;
import Medibot.Service.PillService;
import Medibot.Service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IndexController {

    private final KakaoRestApi kakaoRestApi;
    private final QuestionService questionService;
    private final PillService pillService;

    // 사용자가 입력한 문자열 받기
    @PostMapping("/question")
    public AnswerDto question(@RequestBody String question){

//        QuestionDto questionDto = questionService.getIntentEntity(question);
//        JSONObject jsonObject = questionService.getIntentEntity(question);

        List<String> array = new ArrayList<>();
        array.add("탁센");

        JSONObject entity = new JSONObject();
        entity.appendField("entity", array);

        QuestionDto questionDto = QuestionDto.builder()
                .intent(3)
                .entity(entity)
                .build();

        int intent = questionDto.getIntent();       // 의도
//        int intent = questionDto.get("intent");   // 의도

        if(intent == 0){                        // 약 주의사항

            ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");
            String pillName = rArray.get(0);

            ResPillDto resPillDto = pillService.getPill(pillName);
            JSONObject precaution = new JSONObject();
            precaution.appendField("precaution", resPillDto.getPrecaution());

            AnswerDto answerDto = new AnswerDto(intent, pillName, precaution);

            return answerDto;

        }
        else if(intent == 1){                       // 약국 정보
            ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");
            String location = rArray.get(0);

            List<HosAndPharDto> pharmacy = kakaoRestApi.getPharmacyInfo(location); // kakao api 통해 약국 정보 받아오기
            JSONObject pharmacies = new JSONObject();
            pharmacies.appendField("pharmacy", pharmacy);

            AnswerDto answerDto = new AnswerDto(intent, location, pharmacies);

            return answerDto;
        }
        else if(intent == 2){                       // 병원 정보
//            LinkedHashMap<String, List<String>> hospitalEntity = new LinkedHashMap<>();
//            hospitalEntity = (LinkedHashMap<String, List<String>>) questionDto.getEntity().get("entity");
            JSONObject hos = questionDto.getEntity();
            ArrayList<String> arrayLocation = (ArrayList<String>) hos.get("장소");
            ArrayList<String> arrayCategory = (ArrayList<String>) hos.get("병원");

            String location = arrayLocation.get(0);
            String category = arrayCategory.get(0);

            List<HosAndPharDto> hospital = kakaoRestApi.getHospitalInfo(location, category); // kakao api 통해 병원 정보 받아오기

            JSONObject hospitals = new JSONObject();
            hospitals.appendField("hospital", hospital);

            String locationAndCategory = location + " " + category;
            AnswerDto answerDto = new AnswerDto(intent, locationAndCategory, hospitals);

            return answerDto;

        }
        else if(intent == 3){                       // 부작용

            ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");
            String pillName = rArray.get(0);

            ResPillDto resPillDto = pillService.getPill(pillName);
            JSONObject sideEffect = new JSONObject();
            sideEffect.appendField("sideEffect", resPillDto.getSideEffect());

            AnswerDto answerDto = new AnswerDto(intent, pillName, sideEffect);

            return answerDto;
        }
        else if(intent == 4){                       // 효과

            ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");
            String pillName = rArray.get(0);

            ResPillDto resPillDto = pillService.getPill(pillName);
            JSONObject efcyQesitm = new JSONObject();
            efcyQesitm.appendField("efcyQesitm", resPillDto.getEfcyQesitm());

            AnswerDto answerDto = new AnswerDto(intent, pillName, efcyQesitm);

            return answerDto;
        }
        else if(intent == 5){                       // 복용 방법

            ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");
            String pillName = rArray.get(0);

            ResPillDto resPillDto = pillService.getPill(pillName);
            JSONObject method = new JSONObject();
            method.appendField("method", resPillDto.getMethod());

            AnswerDto answerDto = new AnswerDto(intent, pillName, method);

            return answerDto;
        }
        return new AnswerDto();
    }

    // string 형태의 위치를 정확한 좌표로 바꾸기
//    @GetMapping(value = "/place")
//    public CoordinateDto getKeywordLocation(@RequestBody String place){
//       return kakaoRestApi.getCoordinate(place);
//    }

    // 키워드 + 위치 기반 병원 정보
    @PostMapping("/hospital")
    public List<HosAndPharDto> getHospital(@RequestBody ReqHospitalDto reqHospitalDto){
        return kakaoRestApi.getHospitalInfo(reqHospitalDto.getPlace(), reqHospitalDto.getCategory());
    }

    @PostMapping("/pharmacy")
    public List<HosAndPharDto> getPharmacy(@RequestBody String place){
        return kakaoRestApi.getPharmacyInfo(place);
    }

    @PostMapping("/effect")
    public ResPillDto getEffect(@RequestBody String pillName){
        return pillService.getPill(pillName);
    }

//    @GetMapping("/test")
//    public JSONObject test(){
//        AnswerDto answerDto = new AnswerDto(0, "게보린", "게보린의 주의사항에 대한 답변입니다.");
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            //Object to JSON in String
//            String jsonInString = mapper.writeValueAsString(answerDto);
//            System.out.println(jsonInString);
//
//            JSONParser parser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) parser.parse(jsonInString);
//
//            return jsonObject;
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//        return new JSONObject();
//    }

    @ResponseBody
    @GetMapping(value = "/test2", produces = "application/json;charset=UTF-8")
    public void test2(@RequestBody QuestionDto object){
//    public String test2(@RequestBody QuestionDto object){
        JSONObject entity = new JSONObject();


//        if (object.get("intent").equals("0")){
//            // 약 주의사항, 부작용, 약국 등등
//            ArrayList<String> rArray = new ArrayList<>();
//            rArray = (ArrayList<String>) object.get("entity");
//            String pillName = rArray.get(0);
//            return pillName;
//        }
//        else{
            // 병원
        JSONObject hospital = object.getEntity();
        ArrayList<String> location = (ArrayList<String>) hospital.get("장소");
        ArrayList<String> category = (ArrayList<String>) hospital.get("병원");
        System.out.println(location.get(0));
        System.out.println(location.get(0).getClass());


//        }

    }


}