# SauceDemo — UI-автоматизация
Web UI test automation with Selenide, Cucumber, JUnit Platform and Allure.

[![Compile and optional UI tests](https://github.com/rootofevi1/ui-testing-saucedemo/actions/workflows/verify.yml/badge.svg)](https://github.com/rootofevi1/ui-testing-saucedemo/actions/workflows/verify.yml)

Учебный проект выполнен для демонстрационного интернет-магазина SauceDemo. Одни пользовательские потоки реализованы как обычные Java-тесты и как сценарии Gherkin на русском языке.

## Сценарии

- Успешная авторизация, добавление товара в корзину и оформление заказа.
- Ошибка при неверных учётных данных.
- Валидация обязательных данных покупателя при оформлении.

Используются публичные демонстрационные данные SauceDemo: standard_user / secret_sauce. Это не личные учётные данные.

## Стек

Java 21 · Gradle 9.3.0 · Selenide 7.17.0 · Cucumber 7.34.4

JUnit Jupiter / Platform 6.0.1 · Allure Java 2.35.2 · Allure Report 2.42.1

## Структура

| Каталог | Назначение |
|---|---|
| [src/main/java/com/saucedemo/pages](src/main/java/com/saucedemo/pages) | Page Object для Java-тестов |
| [src/main/java/com/saucedemo/cucumber/pages](src/main/java/com/saucedemo/cucumber/pages) | Реализация шагов Cucumber и работа со страницами |
| [src/test/java/com/saucedemo/tests](src/test/java/com/saucedemo/tests) | Обычные UI-тесты |
| [src/test/java/com/saucedemo/cucumber](src/test/java/com/saucedemo/cucumber) | Suite и hooks |
| [src/test/resources/features](src/test/resources/features) | Сценарии Gherkin |

В pages/aiassistedsolution сохранён отдельный вариант страницы авторизации, подготовленный с помощью AI. Наличие этой реализации не означает, что она используется основным набором тестов.

## Запуск

Нужны JDK 21, Google Chrome и интернет. Значения по умолчанию: Chrome, headless=true, 1920×1080, timeout=10000 мс, https://www.saucedemo.com.

```bash
git clone https://github.com/rootofevi1/ui-testing-saucedemo.git
cd ui-testing-saucedemo
./gradlew test
```

Выбрать одну реализацию:

```bash
./gradlew test --tests 'com.saucedemo.tests.SauceDemoNoCucumberTest'
./gradlew test --tests 'com.saucedemo.cucumber.CucumberRunnerTest'
```

Windows PowerShell, с видимым браузером:

```powershell
.\gradlew.bat test "-Pheadless=false"
```

Параметры можно переопределять: -Pbrowser, -PbrowserSize, -Pheadless, -Ptimeout, -PtestUrl. URL должен вести на совместимый стенд SauceDemo.

## Отчёты и CI

```bash
./gradlew allureReport
./gradlew allureServe
```

Gradle HTML: build/reports/tests/test/index.html. Allure results: build/allure-results.

CI компилирует тесты при изменениях. Полный браузерный прогон запускается вручную через Actions с параметром run_ui_tests. Внешний стенд может быть недоступен или менять разметку; CI компиляции и результаты UI-тестов оцениваются отдельно.

Включены исходники решения и необходимая конфигурация сборки. Тексты заданий, учебные материалы, исходные README курса и история учебного репозитория не публикуются.

Александр · Junior QA/AQA Engineer · [Email](mailto:a@samoylov-qa.ru) · [Telegram](https://t.me/samoylov_av)
