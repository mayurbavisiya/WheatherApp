/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.beans;

import com.panthera.model.Place;
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
@Data
@XmlRootElement(name = "day")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class DayBean {

    @XmlElement(name = "phenomenon")
    private String phenomenon;
    @XmlElement(name = "tempmax")
    private String tempmax;
    @XmlElement(name = "tempmin")
    private String tempmin;
    @XmlElement(name = "peipsi")
    private String peipsi;
    @XmlElement(name = "text")
    private String text;
    @XmlElement(name = "sea")
    private String sea;
    @XmlElement(type = PlaceBean.class, name = "place")
    List<PlaceBean> place = new ArrayList<>();

}
