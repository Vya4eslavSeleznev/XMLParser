package com.xml.parser.service.impl;

import com.xml.parser.service.ParserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class TemplateProcessService {

    private SpringTemplateEngine springTemplateEngine;
    private ParserService parserService;

    public void test() {
        Map<String, String> map = new HashMap<>();
        Context context = new Context();
        //context.setVariable("map", map);
        //map.put("theme", "TEST");
        context.setVariable("description", "TEST");

        String content = springTemplateEngine.process("template",context);
        System.out.println();
    }

}
