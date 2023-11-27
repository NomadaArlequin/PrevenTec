/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.App;

import com.aqp.PrevenTecAppRest.Entity.*;
import com.aqp.PrevenTecAppRest.Controller.*;
import com.aqp.PrevenTecAppRest.Config.clsSuper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/Usuario")
public class appUsuario {
    
    @GetMapping
    public ResponseEntity<?> Select(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {

        clsUsuarioDao UsuarioDao = new clsUsuarioDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = UsuarioDao.ListAll();
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @GetMapping("/pag")
    public ResponseEntity<?> SelectPag(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {

        clsUsuarioDao UsuarioDao = new clsUsuarioDao();
        JSONObject varJObject = new JSONObject();
        try {
            Integer varJtStartIndex = Integer.parseInt(varRequest.getParameter("jtStartIndex").toString());
            Integer varJtPageSize = Integer.parseInt(varRequest.getParameter("jtPageSize").toString());
            varJObject = UsuarioDao.ListPag(varJtPageSize, varJtStartIndex);
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @PostMapping
    public ResponseEntity<?> Insert(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {

        clsUsuarioDao UsuarioDao = new clsUsuarioDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = UsuarioDao.save(objeto(varRequest, varSession));
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @PutMapping
    public ResponseEntity<?> Update(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {

        clsUsuarioDao UsuarioDao = new clsUsuarioDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = UsuarioDao.update(objeto(varRequest, varSession));
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @DeleteMapping
    private ResponseEntity<?> Delete(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {
        clsUsuarioDao UsuarioDao = new clsUsuarioDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = UsuarioDao.delete(objeto(varRequest, varSession));
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }
    
    private clsUsuario objeto( HttpServletRequest varRequest, HttpSession varSession) {
         Integer varSessionUsuCodigo = Integer.parseInt(varSession.getAttribute("session_usu_codigo").toString());
        clsUsuario varClass = new clsUsuario();
        varClass.setId(clsSuper.metInteger(varRequest.getParameter("id")));
        varClass.setTipodocumento_cod(varRequest.getParameter("tipodocumento_cod"));
        varClass.setDocumento(varRequest.getParameter("documento"));
        varClass.setUsuario(varRequest.getParameter("usuario"));
        varClass.setPassword(varRequest.getParameter("password"));
        varClass.setNombre(varRequest.getParameter("nombre"));
        varClass.setApellidopaterno(varRequest.getParameter("apellidopaterno"));
        varClass.setApellidomaterno(varRequest.getParameter("apellidomaterno"));
        varClass.setEmail(varRequest.getParameter("email"));
        varClass.setTelefono(varRequest.getParameter("telefono"));
        varClass.setDireccion(varRequest.getParameter("direccion"));
        varClass.setEstado(clsSuper.metBoolean(varRequest.getParameter("estado")));
        
        varClass.setTipousuario_cod(varRequest.getParameter("tipousuario_cod"));        
        varClass.setPersona_id(clsSuper.metLong(varRequest.getParameter("persona_id")));        
        
        varClass.setUsucreacion(varSessionUsuCodigo);
        varClass.setUsumodificacion(varSessionUsuCodigo);
        return varClass;
    }
    
    

    @GetMapping("/getOption")
    public ResponseEntity<?> metOptionsALL() {
        clsUsuarioDao varUsuarioDao = new clsUsuarioDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = varUsuarioDao.getOption();
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }
    
}
