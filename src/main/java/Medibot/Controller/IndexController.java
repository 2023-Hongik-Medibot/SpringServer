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

        // Real
        QuestionDto questionDto = questionService.getIntentEntity(question);
//        JSONObject jsonObject = questionService.getIntentEntity(question);

        //test
//        JSONObject entity = new JSONObject();
//        List<String> pharmacy1 = new ArrayList<>();
//        pharmacy1.add("휴스나정");
//        entity.put("entity", pharmacy1);
//        QuestionDto questionDto = QuestionDto.builder()
//                .intent(0)
//                .entity(entity)
//                .build();

        int intent = questionDto.getIntent();

        try {
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
            LinkedHashMap<String, List<String>> hospitalEntity = new LinkedHashMap<>();
            hospitalEntity = (LinkedHashMap<String, List<String>>) questionDto.getEntity().get("entity");
//                JSONObject hos = questionDto.getEntity();
//                System.out.println(hos);
//                JSONObject hos1 = hos.get("entity");
//                System.out.println(hos1);
                ArrayList<String> arrayLocation = (ArrayList<String>) hospitalEntity.get("장소");
                System.out.println(arrayLocation);
                ArrayList<String> arrayCategory = (ArrayList<String>) hospitalEntity.get("병원");
                System.out.println(arrayCategory);

//            ArrayList<String> error1 = new ArrayList<>();
//            error1.add("비");
//            error1.add("이인후과");


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


}