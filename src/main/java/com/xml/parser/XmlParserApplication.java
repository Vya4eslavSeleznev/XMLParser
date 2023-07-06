package com.xml.parser;

import com.xml.parser.entity.Header;
import com.xml.parser.repository.HeaderRepository;
import com.xml.parser.service.impl.ParserServiceImpl;
import com.xml.parser.service.impl.TemplateProcessService;
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
		//		var r = c.getBean(HeaderRepository.class).save(
		//			new Header("1", "2", "3")
		//		);
		//
		//
		//		System.out.println();

		//		try {
		//			var r = c.getBean(ParserServiceImpl.class).parseInputMessage();
		//			System.out.println();
		//		}
		//		catch(ParserConfigurationException e) {
		//			throw new RuntimeException(e);
		//		}
		//		catch(XPathExpressionException e) {
		//			throw new RuntimeException(e);
		//		}
		//		catch(IOException e) {
		//			throw new RuntimeException(e);
		//		}
		//		catch(SAXException e) {
		//			throw new RuntimeException(e);
		//		}
		//	}



		c.getBean(TemplateProcessService.class).test();
	}

}
