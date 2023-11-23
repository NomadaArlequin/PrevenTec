package com.aqp.PrevenTecAppRest.Entity;

import java.util.Date;

public class clsSintoma {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private Integer usucreacion;
    private Integer usumodificacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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