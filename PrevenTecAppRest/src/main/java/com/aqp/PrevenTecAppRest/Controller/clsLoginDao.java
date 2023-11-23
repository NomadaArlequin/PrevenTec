/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.Controller;

import com.aqp.PrevenTecAppRest.Config.clsConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;
public class clsLoginDao {
    
    private clsConexion varClsConexion;

    public clsLoginDao() {
        varClsConexion = new clsConexion();
    }

    public JSONObject metLogin(String varUsuAlias, String varUsuClave) throws SQLException {
        JSONObject varJsonObjectResultado = new JSONObject();
        ResultSet varResultado = null;
        PreparedStatement varPst = null;
        Connection varConexion = null;
        try {
            String varSql = "select  resultado, mensaje, codigo , nombre "
                    + " from \n"
                    + "sk_persona.pro_login(?, ?);";
            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(varSql);
            varPst.setString(1, varUsuAlias);
            varPst.setString(2, varUsuClave);

            Integer resultado = 0;
            String mensaje = "Error de conexion ";
            String codigo = "0";
            String nombre = "";
            varResultado = varPst.executeQuery();

            if (varResultado.next()) {
                resultado = varResultado.getInt("resultado");
                mensaje = varResultado.getString("mensaje");
                codigo = varResultado.getString("codigo");              
                nombre = varResultado.getString("nombre");                              
            }

            if (resultado != 0) {
                varJsonObjectResultado.put("Result", "OK");
                varJsonObjectResultado.put("id", codigo);
                varJsonObjectResultado.put("nombre", nombre);              
            } else {
                varJsonObjectResultado.put("Result", "ERROR");
                varJsonObjectResultado.put("Message", mensaje);
            }
        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e.getMessage());
        } finally {
            if (varConexion != null) {
                varConexion.close();
            }
            if (varResultado != null) {
                varResultado.close();
            }
            if (varPst != null) {
                varPst.close();
            }
        }
        return varJsonObjectResultado;
    }

}
