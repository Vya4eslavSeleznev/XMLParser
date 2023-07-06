package com.xml.parser.service.impl;

import com.xml.parser.model.InputMessageModel;
import com.xml.parser.service.ParserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class ParserServiceImpl implements ParserService {

    @Override
    public InputMessageModel parseInputMessage()
      throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(new File("src/main/resources/data.xml"));
        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList authors = (NodeList) xPath
          .compile("//Письмо_инопланетянам/author/id/value/text()")
          .evaluate(xmlDocument, XPathConstants.NODESET);

        String date = getContent("//Письмо_инопланетянам/created/@date_time", xPath, xmlDocument);

        return new InputMessageModel(
          getContent("//Письмо_инопланетянам/код_расы/value/text()", xPath, xmlDocument),
          stringToDate(date),
          getContent("//Письмо_инопланетянам/uid/code/value/text()", xPath, xmlDocument),
          convertAuthors(authors),
          convertText(getContent("//Письмо_инопланетянам/document/text/text()", xPath, xmlDocument)),
          getContent("//Письмо_инопланетянам/document/address/value/text()", xPath, xmlDocument),
          getContent("//Письмо_инопланетянам/document/tel/value/text()", xPath, xmlDocument)
        );
    }

    private String getContent(String expression, XPath xPath, Document xmlDocument) throws XPathExpressionException {
        Node node = (Node) xPath
          .compile(expression)
          .evaluate(xmlDocument, XPathConstants.NODE);

        return node.getNodeValue();
    }

    private List<String> convertText(String content) {
        List<String> textList = new ArrayList<>();
        String[] textArr  = content.split("\n");

        for(String s : textArr) {
            StringBuilder sb = new StringBuilder(s.trim());

            if(!sb.toString().equals("")) {
                textList.add(sb.toString());
            }

            sb.delete(0, sb.length());
            System.out.println();
        }

        return textList;
    }

    private List<String> convertAuthors(NodeList authors) {
        List<String> authorIds = new ArrayList<>();

        for(int i = 0; i < authors.getLength(); i++) {
            authorIds.add(authors.item(i).getNodeValue());
        }

        return authorIds;
    }

    private String stringToDate(String stringDate) {
        Date date;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

        try {
            date = df.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: ", e);
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) + 1);

        df.applyPattern("yyyy-MM-dd_HH:mm");

        return df.format(calendar.getTime());
    }
}
