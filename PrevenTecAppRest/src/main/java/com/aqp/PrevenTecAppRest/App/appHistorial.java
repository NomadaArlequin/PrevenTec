/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.App;

import com.aqp.PrevenTecAppRest.Config.clsDiagnosticoEnfermedadesValles;
import com.aqp.PrevenTecAppRest.Entity.*;
import com.aqp.PrevenTecAppRest.Controller.*;
import com.aqp.PrevenTecAppRest.Config.clsSuper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Historial")
public class appHistorial {

    @PostMapping
    public ResponseEntity<?> Insert(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {
        clsHistorial_clinicoDao Historial_clinicoDao = new clsHistorial_clinicoDao();
        JSONObject varJObject = new JSONObject();
        try {

            Integer varSessionUsuCodigo = Integer.parseInt(varSession.getAttribute("session_usu_codigo").toString());
            Long varPersona_id = Long.parseLong(varSession.getAttribute("session_persona_id").toString());

            clsPersona varClass = new clsPersona();
            varClass.setTipodocumento_cod(varRequest.getParameter("tipodocumento_cod"));
            varClass.setNumdocumento(varRequest.getParameter("numdocumento"));
            varClass.setNombre(varRequest.getParameter("nombre"));
            varClass.setApepaterno(varRequest.getParameter("apepaterno"));
            varClass.setApematerno(varRequest.getParameter("apematerno"));
            varClass.setDireccion(varRequest.getParameter("direccion"));
            varClass.setEmail(varRequest.getParameter("email"));
            varClass.setTelefono(varRequest.getParameter("telefono"));
            varClass.setFecnacimiento(clsSuper.metDateEscojer((varRequest.getParameter("fecnacimiento"))));
            varClass.setSexo_id(clsSuper.metInteger(varRequest.getParameter("sexo_id")));

            clsHistorial_clinico Historial_clinico = new clsHistorial_clinico();

            Historial_clinico.setPersona_id(varPersona_id);
            Historial_clinico.setLatitud(varRequest.getParameter("latitud"));
            Historial_clinico.setLongitud(varRequest.getParameter("longitud"));
            Historial_clinico.setDescripcion(varRequest.getParameter("descripcion"));
            Historial_clinico.setUsucreacion(varSessionUsuCodigo);
            String varJson= varRequest.getParameter("varJson");

            varJObject = Historial_clinicoDao.save_pro(varClass,Historial_clinico,varJson);
            
            
            if(varJObject.getString("Result").equals("OK")){                
                Long codigo= varJObject.getLong("codigo");
                clsDiagnosticoEnfermedadesValles clsDiagnosticoEnfermedadesValles= new clsDiagnosticoEnfermedadesValles();
                JSONObject varJObjectEnfermedad = new JSONObject();
                
                varJObjectEnfermedad=clsDiagnosticoEnfermedadesValles.datosProbables(codigo);
                JSONArray varJsonDiagnostico= varJObjectEnfermedad.getJSONArray("Records");
                
            }

        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

}
