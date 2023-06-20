package Medibot.Controller;

import Medibot.Dto.CoordinateDto;
import Medibot.Dto.HospitalDto;
import Medibot.Dto.ReqHospitalDto;
import Medibot.KakaoRestApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IndexController {

    private final KakaoRestApi kakaoRestApi;

    // 사용자가 입력한 문자열 받기
    @PostMapping("/question")
    public Integer question(@RequestBody String question){
        // restTemplate 이용해서 AI server로 api 요청
        System.out.println(question);
        return 0;
    }

    // string 형태의 위치를 정확한 좌표로 바꾸기
    @GetMapping(value = "/place")
    public CoordinateDto getKeywordLocation(@RequestBody String place){
       return kakaoRestApi.getCoordinate(place);
    }

    // 키워드 + 위치 기반 병원 정보
    @GetMapping("/hospital")
    public List<HospitalDto> getHospital(@RequestBody ReqHospitalDto reqHospitalDto){
        return kakaoRestApi.getHospitalInfo(reqHospitalDto.getPlace(), reqHospitalDto.getCategory());
    }

    @GetMapping("/pharmacy")
    public List<HospitalDto> getPharmacy(@RequestBody String place){
        return kakaoRestApi.getPharmacyInfo(place);
    }
}
