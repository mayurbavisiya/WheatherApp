/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.beans;

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
@NoArgsConstructor
@XmlRootElement(name = "place")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaceBean {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "phenomenon")
    private String phenomenon;
    @XmlElement(name = "tempmin")
    private String tempmin;

}
