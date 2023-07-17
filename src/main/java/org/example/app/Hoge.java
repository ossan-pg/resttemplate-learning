package org.example.app;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.nio.charset.Charset;

public class Hoge {
    public static void main(String[] args) {
        Charset.availableCharsets().values().stream().forEach(cs -> {
            System.out.print(cs.name() + ": ");
            cs.aliases().forEach(alias -> System.out.print(alias + " "));
            System.out.println();
        });
    }
}
