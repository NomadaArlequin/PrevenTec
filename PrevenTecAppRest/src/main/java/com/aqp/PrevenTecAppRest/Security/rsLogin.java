/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.Security;
import com.aqp.PrevenTecAppRest.Config.clsCrearArchivoARFF;
import com.aqp.PrevenTecAppRest.Controller.clsLoginDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author Windows
 */
@RestController
@RequestMapping("/login")
public class rsLogin {
    
    @PostMapping
    private ResponseEntity<?> login(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {
        JSONObject varJObject = new JSONObject();
        try {            
            clsLoginDao LoginDao = new clsLoginDao();
            String varUsuAlias = varRequest.getParameter("usuario");
            String varUsuClave = varRequest.getParameter("password");

            varJObject = LoginDao.metLogin(varUsuAlias, varUsuClave);

            if (varJObject.getString("Result").equals("OK")) {
                Integer id = varJObject.getInt("id");
                varJObject.put("Message", "WELCOME");
                varSession.setAttribute("session_usu_codigo", id);
                varSession.setAttribute("session_nombre", varJObject.getString("nombre"));
            }
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", e.getMessage());
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
        //return (varJObject);
    }
    
    @PostMapping("/Crear")
    private ResponseEntity<?> Crear(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {
        JSONObject varJObject = new JSONObject();
        try {            
            clsCrearArchivoARFF CrearArchivoARFF = new clsCrearArchivoARFF();
            CrearArchivoARFF.Crear();
            
             varJObject.put("Result", "OK");
        } catch (Exception e) {
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", e.getMessage());
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
        //return (varJObject);
    }    
    
    
    
    @GetMapping("/close")
    private ResponseEntity<?> close(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {
        JSONObject varJObject = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        try {
            varSession.invalidate();
            varJObject.put("Result", "OK");
            varJObject.put("seccion", "OK");
        } catch (Exception e) {
            varJObject.put("Records", varJsonArrayP);
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }

    @GetMapping("/verEstado")
    private ResponseEntity<?> GetEstadoSession(HttpServletRequest varRequest, HttpServletResponse varResponse, HttpSession varSession) {
        JSONObject varJObject = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        try {
            //varSession.invalidate();
            if (varSession.getAttribute("session_usu_codigo") != null) {
                varJObject.put("Result", "OK");
                varJObject.put("session", varSession.getAttribute("session_usu_codigo"));
                varJObject.put("session", varSession.getAttribute("session_usu_codigo"));
            } else {
                varJObject.put("Result", "ERROR");
                varJObject.put("Message", "Se perdio su session");
            }

        } catch (Exception e) {
            varJObject.put("Records", varJsonArrayP);
            varJObject.put("Result", "ERROR");
            varJObject.put("Message", "Error srv");
            varJObject.put("numError", "-2");
        }
        return ResponseEntity.status(HttpStatus.OK).body(varJObject.toString());
    }
    
}
