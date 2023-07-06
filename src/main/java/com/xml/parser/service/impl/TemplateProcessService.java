package com.xml.parser.service.impl;

import com.xml.parser.model.EmployeeModel;
import com.xml.parser.service.ParserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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



//        List<EmployeeModel> list = new ArrayList<>();
//
//        list.add(new EmployeeModel("q", "w", "e", "r"));
//        list.add(new EmployeeModel("t", "y", "u", "i"));
//        list.add(new EmployeeModel("o", "p", "]", "["));



        List<String> list = new ArrayList<>();
        list.add("HELLO");
        list.add("WORLD");

        context.setVariable("messages", list);

        String content = springTemplateEngine.process("template",context);
        System.out.println();
    }

}
