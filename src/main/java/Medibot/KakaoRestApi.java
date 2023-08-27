package Medibot;

import Medibot.Dto.CoordinateDto;
import Medibot.Dto.HosAndPharDto;
import Medibot.Dto.KakaoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KakaoRestApi {

    @Value("${kakao.restapi.key}")
    private String kakaoApiKey;

    private static final String API_SERVER_HOST  = "https://dapi.kakao.com";
    private static final String SEARCH_PLACE_KEYWORD_PATH = "/v2/local/search/keyword.json";
    private static final String SEARCH_PLACE_CATEGORY_PATH = "/v2/local/search/category.json";

    public CoordinateDto getCoordinate(String place){
        try{
            String queryString = "?query="+ URLEncoder.encode(place, "UTF-8")+"&page="+"1"+"&size="+"1";
            RestTemplate restTemplate = new RestTemplate();
            URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH+queryString);

            // header setting
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "KakaoAK " + kakaoApiKey);

            // request
            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);

            ObjectMapper mapper = new ObjectMapper();
            KakaoResponse kakaoResponse = mapper.readValue(response.getBody(), KakaoResponse.class);

            String x = kakaoResponse.getDocuments().get(0).getX();
            String y = kakaoResponse.getDocuments().get(0).getY();
            CoordinateDto coordinateDto = new CoordinateDto(x,y);

            return coordinateDto;
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return new CoordinateDto("0", "0");

    }

    public List<HosAndPharDto> getHospitalInfo(String place, String category){
        try{
            CoordinateDto coordinateDto = getCoordinate(place);
            String queryString = "?query="+ URLEncoder.encode(category, "UTF-8")+"&radius=1000&size=5"+"&sort=distance"
                    +"&x="+coordinateDto.getX() +"&y="+coordinateDto.getY()
                    +"&category_group_code=HP8";
            RestTemplate restTemplate = new RestTemplate();
            URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH+queryString);

            // header setting
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "KakaoAK " + kakaoApiKey);

            // request
            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);

            ObjectMapper mapper = new ObjectMapper();
            KakaoResponse kakaoResponse = mapper.readValue(response.getBody(), KakaoResponse.class);

            List<HosAndPharDto> hospitalDtos = kakaoResponse.getDocuments().stream().map(p-> HosAndPharDto.builder()
                    .name(p.getPlaceName())
                    .address(p.getRoadAddressName())
                    .distance(p.getDistance())
                    .phone(p.getPhone())
                    .place_url(p.getPlaceUrl())
                    .build())
                    .collect(Collectors.toList());

            return hospitalDtos;
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return new ArrayList<>();
    }
    public List<HosAndPharDto> getPharmacyInfo(String place){
        try{
            CoordinateDto coordinateDto = getCoordinate(place);
            String queryString = "?radius=1000&size=5"+"&sort=distance"+"&x="+coordinateDto.getX()
                    +"&y="+coordinateDto.getY()+"&category_group_code=PM9";
            RestTemplate restTemplate = new RestTemplate();
            URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_CATEGORY_PATH+queryString);

            // header setting
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "KakaoAK " + kakaoApiKey);

            // request
            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);

            ObjectMapper mapper = new ObjectMapper();
            KakaoResponse kakaoResponse = mapper.readValue(response.getBody(), KakaoResponse.class);

            List<HosAndPharDto> hospitalDtos = kakaoResponse.getDocuments().stream().map(p-> HosAndPharDto.builder()
                            .name(p.getPlaceName())
                            .address(p.getRoadAddressName())
                            .distance(p.getDistance())
                            .phone(p.getPhone())
                            .place_url(p.getPlaceUrl())
                            .build())
                    .collect(Collectors.toList());

            return hospitalDtos;
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return new ArrayList<>();
    }
}
