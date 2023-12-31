# Задание

Разработать Spring Boot приложение, выполняющее следующие функции:

1. Принимает Post-запрос, содержащий исходное не отформатированное письмо в виде xml документа.
2. Выполняет форматирование письма, используя механизм работы с шаблонами из библиотеки
Thymeleaf по приведенным в задании правилам.
3. Для хранения данных из приведенных в задании таблиц используется in memory БД типа h2.
4. Возвращает ответ, содержащий отформатированное письмо в виде xml-документа или ошибку
форматирования.

# Описание эндпоинта

### `http://localhost:8081/xml`

- Body(form-data). На вход подается файл, например `key=file` `value=inputMessage.xml`

- Response:

```xml
<letter>
    <title>
        <description>Официальное письмо народу Татуина от землян</description>
        <theme>Озеленение пустыни</theme>
        <alien_race code="A2"/>
        <created>2023-02-21_13:45</created>
        <id description="Идентификатор письма" value="c36ba6b0-951d-4b16-8bff-4ac3abb0dc59"/>
        <authors>
            <employee>
                <name>Перт</name>
                <second_name>Петров</second_name>
                <lastname>Петрович</lastname>
                <position>Главный инженер по строительству межзвездных автострад</position>
            </employee>
            <employee>
                <name>Иван</name>
                <second_name>Иванов</second_name>
                <lastname>Иванович</lastname>
                <position>Старший научный сотрудник по добыче полезных ископаемых</position>
            </employee>
            <employee>
                <name>Наталья</name>
                <second_name>Сидорова</second_name>
                <lastname>Николаевна</lastname>
                <position>Директор департамента озеленения пустынь</position>
            </employee>
        </authors>
    </title>
    <message>
        <paragraph>Dif-tor heh smusma, уважаемые правители татуина!</paragraph>
        <paragraph>Мы предлагаем Вам наши услуги по озеленению Вашей планеты. Наша компания имеет большой опыт в этой области, и мы готовы предоставить Вам наши знания и ресурсы для достижения Вашей цели.</paragraph>
        <paragraph>Для озеленения планеты используются различные ресурсы, такие как вода, почва, семена и удобрения. В зависимости от условий на планете могут использоваться различные методы озеленения. Например, на пустынных планетах могут использоваться специальные технологии для выращивания деревьев в засушливых условиях, различные овощи и фрукты, которые обладают способностью противостоять экстремальным климатическим явлениям.</paragraph>
        <paragraph>Надеюсь, это поможет Вам. Если у Васесть какие-либо дополнительныевопросы, пожалуйста, не стесняйтесьспрашивать. С уважением, Земляне!</paragraph>
    </message>
    <contacts>
        <tel number="8475861724"/>
        <address>Планета Земля</address>
    </contacts>
    <sent_time>2023-06-07 05:29</sent_time>
</letter>
```

### Swagger

- для запуска Swagger необходимо воспользоваться url:

```
http://localhost:8081/swagger-ui/index.html#/
```

# Как запустить?

- Предварительно мной был создан образ приложения и добавлен на [DockerHub](https://hub.docker.com/u/vya4eslava):

- Для запуска Вам необходимо клонировать на свой компьютер репозиторий. Либо скачать .zip архив с проектом. После этого
выполнить команду:

```shell script
docker compose up -d
```
