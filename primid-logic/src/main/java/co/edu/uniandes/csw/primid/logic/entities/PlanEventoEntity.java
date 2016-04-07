/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.primid.logic.entities;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author s.gonzalez22
 */
@Entity
public class PlanEventoEntity extends BaseEntity implements Serializable {



    @PodamStrategyValue(DateStrategy.class)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

      @PodamStrategyValue(DateStrategy.class)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;





      /**
     * @return the publishDate
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

     /**
     * @return the publishDate
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }




}