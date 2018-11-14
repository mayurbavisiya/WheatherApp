/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class ForecastsBean {

    @XmlElement(type = ForecastBean.class, name = "forecast")
    List<ForecastBean> forecast = new ArrayList<>();

}
