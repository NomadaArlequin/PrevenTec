package com.aqp.PrevenTecAppRest.Entity;

import java.util.Date;

public class clsPersona {
    
    private Long id;
    private String tipodocumento_cod;
    private String numdocumento;
    private String nombre;
    private String apepaterno;
    private String apematerno;
    private String direccion;
    private String email;
    private String telefono;
    private String observacion;
    private Date fecnacimiento;
    private Boolean estado;
    private Integer usucreacion;
    private Integer usumodificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipodocumento_cod() {
        return tipodocumento_cod;
    }

    public void setTipodocumento_cod(String tipodocumento_cod) {
        this.tipodocumento_cod = tipodocumento_cod;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApepaterno() {
        return apepaterno;
    }

    public void setApepaterno(String apepaterno) {
        this.apepaterno = apepaterno;
    }

    public String getApematerno() {
        return apematerno;
    }

    public void setApematerno(String apematerno) {
        this.apematerno = apematerno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecnacimiento() {
        return fecnacimiento;
    }

    public void setFecnacimiento(Date fecnacimiento) {
        this.fecnacimiento = fecnacimiento;
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