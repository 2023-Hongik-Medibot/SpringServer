package Medibot.Service;

import Medibot.Dto.*;
import Medibot.Repository.PillRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

//    private PillRepository pillRepository;

    private static final String API_HOST  = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";

    public ResPillDto getPill(String pillName){
//    public void getPill(String pillName){
        try{
            StringBuilder urlBuilder = new StringBuilder(API_HOST); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + apisApiKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("itemName","UTF-8") + "=" + URLEncoder.encode(pillName, "UTF-8")); /*제품명*/
            urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default:xml*/

            URI uri = URI.create(urlBuilder.toString());

            // header setting
            HttpHeaders headers = new HttpHeaders();

            // request
            HttpEntity<?> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    String.class);

            ObjectMapper mapper = new ObjectMapper();
            ApisResponse apisResponse = mapper.readValue(response.getBody(), ApisResponse.class);
            System.out.println(apisResponse.getBody().getItems().get(0).getEfcyQesitm());
            Items items = apisResponse.getBody().getItems().get(0);

            ResPillDto resPillDto = ResPillDto.builder()
                    .name(items.getItemName())
                    .efcyQesitm(items.getEfcyQesitm())
                    .method(items.getUseMethodQesitm())
                    .precaution(items.getAtpnQesitm())
                    .sideEffect(items.getSeQesitm())
                    .build();

            return resPillDto;
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return new ResPillDto("","","","","");
    }
}
