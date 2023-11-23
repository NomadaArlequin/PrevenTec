/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.Security;
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
}
