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

public class clsPersonaDao {

    private clsConexion varClsConexion;

    public clsPersonaDao() {
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
            String varSql = "SELECT  x.id, x.tipodocumento_cod, x.numdocumento, x.nombre, x.apepaterno, x.apematerno, x.direccion, x.email, x.telefono, x.observacion, x.fecnacimiento, x.estado, x.usucreacion, x.feccreacion, x.usumodificacion, x.fecmodificacion FROM sk_persona.persona as x;";

            varConexion = varClsConexion.getConexion();

            varPst = varConexion.prepareStatement(varSql);

            varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro = new JSONObject();
                varJsonObjectRegistro.put("id", varResultado.getString("id"));
                varJsonObjectRegistro.put("tipodocumento_cod", varResultado.getString("tipodocumento_cod"));
                varJsonObjectRegistro.put("numdocumento", varResultado.getString("numdocumento"));
                varJsonObjectRegistro.put("nombre", varResultado.getString("nombre"));
                varJsonObjectRegistro.put("apepaterno", varResultado.getString("apepaterno"));
                varJsonObjectRegistro.put("apematerno", varResultado.getString("apematerno"));
                varJsonObjectRegistro.put("direccion", varResultado.getString("direccion"));
                varJsonObjectRegistro.put("email", varResultado.getString("email"));
                varJsonObjectRegistro.put("telefono", varResultado.getString("telefono"));
                varJsonObjectRegistro.put("observacion", varResultado.getString("observacion"));
                varJsonObjectRegistro.put("fecnacimiento", varResultado.getString("fecnacimiento"));
                varJsonObjectRegistro.put("estado", varResultado.getBoolean("estado"));
                varJsonObjectRegistro.put("usucreacion", varResultado.getString("usucreacion"));
                varJsonObjectRegistro.put("feccreacion", varResultado.getString("feccreacion"));
                varJsonObjectRegistro.put("usumodificacion", varResultado.getString("usumodificacion"));
                varJsonObjectRegistro.put("fecmodificacion", varResultado.getString("fecmodificacion"));
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
                    + ", x.numdocumento "
                    + ", x.nombre "
                    + ", x.apepaterno "
                    + ", x.apematerno "
                    + ", x.direccion "
                    + ", x.email "
                    + ", x.telefono "
                    + ", x.observacion "
                    + ", x.fecnacimiento "
                    + ", x.estado "
                    + ", x.usucreacion "
                    + ", x.feccreacion "
                    + ", x.usumodificacion "
                    + ", x.fecmodificacion "
                    + " FROM sk_persona.persona as x "
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
                varJsonObjectRegistro.put("numdocumento", varResultado.getString("numdocumento"));
                varJsonObjectRegistro.put("nombre", varResultado.getString("nombre"));
                varJsonObjectRegistro.put("apepaterno", varResultado.getString("apepaterno"));
                varJsonObjectRegistro.put("apematerno", varResultado.getString("apematerno"));
                varJsonObjectRegistro.put("direccion", varResultado.getString("direccion"));
                varJsonObjectRegistro.put("email", varResultado.getString("email"));
                varJsonObjectRegistro.put("telefono", varResultado.getString("telefono"));
                varJsonObjectRegistro.put("observacion", varResultado.getString("observacion"));
                varJsonObjectRegistro.put("fecnacimiento", varResultado.getString("fecnacimiento"));
                varJsonObjectRegistro.put("estado", varResultado.getBoolean("estado"));
                varJsonObjectRegistro.put("usucreacion", varResultado.getString("usucreacion"));
                varJsonObjectRegistro.put("feccreacion", varResultado.getString("feccreacion"));
                varJsonObjectRegistro.put("usumodificacion", varResultado.getString("usumodificacion"));
                varJsonObjectRegistro.put("fecmodificacion", varResultado.getString("fecmodificacion"));
                varJsonArrayP.put(varJsonObjectRegistro);
            }
            Long varNumeroRegistros = Long.parseLong("0");
            varSql = "select count(*) as contador  from sk_persona.persona;";

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

    public JSONObject save(clsPersona varClass) throws SQLException {

        PreparedStatement varPst = null;
        ResultSet varResultado = null;
        Connection varConexion = null;

        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
            varConexion = varClsConexion.getConexion();
            String sql = "INSERT INTO  sk_persona.persona (  tipodocumento_cod , numdocumento , nombre , apepaterno , apematerno , direccion , email , telefono , observacion , fecnacimiento , estado , usucreacion) values "
                    + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , cast(? as timestamp) , ? , ?)  RETURNING id  ; ";
            varPst = varConexion.prepareStatement(sql);

            varPst.setString(1, varClass.getTipodocumento_cod());
            varPst.setString(2, varClass.getNumdocumento());
            varPst.setString(3, varClass.getNombre());
            varPst.setString(4, varClass.getApepaterno());
            varPst.setString(5, varClass.getApematerno());
            varPst.setString(6, varClass.getDireccion());
            varPst.setString(7, varClass.getEmail());
            varPst.setString(8, varClass.getTelefono());
            varPst.setString(9, varClass.getObservacion());
            varPst.setString(10, clsSuper.metDateSqlString(varClass.getFecnacimiento()));
            varPst.setBoolean(11, varClass.getEstado());
            varPst.setInt(12, varClass.getUsucreacion());
            varResultado = varPst.executeQuery();
            if (varResultado.next()) {
                varJsonObjectRegistro.put("id", varResultado.getString("id"));
                varJsonObjectRegistro.put("tipodocumento_cod", varClass.getTipodocumento_cod());
                varJsonObjectRegistro.put("numdocumento", varClass.getNumdocumento());
                varJsonObjectRegistro.put("nombre", varClass.getNombre());
                varJsonObjectRegistro.put("apepaterno", varClass.getApepaterno());
                varJsonObjectRegistro.put("apematerno", varClass.getApematerno());
                varJsonObjectRegistro.put("direccion", varClass.getDireccion());
                varJsonObjectRegistro.put("email", varClass.getEmail());
                varJsonObjectRegistro.put("telefono", varClass.getTelefono());
                varJsonObjectRegistro.put("observacion", varClass.getObservacion());
                varJsonObjectRegistro.put("fecnacimiento", varClass.getFecnacimiento());
                varJsonObjectRegistro.put("estado", varClass.getEstado());
                varJsonObjectRegistro.put("usucreacion", varClass.getUsucreacion());
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

    public JSONObject update(clsPersona varClass) throws SQLException {
        Connection varConexion = null;
        PreparedStatement varPst = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        try {
            String sql;
            sql = "UPDATE sk_persona.persona SET "
                    + "tipodocumento_cod = ? , "
                    + "numdocumento = ? , "
                    + "nombre = ? , "
                    + "apepaterno = ? , "
                    + "apematerno = ? , "
                    + "direccion = ? , "
                    + "email = ? , "
                    + "telefono = ? , "
                    + "observacion = ? , "
                    + "fecnacimiento = cast(? as timestamp) , "
                    + "estado = ? , "
                    + "usumodificacion = ? , "
                    + "fecmodificacion = now()  "
                    + "WHERE id = ? ; ";

            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(sql);
            varPst.setString(1, varClass.getTipodocumento_cod());
            varPst.setString(2, varClass.getNumdocumento());
            varPst.setString(3, varClass.getNombre());
            varPst.setString(4, varClass.getApepaterno());
            varPst.setString(5, varClass.getApematerno());
            varPst.setString(6, varClass.getDireccion());
            varPst.setString(7, varClass.getEmail());
            varPst.setString(8, varClass.getTelefono());
            varPst.setString(9, varClass.getObservacion());
            varPst.setString(10, clsSuper.metDateSqlString(varClass.getFecnacimiento()));
            varPst.setBoolean(11, varClass.getEstado());
            varPst.setInt(12, varClass.getUsumodificacion());
            varPst.setLong(13, varClass.getId());
            varPst.executeUpdate();

            varJsonObjectRegistro.put("id", varClass.getId());
            varJsonObjectRegistro.put("tipodocumento_cod", varClass.getTipodocumento_cod());
            varJsonObjectRegistro.put("numdocumento", varClass.getNumdocumento());
            varJsonObjectRegistro.put("nombre", varClass.getNombre());
            varJsonObjectRegistro.put("apepaterno", varClass.getApepaterno());
            varJsonObjectRegistro.put("apematerno", varClass.getApematerno());
            varJsonObjectRegistro.put("direccion", varClass.getDireccion());
            varJsonObjectRegistro.put("email", varClass.getEmail());
            varJsonObjectRegistro.put("telefono", varClass.getTelefono());
            varJsonObjectRegistro.put("observacion", varClass.getObservacion());
            varJsonObjectRegistro.put("fecnacimiento", varClass.getFecnacimiento());
            varJsonObjectRegistro.put("estado", varClass.getEstado());
            varJsonObjectRegistro.put("usumodificacion", varClass.getUsumodificacion());

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

    public JSONObject delete(clsPersona varClass) throws SQLException {
        Connection varConexion = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        PreparedStatement varPst = null;
        try {
            String sql = "DELETE FROM sk_persona.persona  WHERE id = ? ";
            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(sql);
            varPst.setLong(1, varClass.getId());
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
                    + " FROM sk_persona.persona AS x\n"
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
