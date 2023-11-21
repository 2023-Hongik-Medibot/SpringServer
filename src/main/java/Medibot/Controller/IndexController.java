package Medibot.Controller;

import Medibot.Dto.*;
import Medibot.Exception.NotFoundIntentException;
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
        System.out.println(question);

        // Real
        QuestionDto questionDto = questionService.getIntentEntity(question);

        //test
//        JSONObject body = new JSONObject();
//        List<String> entity = new ArrayList<>();
//        entity.add("그날엔");
//        LinkedHashMap<String, List<String>> entity = new LinkedHashMap<>();
//
//        List<String> loc = new ArrayList<>();
//        loc.add("서울대입구");
//        entity.put("장소", loc);
//        List<String> hos = new ArrayList<>();
//        hos.add("안과");
//        entity.put("병원", hos);
//        body.put("entity", entity);
//
//        QuestionDto questionDto = QuestionDto.builder()
//                .intent(0)
//                .entity(body)
//                .build();

        int intent = questionDto.getIntent();
        System.out.println("의도는 " + intent);

        try {
            if(intent == 0){                        // 약 주의사항

                ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");

                if(rArray.isEmpty()){
                    return new AnswerDto(0,null,null);
                }
                String pillName = rArray.get(0);

                ResPillDto resPillDto = pillService.getPill(pillName);
                JSONObject precaution = new JSONObject();
                precaution.appendField("precaution", resPillDto.getPrecaution());

                AnswerDto answerDto = new AnswerDto(intent, pillName, precaution);

                return answerDto;

            }
            else if(intent == 1){                       // 약국 정보
                ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");

                if(rArray.isEmpty()){
                    return new AnswerDto(1, null,null);
                }

                String location = rArray.get(0);

                List<HosAndPharDto> pharmacy = kakaoRestApi.getPharmacyInfo(location); // kakao api 통해 약국 정보 받아오기
                JSONObject pharmacies = new JSONObject();
                pharmacies.appendField("pharmacy", pharmacy);

                AnswerDto answerDto = new AnswerDto(intent, location, pharmacies);

                return answerDto;
            }
            else if(intent == 2){                       // 병원 정보
                LinkedHashMap<String, List<String>> hospitalEntity = new LinkedHashMap<>();
                hospitalEntity = (LinkedHashMap<String, List<String>>) questionDto.getEntity().get("entity");
;
                ArrayList<String> arrayLocation = (ArrayList<String>) hospitalEntity.get("장소");
                System.out.println("장소는 "+arrayLocation);
                ArrayList<String> arrayCategory = (ArrayList<String>) hospitalEntity.get("병원");
                System.out.println("병원은 "+arrayCategory);

                if(arrayLocation.isEmpty() && !arrayCategory.isEmpty()){ // 장소 개체명이 안 넘어올 때,
                    JSONObject answer = new JSONObject();
                    answer.put("hospital", 21);
                    return new AnswerDto(2, arrayCategory.get(0) ,answer);
                }
                else if(arrayCategory.isEmpty() && !arrayLocation.isEmpty()){   // 병원 개체명이 안 넘어올 때
                    JSONObject answer = new JSONObject();
                    answer.put("hospital", 22);
                    return new AnswerDto(2, arrayLocation.get(0), answer);
                }
                else if(arrayCategory.isEmpty() && arrayLocation.isEmpty()){
                    return new AnswerDto(2, null, null);
                }
                else {
                    String location = arrayLocation.get(0);
                    String category = arrayCategory.get(0);

                    List<HosAndPharDto> hospital = kakaoRestApi.getHospitalInfo(location, category); // kakao api 통해 병원 정보 받아오기

                    JSONObject hospitals = new JSONObject();
                    hospitals.appendField("hospital", hospital);

                    String locationAndCategory = location + " " + category;
                    AnswerDto answerDto = new AnswerDto(intent, locationAndCategory, hospitals);

                    return answerDto;
                }

            }
            else if(intent == 3){                       // 부작용

                ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");

                if(rArray.isEmpty()){
                    return new AnswerDto(0,null,null);
                }

                String pillName = rArray.get(0);

                ResPillDto resPillDto = pillService.getPill(pillName);
                JSONObject sideEffect = new JSONObject();
                sideEffect.appendField("sideEffect", resPillDto.getSideEffect());

                AnswerDto answerDto = new AnswerDto(intent, pillName, sideEffect);

                return answerDto;
            }
            else if(intent == 4){                       // 효과

                ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");
                if(rArray.isEmpty()){
                    return new AnswerDto(0,null,null);
                }

                String pillName = rArray.get(0);

                ResPillDto resPillDto = pillService.getPill(pillName);
                JSONObject efcyQesitm = new JSONObject();
                efcyQesitm.appendField("efcyQesitm", resPillDto.getEfcyQesitm());

                AnswerDto answerDto = new AnswerDto(intent, pillName, efcyQesitm);

                return answerDto;
            }
            else if(intent == 5){                       // 복용 방법

                ArrayList<String> rArray = (ArrayList<String>) questionDto.getEntity().get("entity");
                if(rArray.isEmpty()){
                    return new AnswerDto(0,null,null);
                }

                String pillName = rArray.get(0);

                ResPillDto resPillDto = pillService.getPill(pillName);
                JSONObject method = new JSONObject();
                method.appendField("method", resPillDto.getMethod());

                AnswerDto answerDto = new AnswerDto(intent, pillName, method);

                return answerDto;
            }
            else {
                throw new NotFoundIntentException();
            }
        }
        catch (NullPointerException e){
            System.out.println("Intent is : "+ intent);
            System.out.println("Entity is : " + questionDto.getEntity());
            System.out.println("e message = " + e.getMessage());
            return AnswerDto.builder()
                    .intent(100)
                    .entity("죄송합니다. 다시 한번 더 질문 해 주실래요?")
                    .answer(new JSONObject())
                    .build();
        }
        catch (IndexOutOfBoundsException e){
            ///
            return null;
        }



    }

    // 키워드 + 위치 기반 병원 정보
    @PostMapping("/hospital/location")
    public List<HosAndPharDto> getHospital_location(@RequestBody ReqHospitalDto reqHospitalDto){
        return kakaoRestApi.getHospitalInfo(reqHospitalDto.getPlace(), reqHospitalDto.getCategory());
    }

    @PostMapping("/hospital")
    public AnswerDto getHospital(@RequestBody ReqHospitalDto reqHospitalDto){
        String location = reqHospitalDto.getPlace();
        String category = reqHospitalDto.getCategory();

        List<HosAndPharDto> hospital = kakaoRestApi.getHospitalInfo(location, category); // kakao api 통해 병원 정보 받아오기

        JSONObject hospitals = new JSONObject();
        hospitals.appendField("hospital", hospital);

        String locationAndCategory = location + " " + category;
        AnswerDto answerDto = new AnswerDto(2, locationAndCategory, hospitals);

        return answerDto;
    }

    @PostMapping("/pharmacy")
    public AnswerDto getPharmacy(@RequestBody String place){
        List<HosAndPharDto> pharmacy = kakaoRestApi.getPharmacyInfo(place);
        JSONObject pharmacies = new JSONObject();
        pharmacies.appendField("pharmacy", pharmacy);

        AnswerDto answerDto = new AnswerDto(1, place, pharmacies);
        return answerDto;
    }

    @PostMapping("/precaution")
    public AnswerDto getWarning(@RequestBody String pillName){
        ResPillDto resPillDto = pillService.getPill(pillName);
        JSONObject precaution = new JSONObject();
        precaution.appendField("precaution", resPillDto.getMethod());

        AnswerDto answerDto = new AnswerDto(0, pillName, precaution);

        return answerDto;

    }

    @PostMapping("/sideEffect")
    public AnswerDto getSideEffect(@RequestBody String pillName){
        ResPillDto resPillDto = pillService.getPill(pillName);
        JSONObject sideEffect = new JSONObject();
        sideEffect.appendField("sideEffect", resPillDto.getSideEffect());

        AnswerDto answerDto = new AnswerDto(3, pillName, sideEffect);

        return answerDto;
    }

    @PostMapping("/effect")
    public AnswerDto getEffect(@RequestBody String pillName){
        ResPillDto resPillDto = pillService.getPill(pillName);
        JSONObject sideEffect = new JSONObject();
        sideEffect.appendField("sideEffect", resPillDto.getSideEffect());

        AnswerDto answerDto = new AnswerDto(4, pillName, sideEffect);

        return answerDto;
    }
    @PostMapping("/method")
    public AnswerDto getmethod(@RequestBody String pillName){
        ResPillDto resPillDto = pillService.getPill(pillName);
        JSONObject efcyQesitm = new JSONObject();
        efcyQesitm.appendField("efcyQesitm", resPillDto.getEfcyQesitm());

        AnswerDto answerDto = new AnswerDto(5, pillName, efcyQesitm);

        return answerDto;
    }



}