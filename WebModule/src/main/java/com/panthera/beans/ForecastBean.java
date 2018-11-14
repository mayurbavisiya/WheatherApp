/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Administrator
 */
@Data
@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class ForecastBean {

    @XmlAttribute
    private Date date;

    @XmlElement(type = NightBean.class, name = "night")
    List<NightBean> night = new ArrayList<>();
    @XmlElement(type = DayBean.class, name = "day")
    List<DayBean> day = new ArrayList<>();

}
