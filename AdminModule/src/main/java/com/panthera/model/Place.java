/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Administrator
 */
@Data
@Table
@Entity
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int placeId;
    @Column(length = 1000)
    private String name;
    @Column(length = 1000)
    private String phenomenon;
    @Column(length = 1000)
    private String tempmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private AtmosphereInfo atmospherInfo;

}
