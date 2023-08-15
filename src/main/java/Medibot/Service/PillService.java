package Medibot.Service;

import Medibot.Dto.CoordinateDto;
import Medibot.Dto.KakaoResponse;
import Medibot.Dto.ResPillDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;

@Service
public class PillService {

    @Value("${apis.restapi.key}")
    private String apisApiKey;

    private static final String API_HOST  = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";

    // public ResPillDto getEffect(String pillName){
    public void getEffect(String pillName){
        try{
            String queryString = "?serviceKey="+ apisApiKey +"&pageNo="+"1"+"&numOfRows="+"1"+"&itemName="+pillName;
//            String queryString = "?&pageNo="+"1"+"&numOfRows="+"1"+"&itemName="+pillName;
            RestTemplate restTemplate = new RestTemplate();
            URI url = URI.create(API_HOST+queryString);


            // header setting
            HttpHeaders headers = new HttpHeaders();
//            headers.set("apisApiKey", apisApiKey);

            // request
            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);

            ObjectMapper mapper = new ObjectMapper();
            // KakaoResponse kakaoResponse = mapper.readValue(response.getBody(), KakaoResponse.class);
            System.out.println(response.getBody());
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }

    }
}
