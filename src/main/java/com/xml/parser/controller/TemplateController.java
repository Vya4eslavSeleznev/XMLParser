package com.xml.parser.controller;

import com.xml.parser.service.TemplateProcessService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

@RestController
@RequestMapping("/xml")
@AllArgsConstructor
public class TemplateController {

    private TemplateProcessService templateProcessService;

    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> parseDocument(@RequestPart("file") MultipartFile file) {
        try {
            String content = templateProcessService.setTemplate(file);
            return new ResponseEntity<>(content, HttpStatus.OK);
        }
        catch(XPathExpressionException | IOException | ParserConfigurationException | SAXException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
