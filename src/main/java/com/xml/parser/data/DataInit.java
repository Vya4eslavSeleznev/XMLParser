package com.xml.parser.data;

import com.xml.parser.entity.Author;
import com.xml.parser.entity.Header;
import com.xml.parser.entity.Position;
import com.xml.parser.repository.AuthorRepository;
import com.xml.parser.repository.HeaderRepository;
import com.xml.parser.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInit implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private HeaderRepository headerRepository;
    private PositionRepository positionRepository;

    @Override
    public void run(String... args) throws Exception {
        Position position1 = positionRepository.save(new Position(
          "7432234555", "Старший научный сотрудник по добыче полезных ископаемых"
        ));

        Position position2 = positionRepository.save(new Position(
          "3676545567", "Директор департамента озеленения пустынь"
        ));

        Position position3 = positionRepository.save(new Position(
          "6575677888", "Главный инженер по строительству межзвездных автострад"
        ));

        positionRepository.save(new Position(
          "9655677677", "Руководитель отдела взаимодействия с внеземными цивилизациями"
        ));

        authorRepository.save(new Author(
            "ISO_639-1", "Иванов", "Иван", "Иванович", position1
        ));

        authorRepository.save(new Author(
          "ISO_965-44", "Сидорова", "Наталья", "Николаевна", position2
        ));

        authorRepository.save(new Author(
          "ISO_139-5", "Петров", "Перт", "Петрович", position3
        ));

        authorRepository.save(new Author(
          "ISO_8568-51", "Плюшкина", "Ирина", "", position1
        ));

        headerRepository.save(new Header(
          "A1", "Официальное письмо марсианам от жителей Земли", "Добыча полезных ископаемых"
        ));

        headerRepository.save(new Header(
          "A2", "Официальное письмо народу Татуина от землян", "Озеленение пустыни"
        ));

        headerRepository.save(new Header(
          "A3", "Официальное письмо воганам от жителей Земли", "Расширение межзвездной навигации"
        ));
    }
}
