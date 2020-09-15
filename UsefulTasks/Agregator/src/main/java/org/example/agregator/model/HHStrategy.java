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

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();

        Document document = getDocument(searchString, 0);
        String cssQuery = "[data-qa=vacancy-serp__vacancy]";
        Elements elements = document.select(cssQuery);

        int page = 0;
        while (elements.size() > 0) {
            Iterator<Element> iterator = elements.iterator();

            while (iterator.hasNext()) {
                Element e = iterator.next();
                Vacancy vacancy = new Vacancy();
                vacancy.setUrl(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                vacancy.setSalary(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                vacancy.setCity(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                vacancy.setCompanyName(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                vacancy.setTitle(e.getElementsByAttributeValue("target", "_blank").text());
                vacancy.setSiteName("hh.ua");
                list.add(vacancy);
            }
            page++;
            document = getDocument(searchString, page);
            elements = document.select(cssQuery);
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
