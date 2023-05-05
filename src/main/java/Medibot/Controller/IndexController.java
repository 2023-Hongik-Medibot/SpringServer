package Medibot.Controller;

import Medibot.Dto.CoordinateDto;
import Medibot.Dto.HospitalDto;
import Medibot.Dto.ReqHospitalDto;
import Medibot.KakaoRestApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final KakaoRestApi kakaoRestApi;

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
