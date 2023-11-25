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
@RequestMapping("/Tipodocumento")
public class appTipodocumento {
    

    @GetMapping
    public ResponseEntity<?> Select(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {

        clsTipodocumentoDao TipodocumentoDao = new clsTipodocumentoDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = TipodocumentoDao.ListAll();
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @GetMapping("/pag")
    public ResponseEntity<?> SelectPag(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {

        clsTipodocumentoDao TipodocumentoDao = new clsTipodocumentoDao();
        JSONObject varJObject = new JSONObject();
        try {
            Integer varJtStartIndex = Integer.parseInt(varRequest.getParameter("jtStartIndex").toString());
            Integer varJtPageSize = Integer.parseInt(varRequest.getParameter("jtPageSize").toString());
            varJObject = TipodocumentoDao.ListPag(varJtPageSize, varJtStartIndex);
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @PostMapping
    public ResponseEntity<?> Insert(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {

        clsTipodocumentoDao TipodocumentoDao = new clsTipodocumentoDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = TipodocumentoDao.save(objeto(varRequest, varSession));
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
            e.printStackTrace();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @PutMapping
    public ResponseEntity<?> Update(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {

        clsTipodocumentoDao TipodocumentoDao = new clsTipodocumentoDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = TipodocumentoDao.update(objeto(varRequest, varSession));
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @DeleteMapping
    private ResponseEntity<?> Delete(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {
        clsTipodocumentoDao TipodocumentoDao = new clsTipodocumentoDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = TipodocumentoDao.delete(objeto(varRequest, varSession));
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    private clsTipodocumento objeto(HttpServletRequest varRequest, HttpSession varSession) {
       Integer varSessionUsuCodigo = Integer.parseInt(varSession.getAttribute("session_usu_codigo").toString());
        clsTipodocumento varClass = new clsTipodocumento();
        varClass.setCodigo(varRequest.getParameter("codigo"));
        varClass.setNombre(varRequest.getParameter("nombre"));
        varClass.setAbreviacion(varRequest.getParameter("abreviacion"));
        varClass.setMin(clsSuper.metInteger(varRequest.getParameter("min")));
        varClass.setMax(clsSuper.metInteger(varRequest.getParameter("max")));
        varClass.setTipo(varRequest.getParameter("tipo"));
        varClass.setEstado(clsSuper.metBoolean(varRequest.getParameter("estado")));
        varClass.setUsucreacion(varSessionUsuCodigo);
        varClass.setUsumodificacion(varSessionUsuCodigo);
        return varClass;
    }

    @GetMapping("/getOption")
    public ResponseEntity<?> metOptionsALL() {
        clsTipodocumentoDao TipodocumentoDao = new clsTipodocumentoDao();
        JSONObject varJObject = new JSONObject();
        try {
            varJObject = TipodocumentoDao.getOption();
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }    
    
}
