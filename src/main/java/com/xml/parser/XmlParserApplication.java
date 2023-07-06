package com.xml.parser;

import com.xml.parser.service.impl.TemplateProcessServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

@SpringBootApplication
public class XmlParserApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(XmlParserApplication.class, args);


	}
}
