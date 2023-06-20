package Medibot.Controller;

import Medibot.Dto.CoordinateDto;
import Medibot.Dto.HospitalDto;
import Medibot.Dto.ReqHospitalDto;
import Medibot.KakaoRestApi;
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
    private static final String AI_SERVER_HOST  = "http://ec2-3-34-46-75.ap-northeast-2.compute.amazonaws.com:8000/ask/first/";

    // 사용자가 입력한 문자열 받기
    @PostMapping("/question")
    public Integer question(@RequestBody String question){
        // restTemplate 이용해서 AI server로 api 요청
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

        System.out.println(response.getBody().get("intent"));
        System.out.println(question);

        int res = Integer.parseInt(response.getBody().get("intent").toString());
        return res;
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
