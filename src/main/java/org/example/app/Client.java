package org.example.app;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.HashMap;

@Controller
public class Client {
    @ModelAttribute
    public InputForm setupInputForm() {
        return new InputForm();
    }

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("someAttr", "hoge");
        return "index";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String result(InputForm inputForm, Model model) {
        // TODO Map じゃあかんのか？
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("id", inputForm.getId());
        map.add("name", inputForm.getName());

        MediaType mediaType = new MediaType(
                MediaType.APPLICATION_FORM_URLENCODED,
                Map.of("charset", "windows-31j")
        );

        // TODO タイムアウトの設定とか Charset とか
        model.addAttribute("someAttr", "hoge");
        RequestEntity<MultiValueMap<String,String>> req = RequestEntity
                .post(URI.create("http://localhost:8080/api/xml"))
                .contentType(mediaType)
                .body(map);

        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> res = rest.exchange(req, String.class);

        System.out.println("status:");
        System.out.println("  " + res.getStatusCode());
        System.out.println("headers:");
        res.getHeaders().forEach((key, values) -> System.out.println("  " + key + ": " + values));
        System.out.println("body:");
        System.out.println("  " + res.getBody());

        return "result";
    }

    public static void main(String[] args) {
        // TODO Map じゃあかんのか？
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("key", "value");

        // TODO タイムアウトの設定とか Charset とか
        RequestEntity<MultiValueMap<String,String>> request =RequestEntity
                .post(URI.create("http://localhost:8080/api/xml"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .body(map);

        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> res = rest.exchange(request, String.class);
        System.out.println("status:");
        System.out.println("  " + res.getStatusCode());
        System.out.println("headers:");
        res.getHeaders().forEach((key, values) -> System.out.println("  " + key + ": " + values));
        System.out.println("body:");
        System.out.println("  " + res.getBody());
    }
}