package com.aqp.PrevenTecAppRest.Entity;

import java.util.Date;

public class clsEnfermedad_clasificacion {
    
    private Long id;
    private Integer clasificacion_id;
    private Integer enfermedad_id;
    private String descripcion;
    private Boolean estado;
    private Integer usucreacion;
    private Integer usumodificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClasificacion_id() {
        return clasificacion_id;
    }

    public void setClasificacion_id(Integer clasificacion_id) {
        this.clasificacion_id = clasificacion_id;
    }

    public Integer getEnfermedad_id() {
        return enfermedad_id;
    }

    public void setEnfermedad_id(Integer enfermedad_id) {
        this.enfermedad_id = enfermedad_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getUsucreacion() {
        return usucreacion;
    }

    public void setUsucreacion(Integer usucreacion) {
        this.usucreacion = usucreacion;
    }

    public Integer getUsumodificacion() {
        return usumodificacion;
    }

    public void setUsumodificacion(Integer usumodificacion) {
        this.usumodificacion = usumodificacion;
    }

    
}