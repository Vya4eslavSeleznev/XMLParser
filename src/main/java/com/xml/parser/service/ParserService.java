package com.xml.parser.service;

import com.xml.parser.model.InputMessageModel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public interface ParserService {

    InputMessageModel parseInputMessage()
      throws IOException, SAXException, XPathExpressionException, ParserConfigurationException;
}
