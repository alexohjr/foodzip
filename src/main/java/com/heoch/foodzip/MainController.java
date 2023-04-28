package com.heoch.foodzip;

import groovyjarjarpicocli.CommandLine;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.xml.ws.Response;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("")
@Slf4j
public class MainController {

    @Autowired
    private WebClient webClient;

    @GetMapping("")
    public String mainPage(Model model) {
        model.addAttribute("say", "Hello");

        List<TestDTO> list = new ArrayList<>();

        for(int i=0; i<10; i++) {
            TestDTO dto = TestDTO.builder().num(i).name(i+"번").build();
            list.add(dto);

//             log.info("####### dto :: " + dto.getName());
        }

        model.addAttribute("list", list);

        return "main";
    }

    @ResponseBody
    @PostMapping("main/search")
    public void search(@RequestParam("searchParam") String param) {

        log.info("##### hi");

        log.info("##### param :: " +  param);
    }

    @ResponseBody
    @GetMapping("main/add")
    public ResponseEntity<?> add(@RequestParam("coords") String coords) {

        // 0. 결과값을 담을 객체를 생성합니다.
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        try {
            // 1. 타임아웃 설정시 HttpComponentsClientHttpRequestFactory 객체를 생성합니다.
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); // 타임아웃 설정 5초
            factory.setReadTimeout(5000); // 타임아웃 설정 5초

            //Apache HttpComponents : 각 호스트(IP와 Port의 조합)당 커넥션 풀에 생성가능한 커넥션 수
            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setMaxConnTotal(50)//최대 커넥션 수
                    .setMaxConnPerRoute(20).build();
            factory.setHttpClient(httpClient);

            // 2. RestTemplate 객체를 생성합니다.
            RestTemplate restTemplate = new RestTemplate(factory);

            // 3. header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-NCP-APIGW-API-KEY-ID", "1ejgdmtjix");
            headers.set("X-NCP-APIGW-API-KEY", "jhEkoywbKUSf9ahq5mgmtATtdnFskjWB75Twx9bq");

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            // 4. 요청 URL을 정의해줍니다.
            String url = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("coords", coords)
                    .queryParam("output", "json")
                    .build(false);

            // 5. exchange() 메소드로 api를 호출합니다.
            ResponseEntity<?> response = restTemplate.exchange(String.valueOf(uri), HttpMethod.GET, entity, JSONObject.class);

            if(response.getStatusCode() == HttpStatus.OK) {

                // 6. 요청한 결과를 HashMap에 추가합니다.
                // HTTP Status Code
                resultMap.put("statusCode", response.getStatusCodeValue());
                // 헤더 정보
                resultMap.put("header", response.getHeaders());
                // 반환받은 실제 데이터 정보
                resultMap.put("body", response.getBody());

                log.info("##### result : {}", resultMap);

                log.info("##### body : {}", resultMap.get("body").toString());

                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }// end catch

        return null;
    }

    @ResponseBody
    @GetMapping("main/add2")
    public ResponseEntity<String> add2(@RequestParam("coords") String coords) {

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("coords", coords);
        queryParams.add("output", "json");

        URI uri = UriComponentsBuilder
                .fromUriString("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc")
                .queryParams(queryParams)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        return webClient.get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("X-NCP-APIGW-API-KEY-ID", "1ejgdmtjix");
                    httpHeaders.set("X-NCP-APIGW-API-KEY", "jhEkoywbKUSf9ahq5mgmtATtdnFskjWB75Twx9bq");
                })
                .retrieve()
                .toEntity(String.class)
                .block();

    }

}
