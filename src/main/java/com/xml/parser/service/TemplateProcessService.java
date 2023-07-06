package com.xml.parser.service;

import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public interface TemplateProcessService {

    String setTemplate(MultipartFile file)
      throws XPathExpressionException, IOException, ParserConfigurationException, SAXException;
}
