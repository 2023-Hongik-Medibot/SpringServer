package Medibot.Controller;

import Medibot.Dto.*;
import Medibot.KakaoRestApi;
import Medibot.Service.QuestionService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IndexController {

    private final KakaoRestApi kakaoRestApi;
    private final QuestionService questionService;

    // 사용자가 입력한 문자열 받기
    @PostMapping("/question")
//    public void question(@RequestBody String question){
    public QuestionDto question(@RequestBody String question){
        return questionService.getIntentEntity(question);
    }

    // string 형태의 위치를 정확한 좌표로 바꾸기
    @GetMapping(value = "/place")
    public CoordinateDto getKeywordLocation(@RequestBody String place){
       return kakaoRestApi.getCoordinate(place);
    }

    // 키워드 + 위치 기반 병원 정보
    @PostMapping("/hospital")
    public List<HospitalDto> getHospital(@RequestBody ReqHospitalDto reqHospitalDto){
        System.out.println(reqHospitalDto.getPlace() + "   " +reqHospitalDto.getCategory());
        return kakaoRestApi.getHospitalInfo(reqHospitalDto.getPlace(), reqHospitalDto.getCategory());
    }

    @PostMapping("/pharmacy")
    public List<HospitalDto> getPharmacy(@RequestBody String place){
        System.out.println(place);
        return kakaoRestApi.getPharmacyInfo(place);
    }

    @GetMapping("/test")
    public void test(@RequestBody JSONObject jsonObject){
        System.out.println("asfd");
        questionService.test(jsonObject);
    }

}
