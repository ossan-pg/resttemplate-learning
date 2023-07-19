package org.example.api;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class Server {

    @RequestMapping(value = "/api/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String apiXml(@RequestBody MultiValueMap<String,String> req) {
        // TODO 受診したリクエストパラメータをXML形式のデータにして返す
        return req.entrySet().stream()
                .map(e -> "<" + e.getKey() + ">"
                        + String.join(", ", e.getValue())
                        + "</" + e.getKey() + ">"
                ).collect(Collectors.joining());
    }
}
