package com.aqp.PrevenTecAppRest.Entity;

import java.util.Date;

public class clsHistorial_sintomas {
    
    private Long id;
    private Long historial_clinico_id;
    private Integer sintoma_id;
    private Boolean validado;
    private Boolean estado;
    private Integer usucreacion;
    private Integer usumodificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHistorial_clinico_id() {
        return historial_clinico_id;
    }

    public void setHistorial_clinico_id(Long historial_clinico_id) {
        this.historial_clinico_id = historial_clinico_id;
    }

    public Integer getSintoma_id() {
        return sintoma_id;
    }

    public void setSintoma_id(Integer sintoma_id) {
        this.sintoma_id = sintoma_id;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
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