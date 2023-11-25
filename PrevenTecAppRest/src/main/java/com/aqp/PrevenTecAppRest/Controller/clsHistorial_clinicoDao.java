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

public class clsHistorial_clinicoDao {

    private clsConexion varClsConexion;

    public clsHistorial_clinicoDao() {
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
            String varSql = "SELECT  x.id, x.persona_id, x.enfermo_id, x.longitud, x.latitud, x.descripcion, x.estado, x.usucreacion, x.feccreacion, x.usumodificacion, x.fecmodificacion FROM sk_general.historial_clinico as x;";

            varConexion = varClsConexion.getConexion();

            varPst = varConexion.prepareStatement(varSql);

            varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro = new JSONObject();
                varJsonObjectRegistro.put("id", varResultado.getString("id"));
                varJsonObjectRegistro.put("persona_id", varResultado.getString("persona_id"));
                varJsonObjectRegistro.put("enfermo_id", varResultado.getString("enfermo_id"));
                varJsonObjectRegistro.put("longitud", varResultado.getString("longitud"));
                varJsonObjectRegistro.put("latitud", varResultado.getString("latitud"));
                varJsonObjectRegistro.put("descripcion", varResultado.getString("descripcion"));
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
                    + ", x.persona_id "
                    + ", x.enfermo_id "
                    + ", x.longitud "
                    + ", x.latitud "
                    + ", x.descripcion "
                    + ", x.estado "
                    + ", x.usucreacion "
                    + ", x.feccreacion "
                    + ", x.usumodificacion "
                    + ", x.fecmodificacion "
                    + " FROM sk_general.historial_clinico as x "
                    + " LIMIT ? OFFSET ? order by 2; ";

            varConexion = varClsConexion.getConexion();

            varPst = varConexion.prepareStatement(varSql);
            varPst.setInt(1, varJtpageSize);
            varPst.setInt(2, varJtStarIndex);

            varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro = new JSONObject();
                varJsonObjectRegistro.put("id", varResultado.getString("id"));
                varJsonObjectRegistro.put("persona_id", varResultado.getString("persona_id"));
                varJsonObjectRegistro.put("enfermo_id", varResultado.getString("enfermo_id"));
                varJsonObjectRegistro.put("longitud", varResultado.getString("longitud"));
                varJsonObjectRegistro.put("latitud", varResultado.getString("latitud"));
                varJsonObjectRegistro.put("descripcion", varResultado.getString("descripcion"));
                varJsonObjectRegistro.put("estado", varResultado.getBoolean("estado"));
                varJsonObjectRegistro.put("usucreacion", varResultado.getString("usucreacion"));
                varJsonObjectRegistro.put("feccreacion", varResultado.getString("feccreacion"));
                varJsonObjectRegistro.put("usumodificacion", varResultado.getString("usumodificacion"));
                varJsonObjectRegistro.put("fecmodificacion", varResultado.getString("fecmodificacion"));
                varJsonArrayP.put(varJsonObjectRegistro);
            }
            Long varNumeroRegistros = Long.parseLong("0");
            varSql = "select count(*) as contador  from sk_general.historial_clinico;";

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

    public JSONObject save(clsHistorial_clinico varClass) throws SQLException {

        PreparedStatement varPst = null;
        ResultSet varResultado = null;
        Connection varConexion = null;

        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
            varConexion = varClsConexion.getConexion();
            String sql = "INSERT INTO  sk_general.historial_clinico (  persona_id , enfermo_id , longitud , latitud , descripcion , estado , usucreacion) values "
                    + "( ? , ? , ? , ? , ? , ? , ?)  RETURNING id  ; ";
            varPst = varConexion.prepareStatement(sql);

            varPst.setLong(1, varClass.getPersona_id());
            varPst.setLong(2, varClass.getEnfermo_id());
            varPst.setString(3, varClass.getLongitud());
            varPst.setString(4, varClass.getLatitud());
            varPst.setString(5, varClass.getDescripcion());
            varPst.setBoolean(6, varClass.getEstado());
            varPst.setInt(7, varClass.getUsucreacion());
            varResultado = varPst.executeQuery();
            if (varResultado.next()) {
                varJsonObjectRegistro.put("id", varResultado.getString("id"));
                varJsonObjectRegistro.put("persona_id", varClass.getPersona_id());
                varJsonObjectRegistro.put("enfermo_id", varClass.getEnfermo_id());
                varJsonObjectRegistro.put("longitud", varClass.getLongitud());
                varJsonObjectRegistro.put("latitud", varClass.getLatitud());
                varJsonObjectRegistro.put("descripcion", varClass.getDescripcion());
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

    public JSONObject update(clsHistorial_clinico varClass) throws SQLException {
        Connection varConexion = null;
        PreparedStatement varPst = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        try {
            String sql;
            sql = "UPDATE sk_general.historial_clinico SET "
                    + "persona_id = ? , "
                    + "enfermo_id = ? , "
                    + "longitud = ? , "
                    + "latitud = ? , "
                    + "descripcion = ? , "
                    + "estado = ? , "
                    + "usumodificacion = ? , "
                    + "fecmodificacion = now()  "
                    + "WHERE id = ? ; ";

            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(sql);
            varPst.setLong(1, varClass.getPersona_id());
            varPst.setLong(2, varClass.getEnfermo_id());
            varPst.setString(3, varClass.getLongitud());
            varPst.setString(4, varClass.getLatitud());
            varPst.setString(5, varClass.getDescripcion());
            varPst.setBoolean(6, varClass.getEstado());
            varPst.setInt(7, varClass.getUsumodificacion());
            varPst.setLong(8, varClass.getId());
            varPst.executeUpdate();

            varJsonObjectRegistro.put("id", varClass.getId());
            varJsonObjectRegistro.put("persona_id", varClass.getPersona_id());
            varJsonObjectRegistro.put("enfermo_id", varClass.getEnfermo_id());
            varJsonObjectRegistro.put("longitud", varClass.getLongitud());
            varJsonObjectRegistro.put("latitud", varClass.getLatitud());
            varJsonObjectRegistro.put("descripcion", varClass.getDescripcion());
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

    public JSONObject delete(clsHistorial_clinico varClass) throws SQLException {
        Connection varConexion = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        PreparedStatement varPst = null;
        try {
            String sql = "DELETE FROM sk_general.historial_clinico  WHERE id = ? ";
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
                    + " x.descripcion as DisplayText\n"
                    + " FROM sk_general.historial_clinico AS x\n"
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
