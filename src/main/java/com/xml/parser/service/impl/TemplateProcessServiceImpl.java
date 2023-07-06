package com.xml.parser.service.impl;

import com.xml.parser.entity.Author;
import com.xml.parser.entity.Header;
import com.xml.parser.model.EmployeeModel;
import com.xml.parser.model.InputMessageModel;
import com.xml.parser.model.OutputMessageModel;
import com.xml.parser.repository.AuthorRepository;
import com.xml.parser.repository.HeaderRepository;
import com.xml.parser.service.ParserService;
import com.xml.parser.service.TemplateProcessService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TemplateProcessServiceImpl implements TemplateProcessService {

    private SpringTemplateEngine springTemplateEngine;
    private ParserService parserService;
    private HeaderRepository headerRepository;
    private AuthorRepository authorRepository;

    @Override
    public String setTemplate(MultipartFile file)
      throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
        Context context = new Context();

        InputMessageModel inputMessageModel = parserService.parseInputMessage(file);
        Header header = headerRepository.findByMessageCode(inputMessageModel.getCode());

        List<Author> authorList = authorRepository.findByCustomAuthorIdIn(inputMessageModel.getAuthorsId());
        List<EmployeeModel> authors = authorList
          .stream()
          .map(elem -> new EmployeeModel(
            elem.getFirstName(), elem.getSecondName(), elem.getLastName(), elem.getPosition().getName()
          ))
          .toList();

        OutputMessageModel model = new OutputMessageModel(
          header.getDescription(),
          header.getMessageTopic(),
          header.getMessageCode(),
          inputMessageModel.getCreatedDate(),
          inputMessageModel.getUid(),
          authors,
          inputMessageModel.getText(),
          inputMessageModel.getPhone(),
          inputMessageModel.getAddress(),
          new SimpleDateFormat("yyyy-dd-MM hh:mm").format(new Date())
        );

        context.setVariable("model", model);

        return springTemplateEngine.process("template",context);
    }
}
