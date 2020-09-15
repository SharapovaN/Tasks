package org.example.agregator.model;


import org.example.agregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HabrStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();

        Document document = getDocument(searchString, 0);
        Elements elements = document.getElementsByClass("inner");

        int page = 0;
        while (elements.size() > 0) {

        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            Vacancy vacancy = new Vacancy();

            vacancy.setCompanyName(e.getElementsByAttributeValue("class", "company_name").text());
            vacancy.setCity(e.getElementsByAttributeValueContaining("href", "/vacancies?city_id").text());
            vacancy.setTitle(e.getElementsByAttributeValueContaining("href", "/vacancies/").text());
            vacancy.setSalary(e.getElementsByAttributeValueContaining("title", "Зарплата").text());
            vacancy.setSiteName("https://career.habr.com");
            vacancy.setUrl("https://career.habr.com" + e.select("a[class=job_icon]").attr("href"));
        if (!vacancy.getTitle().equals(""))
            list.add(vacancy);
        }
            page++;
            document = getDocument(searchString, page);
            elements = document.getElementsByClass("inner");
        }
        return list;
    }

    protected Document getDocument(String searchString, int page) {
        Document document = null;

        try {
            document = Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36")
                    .referrer("no-referrer-when-downgrade").get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return document;
    }
}
