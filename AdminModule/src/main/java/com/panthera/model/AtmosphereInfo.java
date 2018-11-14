/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "atmosphereInfo")
public class AtmosphereInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int atmosphereInfoId;

    private boolean isDay;
    @Column(length = 1000)
    private String phenomenon;
     @Column(length = 1000)
    private String tempmax;
     @Column(length = 1000)
    private String tempmin;
     @Column(length = 1000)
    private String peipsi;
    @Column(length = 1000)
    private String text;
      @Column(length = 1000)
    private String sea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Forecast forecastId;

    @OneToMany(mappedBy = "atmospherInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Place> place = new ArrayList<>();

}
