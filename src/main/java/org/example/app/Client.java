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
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Client {
    @ModelAttribute
    public InputForm setupInputForm() {
        return new InputForm();
    }

    @RequestMapping(value = "/")
    public String index(Model model) {
        //model.addAttribute("someAttr", "hoge");
        return "index";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String result(InputForm inputForm, Model model) {
        // Map(HashMap) だと対応する HttpMessageConverter が設定されていないため 500エラーになる
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("id", inputForm.getId());
        map.add("name", inputForm.getName());
        System.out.println("id: " + inputForm.getId());
        System.out.println("name: " + inputForm.getName());
        System.out.println("============");

        MediaType mediaType = new MediaType(
                MediaType.APPLICATION_FORM_URLENCODED,
                Charset.forName("windows-31j")
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

        model.addAttribute("status", res.getStatusCode().toString());
        List<String> headers = res.getHeaders().entrySet().stream()
                        .map(e -> e.getKey() + ": "
                                + e.getValue().stream()
                                .collect(Collectors.joining(";", "[", "]")))
                        .collect(Collectors.toList());
        model.addAttribute("headers", headers);
        model.addAttribute("body", res.getBody());
        return "result";
    }

    public static void main(String[] args) {
        // TODO Map じゃあかんのか？
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("key1", "ほげほげ");
        map.add("key1", "value2");
        map.add("key2", "valueA");

        // TODO タイムアウトの設定とか Charset とか
        RequestEntity<MultiValueMap<String,String>> req =RequestEntity
                .post(URI.create("http://localhost:8080/api/xml"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(map);

        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> res = rest.exchange(req, String.class);
        System.out.println("status:");
        System.out.println("  " + res.getStatusCode());
        System.out.println("headers:");
        res.getHeaders().forEach((key, values) -> System.out.println("  " + key + ": " + values));
        System.out.println("body:");
        res.getBody().lines().forEach(s -> System.out.println("  " + s));
        // System.out.println("  " + res.getBody());
    }
}