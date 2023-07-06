package com.xml.parser.service.impl;

import com.xml.parser.model.InputMessageModel;
import com.xml.parser.service.ParserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class ParserServiceImpl implements ParserService {

    private final static String LAST_PARAGRAPH = "Надеюсь, это поможет Вам. Если у Вас"
      + "есть какие-либо дополнительные"
      +"вопросы, пожалуйста, не стесняйтесь"
      + "спрашивать. С уважением, Земляне!";

    @Override
    public InputMessageModel parseInputMessage(MultipartFile file)
      throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(file.getInputStream());
        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList authors = (NodeList) xPath
          .compile("//Письмо_инопланетянам/author/id/value/text()")
          .evaluate(xmlDocument, XPathConstants.NODESET);

        String date = getContent("//Письмо_инопланетянам/created/@date_time", xPath, xmlDocument);
        String phone = getContent("//Письмо_инопланетянам/document/tel/value/text()", xPath, xmlDocument);
        String address = getContent("//Письмо_инопланетянам/document/address/value/text()", xPath, xmlDocument);

        phone = ifContactNull(phone);
        address = ifContactNull(address);

        return new InputMessageModel(
          getContent("//Письмо_инопланетянам/код_расы/value/text()", xPath, xmlDocument),
          stringToDate(date),
          getContent("//Письмо_инопланетянам/uid/code/value/text()", xPath, xmlDocument),
          convertAuthors(authors),
          convertText(getContent("//Письмо_инопланетянам/document/text/text()", xPath, xmlDocument)),
          address,
          phone.replaceAll("[()-]","")
        );
    }

    private String ifContactNull(String str) {
        if(str == null) {
            return "not found";
        }

        return str;
    }

    private String getContent(String expression, XPath xPath, Document xmlDocument) throws XPathExpressionException {
        Node node = (Node) xPath
          .compile(expression)
          .evaluate(xmlDocument, XPathConstants.NODE);

        if(node == null) {
            return null;
        }

        return node.getNodeValue();
    }

    private List<String> convertText(String content) {
        List<String> textList = new ArrayList<>();
        String[] textArr  = content.split("\n");

        for(String s : textArr) {
            StringBuilder sb = new StringBuilder(s.trim());

            if(s.toLowerCase().contains("здравствуйте") && !sb.toString().equals("")) {
                textList.add(greeting(s));
            } else if(!sb.toString().equals("")) {
                textList.add(sb.toString());
            }

            sb.delete(0, sb.length());
            System.out.println();
        }

        textList.add(LAST_PARAGRAPH);

        return textList;
    }

    private String greeting(String str) {
        str = str.toLowerCase();

        if(str.contains("марс")){
            str = str.replace("здравствуйте", "こんにちは");
        } else if(str.contains("татуин")) {
            str = str.replace("здравствуйте", "Dif-tor heh smusma");
        } else {
            str = str.replace("здравствуйте", "안녕하세요");
        }

        return str.trim();
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
