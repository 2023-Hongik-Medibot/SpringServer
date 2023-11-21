package Medibot.Service;

import Medibot.Dto.*;
import Medibot.Exception.NotFoundPillException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class PillService {

    @Value("${apis.restapi.key}")
    private String apisApiKey;

    private static final String API_HOST  = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";

    public ResPillDto getPill(String pillName){
        try{
            System.out.println(pillName);
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
            System.out.println(apisResponse.getBody());
            System.out.println(apisResponse.getBody().getItems());


            if(apisResponse.getBody().getItems() == null){
                System.out.println("ififififi");
                return ResPillDto.builder()
                        .build();
            }
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
        catch (NullPointerException e){
            System.out.println("e = " + e);
            throw new NotFoundPillException();

        } catch (JsonMappingException e) {
            e.printStackTrace();
            System.out.println("e = " + e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("e = " + e);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("e = " + e);
        }

        return new ResPillDto("","","","","");
    }

    public List<pillImageResponseDto> pillInfo(pillNameAndImageUrlDto pillNameAndImageUrlDto){
        try{

            List<String> pillNames = pillNameAndImageUrlDto.getPillName();
            System.out.println(pillNames);
            List<pillImageResponseDto> responseDtos = new ArrayList<>();

            for(int i = 0; i<pillNames.size(); i++){
                StringBuilder urlBuilder = new StringBuilder(API_HOST); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + apisApiKey); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
                urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
                urlBuilder.append("&" + URLEncoder.encode("itemName","UTF-8") + "=" + URLEncoder.encode(pillNames.get(i), "UTF-8")); /*제품명*/
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
                List<Items> items = apisResponse.getBody().getItems();

                Items items1 = items.get(0);

                pillImageResponseDto pillImage = pillImageResponseDto.builder()
                        .s3path(pillNameAndImageUrlDto.getImageUrls())
                        .pillName(items1.getItemName())
                        .pillImage(items1.getItemImage())
                        .efcyQesitm(items1.getEfcyQesitm())
                        .method(items1.getUseMethodQesitm())
                        .precaution(items1.getAtpnQesitm())
                        .sideEffect(items1.getSeQesitm())
                        .build();

                responseDtos.add(pillImage);
            }


            return responseDtos;
        }
        catch (Exception e){
            List<pillImageResponseDto> responseDtos = new ArrayList<>();
            pillImageResponseDto pillImage = pillImageResponseDto.builder()
                    .s3path(pillNameAndImageUrlDto.getImageUrls())
                    .build();
            System.out.println("e = " + e);
            responseDtos.add(pillImage);
            return responseDtos;
        }
    }



}
