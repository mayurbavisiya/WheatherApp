/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.controller;

import com.panthera.beans.DayBean;
import com.panthera.beans.ForecastBean;
import com.panthera.beans.ForecastsBean;
import com.panthera.beans.NightBean;
import com.panthera.beans.PlaceBean;
import com.panthera.model.AtmosphereInfo;
import com.panthera.service.ForecastService;
import com.panthera.model.Forecast;
import com.panthera.model.Place;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Administrator
 */
@Controller
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @Scheduled(cron = "* */30 * * * ?")
    //@RequestMapping("/getForcast")
    public void getWeatherDetails() throws JAXBException {

        final String uri = "http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
        List<Forecast> forecastList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        JAXBContext jaxbContext = JAXBContext.newInstance(ForecastsBean.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(result.getBody());
        ForecastsBean foreCastBean = (ForecastsBean) jaxbUnmarshaller.unmarshal(reader);

        for (ForecastBean forecastBean : foreCastBean.getForecast()) {
            Forecast forecast = new Forecast();
            forecast.setDate(forecastBean.getDate());
            List<AtmosphereInfo> atmosphereSet = new ArrayList<>();
            for (NightBean nightBean : forecastBean.getNight()) {
                AtmosphereInfo atmospherInfo = new AtmosphereInfo();
                atmospherInfo.setDay(false);
                atmospherInfo.setPeipsi(nightBean.getPeipsi());
                atmospherInfo.setPhenomenon(nightBean.getPhenomenon());
                atmospherInfo.setSea(nightBean.getSea());
                atmospherInfo.setTempmax(nightBean.getTempmax());
                atmospherInfo.setTempmin(nightBean.getTempmin());
                atmospherInfo.setText(nightBean.getText());
                atmospherInfo.setForecastId(forecast);

                List<Place> placeSet = new ArrayList<>();
                for (PlaceBean placeBean : nightBean.getPlace()) {

                    Place place = new Place();
                    place.setName(placeBean.getName());
                    place.setPhenomenon(placeBean.getPhenomenon());
                    place.setTempmin(placeBean.getTempmin());
                    place.setAtmospherInfo(atmospherInfo);
                    placeSet.add(place);
                }
                atmospherInfo.setPlace(placeSet);
                atmosphereSet.add(atmospherInfo);

            }

            for (DayBean dayBean : forecastBean.getDay()) {
                AtmosphereInfo atmospherInfo = new AtmosphereInfo();
                atmospherInfo.setDay(true);
                atmospherInfo.setPeipsi(dayBean.getPeipsi());
                atmospherInfo.setPhenomenon(dayBean.getPhenomenon());
                atmospherInfo.setSea(dayBean.getSea());
                atmospherInfo.setTempmax(dayBean.getTempmax());
                atmospherInfo.setTempmin(dayBean.getTempmin());
                atmospherInfo.setText(dayBean.getText());
                atmospherInfo.setForecastId(forecast);

                List<Place> placeSet = new ArrayList<>();
                for (PlaceBean placeBean : dayBean.getPlace()) {
                    Place place = new Place();
                    place.setName(placeBean.getName());
                    place.setPhenomenon(placeBean.getPhenomenon());
                    place.setTempmin(placeBean.getTempmin());
                    place.setAtmospherInfo(atmospherInfo);
                    placeSet.add(place);
                }
                atmospherInfo.setPlace(placeSet);
                atmosphereSet.add(atmospherInfo);
            }
            forecast.setAtmosphereInfo(atmosphereSet);
            forecastList.add(forecast);

        }

        forecastService.addForecast(forecastList);

    }

    @RequestMapping({"/", "/getAllWeatherDetails"})
    @ResponseBody
    public List<Forecast> getAllWeatherDetails(ModelMap map) {
       
        return forecastService.getAllForecastDetails();

    }

}
