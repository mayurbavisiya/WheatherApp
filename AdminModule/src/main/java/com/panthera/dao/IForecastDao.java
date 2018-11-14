/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.panthera.model.Forecast;

/**
 *
 * @author Administrator
 */
@Repository
public interface IForecastDao extends JpaRepository<Forecast, Integer> {

}
