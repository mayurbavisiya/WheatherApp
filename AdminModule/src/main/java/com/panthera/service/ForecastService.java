/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.service;

import com.panthera.model.Forecast;
import com.panthera.dao.IForecastDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class ForecastService {

    @Autowired
    private IForecastDao forecastDao;

    public void addForecast(List<Forecast> forecastList) {
        forecastDao.saveAll(forecastList);
    }

    public List<Forecast> getAllForecastDetails() {
        return forecastDao.findAll();
    }

}
