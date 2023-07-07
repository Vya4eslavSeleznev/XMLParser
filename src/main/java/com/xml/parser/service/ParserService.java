package com.xml.parser.service;

import com.xml.parser.exception.MessageException;
import com.xml.parser.model.InputMessageModel;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public interface ParserService {

    InputMessageModel parseInputMessage(MultipartFile file)
      throws IOException, SAXException, XPathExpressionException, ParserConfigurationException, MessageException;
}
