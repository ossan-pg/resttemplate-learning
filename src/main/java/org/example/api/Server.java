package org.example.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server {

    @RequestMapping(value = "/api/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String apiXml() {
        // TODO 受診したリクエストパラメータをXML形式のデータにして返す
        return "<a><b>content2</b></a>";
    }
}
