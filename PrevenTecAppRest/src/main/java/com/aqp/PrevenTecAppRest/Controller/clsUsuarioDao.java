package com.aqp.PrevenTecAppRest.Controller;

import com.aqp.PrevenTecAppRest.Config.clsConexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aqp.PrevenTecAppRest.Entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.aqp.PrevenTecAppRest.Config.clsSuper;
import java.sql.SQLException;

public class clsUsuarioDao {

    private clsConexion varClsConexion;

    public clsUsuarioDao() {
        varClsConexion = new clsConexion();
    }

    public JSONObject ListAll() throws SQLException {
        Connection varConexion = null;
        ResultSet varResultado = null;
        PreparedStatement varPst = null;

        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();

        try {
            String varSql = "SELECT  x.id, x.tipodocumento_cod, x.documento, x.usuario, x.password, x.nombre, "
                    + "x.apellidopaterno, x.apellidomaterno, x.email, x.telefono, x.direccion, x.estado, "
                    + "x.usucreacion, x.feccreacion, x.usumodificacion, x.fecmodificacion , x.tipousuario_cod , x.persona_id "
                    + "FROM sk_persona.usuario as x;";

            varConexion = varClsConexion.getConexion();

            varPst = varConexion.prepareStatement(varSql);

            varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro = new JSONObject();
                varJsonObjectRegistro.put("id", varResultado.getString("id"));
                varJsonObjectRegistro.put("tipodocumento_cod", varResultado.getString("tipodocumento_cod"));
                varJsonObjectRegistro.put("documento", varResultado.getString("documento"));
                varJsonObjectRegistro.put("usuario", varResultado.getString("usuario"));
                varJsonObjectRegistro.put("password", varResultado.getString("password"));
                varJsonObjectRegistro.put("nombre", varResultado.getString("nombre"));
                varJsonObjectRegistro.put("apellidopaterno", varResultado.getString("apellidopaterno"));
                varJsonObjectRegistro.put("apellidomaterno", varResultado.getString("apellidomaterno"));
                varJsonObjectRegistro.put("email", varResultado.getString("email"));
                varJsonObjectRegistro.put("telefono", varResultado.getString("telefono"));
                varJsonObjectRegistro.put("direccion", varResultado.getString("direccion"));
                varJsonObjectRegistro.put("estado", varResultado.getBoolean("estado"));
                varJsonObjectRegistro.put("usucreacion", varResultado.getString("usucreacion"));
                varJsonObjectRegistro.put("feccreacion", varResultado.getString("feccreacion"));
                varJsonObjectRegistro.put("usumodificacion", varResultado.getString("usumodificacion"));
                varJsonObjectRegistro.put("fecmodificacion", varResultado.getString("fecmodificacion"));
                varJsonObjectRegistro.put("tipousuario_cod", varResultado.getString("tipousuario_cod"));
                varJsonObjectRegistro.put("persona_id", varResultado.getString("persona_id"));

                varJsonArrayP.put(varJsonObjectRegistro);
            }
            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("TotalRecordCount", varJsonArrayP.toList().size());
            varJsonObjectResultado.put("Records", varJsonArrayP);
        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e);
            varJsonObjectResultado.put("numError", "-3");
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

    public JSONObject ListPag(int varJtpageSize, int varJtStarIndex) throws SQLException {
        Connection varConexion = null;
        ResultSet varResultado = null;
        PreparedStatement varPst = null;

        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();

        try {
            String varSql = "SELECT  x.id "
                    + ", x.tipodocumento_cod "
                    + ", x.documento "
                    + ", x.usuario "
                    + ", x.password "
                    + ", x.nombre "
                    + ", x.apellidopaterno "
                    + ", x.apellidomaterno "
                    + ", x.email "
                    + ", x.telefono "
                    + ", x.direccion "
                    + ", x.estado "
                    + ", x.usucreacion "
                    + ", x.feccreacion "
                    + ", x.usumodificacion "
                    + ", x.fecmodificacion "
                    + ", x.tipousuario_cod "
                    + ", x.persona_id "
                    + " FROM sk_persona.usuario as x "
                    + " LIMIT ? OFFSET ? order by 2; ";

            varConexion = varClsConexion.getConexion();

            varPst = varConexion.prepareStatement(varSql);
            varPst.setInt(1, varJtpageSize);
            varPst.setInt(2, varJtStarIndex);

            varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro = new JSONObject();
                varJsonObjectRegistro.put("id", varResultado.getString("id"));
                varJsonObjectRegistro.put("tipodocumento_cod", varResultado.getString("tipodocumento_cod"));
                varJsonObjectRegistro.put("documento", varResultado.getString("documento"));
                varJsonObjectRegistro.put("usuario", varResultado.getString("usuario"));
                varJsonObjectRegistro.put("password", varResultado.getString("password"));
                varJsonObjectRegistro.put("nombre", varResultado.getString("nombre"));
                varJsonObjectRegistro.put("apellidopaterno", varResultado.getString("apellidopaterno"));
                varJsonObjectRegistro.put("apellidomaterno", varResultado.getString("apellidomaterno"));
                varJsonObjectRegistro.put("email", varResultado.getString("email"));
                varJsonObjectRegistro.put("telefono", varResultado.getString("telefono"));
                varJsonObjectRegistro.put("direccion", varResultado.getString("direccion"));
                varJsonObjectRegistro.put("estado", varResultado.getBoolean("estado"));
                varJsonObjectRegistro.put("usucreacion", varResultado.getString("usucreacion"));
                varJsonObjectRegistro.put("feccreacion", varResultado.getString("feccreacion"));
                varJsonObjectRegistro.put("usumodificacion", varResultado.getString("usumodificacion"));
                varJsonObjectRegistro.put("fecmodificacion", varResultado.getString("fecmodificacion"));
                varJsonObjectRegistro.put("tipousuario_cod", varResultado.getString("tipousuario_cod"));
                varJsonObjectRegistro.put("persona_id", varResultado.getString("persona_id"));
                varJsonArrayP.put(varJsonObjectRegistro);
            }
            Long varNumeroRegistros = Long.parseLong("0");
            varSql = "select count(*) as contador  from sk_persona.usuario;";

            varPst = varConexion.prepareStatement(varSql);
            varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varNumeroRegistros = varResultado.getLong("contador");
            }
            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("TotalRecordCount", varNumeroRegistros);
            varJsonObjectResultado.put("Records", varJsonArrayP);
        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e);
            varJsonObjectResultado.put("numError", "-3");
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

    public JSONObject save(clsUsuario varClass) throws SQLException {

        PreparedStatement varPst = null;
        ResultSet varResultado = null;
        Connection varConexion = null;

        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
            varConexion = varClsConexion.getConexion();
            String sql = "INSERT INTO  sk_persona.usuario (  tipodocumento_cod , documento , usuario ,"
                    + " password , nombre , apellidopaterno , apellidomaterno , email , telefono , "
                    + "direccion , estado , usucreacion, tipousuario_cod, persona_id ) values "
                    + "( ? , ? , ? ,md5( ?) , ? , ? , ? , ? , ? , ? , ? , ?,?,?)  RETURNING id  ; ";
            varPst = varConexion.prepareStatement(sql);

            varPst.setString(1, varClass.getTipodocumento_cod());
            varPst.setString(2, varClass.getDocumento());
            varPst.setString(3, varClass.getUsuario());
            varPst.setString(4, varClass.getPassword());
            varPst.setString(5, varClass.getNombre());
            varPst.setString(6, varClass.getApellidopaterno());
            varPst.setString(7, varClass.getApellidomaterno());
            varPst.setString(8, varClass.getEmail());
            varPst.setString(9, varClass.getTelefono());
            varPst.setString(10, varClass.getDireccion());
            varPst.setBoolean(11, varClass.getEstado());
            varPst.setInt(12, varClass.getUsucreacion());
            varPst.setString(13, varClass.getTipousuario_cod());
            varPst.setLong(14, varClass.getPersona_id());

            varResultado = varPst.executeQuery();
            if (varResultado.next()) {
                varJsonObjectRegistro.put("id", varResultado.getString("id"));
                varJsonObjectRegistro.put("tipodocumento_cod", varClass.getTipodocumento_cod());
                varJsonObjectRegistro.put("documento", varClass.getDocumento());
                varJsonObjectRegistro.put("usuario", varClass.getUsuario());
                varJsonObjectRegistro.put("password", varClass.getPassword());
                varJsonObjectRegistro.put("nombre", varClass.getNombre());
                varJsonObjectRegistro.put("apellidopaterno", varClass.getApellidopaterno());
                varJsonObjectRegistro.put("apellidomaterno", varClass.getApellidomaterno());
                varJsonObjectRegistro.put("email", varClass.getEmail());
                varJsonObjectRegistro.put("telefono", varClass.getTelefono());
                varJsonObjectRegistro.put("direccion", varClass.getDireccion());
                varJsonObjectRegistro.put("estado", varClass.getEstado());
                varJsonObjectRegistro.put("usucreacion", varClass.getUsucreacion());
                varJsonObjectRegistro.put("tipousuario_cod", varClass.getTipousuario_cod());
                varJsonObjectRegistro.put("persona_id", varClass.getPersona_id());
            }
            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("Record", varJsonObjectRegistro);

        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e);
            varJsonObjectResultado.put("numError", "-3");

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

    public JSONObject updateOld(clsUsuario varClass) throws SQLException {
        Connection varConexion = null;
        PreparedStatement varPst = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        try {
            String sql;
            sql = "UPDATE sk_persona.usuario SET "
                    + "tipodocumento_cod = ? , "
                    + "documento = ? , "
                    + "usuario = ? , "
                    + "password = ? , "
                    + "nombre = ? , "
                    + "apellidopaterno = ? , "
                    + "apellidomaterno = ? , "
                    + "email = ? , "
                    + "telefono = ? , "
                    + "direccion = ? , "
                    + "estado = ? , "
                    + "usumodificacion = ? , "
                    + "fecmodificacion = now() , "
                    + "tipousuario_cod = ? , "
                    + "persona_id = ?  "
                    + "WHERE id = ? ; ";

            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(sql);
            varPst.setString(1, varClass.getTipodocumento_cod());
            varPst.setString(2, varClass.getDocumento());
            varPst.setString(3, varClass.getUsuario());
            varPst.setString(4, varClass.getPassword());
            varPst.setString(5, varClass.getNombre());
            varPst.setString(6, varClass.getApellidopaterno());
            varPst.setString(7, varClass.getApellidomaterno());
            varPst.setString(8, varClass.getEmail());
            varPst.setString(9, varClass.getTelefono());
            varPst.setString(10, varClass.getDireccion());
            varPst.setBoolean(11, varClass.getEstado());
            varPst.setInt(12, varClass.getUsumodificacion());
            varPst.setString(13, varClass.getTipousuario_cod());
            varPst.setLong(14, varClass.getPersona_id());

            varPst.setInt(15, varClass.getId());
            varPst.executeUpdate();

            varJsonObjectRegistro.put("id", varClass.getId());
            varJsonObjectRegistro.put("tipodocumento_cod", varClass.getTipodocumento_cod());
            varJsonObjectRegistro.put("documento", varClass.getDocumento());
            varJsonObjectRegistro.put("usuario", varClass.getUsuario());
            varJsonObjectRegistro.put("password", varClass.getPassword());
            varJsonObjectRegistro.put("nombre", varClass.getNombre());
            varJsonObjectRegistro.put("apellidopaterno", varClass.getApellidopaterno());
            varJsonObjectRegistro.put("apellidomaterno", varClass.getApellidomaterno());
            varJsonObjectRegistro.put("email", varClass.getEmail());
            varJsonObjectRegistro.put("telefono", varClass.getTelefono());
            varJsonObjectRegistro.put("direccion", varClass.getDireccion());
            varJsonObjectRegistro.put("estado", varClass.getEstado());
            varJsonObjectRegistro.put("usumodificacion", varClass.getUsumodificacion());
            varJsonObjectRegistro.put("tipousuario_cod", varClass.getTipousuario_cod());

            varJsonArrayP.put(varJsonObjectRegistro);

            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("Record", varJsonObjectRegistro);
        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e);
            varJsonObjectResultado.put("numError", "-3");
        } finally {
            if (varConexion != null) {
                varConexion.close();
            }
            if (varPst != null) {
                varPst.close();
            }
        }

        return varJsonObjectResultado;
    }

    public JSONObject update(clsUsuario varClass) throws SQLException {
        Connection varConexion = null;
        PreparedStatement varPst = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        ResultSet varResultado = null;
        try {
            String sql;
            sql = "SELECT resultado , mensaje  , codigo  , pass from sk_persona.usuario_update"
                    + " ( ?,?,?,?,?,"
                    + "   ?,?,?,?,?,"
                    + "   ?,?,?,?,? "
                    + ");";

            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(sql);
            varPst.setInt(1, varClass.getId());
            varPst.setString(2, varClass.getTipodocumento_cod());
            varPst.setString(3, varClass.getDocumento());
            varPst.setString(4, varClass.getUsuario());
            varPst.setString(5, varClass.getPassword());
            varPst.setString(6, varClass.getNombre());
            varPst.setString(7, varClass.getApellidopaterno());
            varPst.setString(8, varClass.getApellidomaterno());
            varPst.setString(9, varClass.getEmail());
            varPst.setString(10, varClass.getTelefono());
            varPst.setString(11, varClass.getDireccion());
            varPst.setBoolean(12, varClass.getEstado());            
            varPst.setLong(13, varClass.getPersona_id());            
            varPst.setString(14, varClass.getTipousuario_cod());
            varPst.setInt(15, varClass.getUsumodificacion());


            varResultado = varPst.executeQuery();

            int varRespuesta = 0;
            String varMensajeError = "";
            while (varResultado.next()) {
                String pass = varResultado.getString("pass");

                varJsonObjectRegistro.put("id", varClass.getId());
                varJsonObjectRegistro.put("tipodocumento_cod", varClass.getTipodocumento_cod());
                varJsonObjectRegistro.put("documento", varClass.getDocumento());
                varJsonObjectRegistro.put("usuario", varClass.getUsuario());
                varJsonObjectRegistro.put("password", pass);
                varJsonObjectRegistro.put("nombre", varClass.getNombre());
                varJsonObjectRegistro.put("apellidopaterno", varClass.getApellidopaterno());
                varJsonObjectRegistro.put("apellidomaterno", varClass.getApellidomaterno());
                varJsonObjectRegistro.put("email", varClass.getEmail());
                varJsonObjectRegistro.put("telefono", varClass.getTelefono());
                varJsonObjectRegistro.put("direccion", varClass.getDireccion());
                varJsonObjectRegistro.put("estado", varClass.getEstado());
                varJsonObjectRegistro.put("usumodificacion", varClass.getUsumodificacion());
                varJsonObjectRegistro.put("tipousuario_cod", varClass.getTipousuario_cod());
            }

            varJsonArrayP.put(varJsonObjectRegistro);

            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("Record", varJsonObjectRegistro);
        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e);
            varJsonObjectResultado.put("numError", "-3");
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

    public JSONObject delete(clsUsuario varClass) throws SQLException {
        Connection varConexion = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        PreparedStatement varPst = null;
        try {
            String sql = "DELETE FROM sk_persona.usuario  WHERE id = ? ";
            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(sql);
            varPst.setInt(1, varClass.getId());
            varPst.executeUpdate();
            varJsonObjectResultado.put("Result", "OK");
        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e);
            varJsonObjectResultado.put("numError", "-3");
        } finally {
            if (varConexion != null) {
                varConexion.close();
            }
            if (varPst != null) {
                varPst.close();
            }
        }
        return varJsonObjectResultado;
    }

    public JSONObject getOption() throws SQLException {
        Connection varConexion = null;
        JSONObject varJsonObjectP = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        ResultSet varResultado = null;
        PreparedStatement varPst = null;

        varJsonObjectP.put("Value", "");
        varJsonObjectP.put("DisplayText", "");
        varJsonArrayP.put(varJsonObjectP);
        try {
            String varSql = "SELECT \n"
                    + " x.id as Value,\n"
                    + " x.nombre as DisplayText\n"
                    + " FROM sk_persona.usuario AS x\n"
                    + " ORDER BY 2;";
            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(varSql);

            varResultado = varPst.executeQuery();

            while (varResultado.next()) {
                varJsonObjectP = new JSONObject();
                varJsonObjectP.put("Value", varResultado.getString("Value"));
                varJsonObjectP.put("DisplayText", varResultado.getString("DisplayText"));
                varJsonArrayP.put(varJsonObjectP);
            }

            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("TotalRecordCount", varJsonArrayP.toList().size());

        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e.getMessage());
            varJsonObjectResultado.put("numError", "-3");
        } finally {
            if (varConexion != null) {
                varConexion.close();
            }
            if (varPst != null) {
                varPst.close();
            }
            if (varResultado != null) {
                varResultado.close();
            }
        }
        varJsonObjectResultado.put("Records", varJsonArrayP);

        return varJsonObjectResultado;
    }
}
