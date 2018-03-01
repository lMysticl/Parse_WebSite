package service.Impl;


import lombok.extern.slf4j.Slf4j;
import model.ElementAttributes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.ParseSite;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
@Slf4j
public class ParseSiteImpl implements ParseSite {


    private ElementAttributes elementAttributes = new ElementAttributes();

    private String pathAndValue;

    public String parse(String url, String PLATFORM) {
        try {
            Document document = Jsoup.connect(url).get();
            String className = elementAttributes.getClassName();

            if (className != null) {

                Elements elements = Jsoup.connect(url).get().body().select("*");

                elements.stream()
                        .filter(element -> !element.ownText().isEmpty())
                        .forEach(this::setPath);

                return "\n"+"***************" +"\n"
                       +"PLATFORM: " +PLATFORM
                        +"\n Element: "+ String.valueOf(document.getElementsByClass(elementAttributes.getClassName()).get(0))
                        +"\n Address: " + pathAndValue + "\n"
                        +"***************";
            }
        } catch (IOException | IllegalArgumentException e) {
            log.error("Error: " + e);
        }
        return "Not found element";
    }


    public void setAttributeId(String url, String id) throws IOException {

        Document document = Jsoup.connect(url).get();

        if (document.getElementById(id) != null) {

            elementAttributes.setClassName(document.getElementById(id).className());
            elementAttributes.setAttribute(document.getElementById(id).attributes().toString());
            elementAttributes.setText(document.getElementById(id).text());
            elementAttributes.setHref(document.getElementById(id).attr("href"));

        }

    }


    private String setPath(Element element) {

        String value = element.ownText();
        Elements p_el = element.parents();

        StringBuilder path = new StringBuilder(element.nodeName());

        p_el.forEach(el -> path.insert(0, el.nodeName() + '>'));

        new ArrayList<String>().add(path + " = " + value + "\n");

        if (value.equals(elementAttributes.getText())) {
            return pathAndValue = path + " = " + value;
        } else {
            return "No matches";
        }

    }
}
