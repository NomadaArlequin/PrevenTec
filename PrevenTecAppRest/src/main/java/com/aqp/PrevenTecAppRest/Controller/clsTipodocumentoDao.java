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

public class clsTipodocumentoDao {

    private clsConexion varClsConexion;

    public clsTipodocumentoDao() {
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
            String varSql = "SELECT  x.codigo, x.nombre, x.abreviacion, x.min, x.max, x.tipo, x.estado, x.usucreacion, x.feccreacion, x.usumodificacion, x.fecmodificacion FROM sk_persona.tipodocumento as x;";

            varConexion = varClsConexion.getConexion();

            varPst = varConexion.prepareStatement(varSql);

            varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro = new JSONObject();
                varJsonObjectRegistro.put("codigo", varResultado.getString("codigo"));
                varJsonObjectRegistro.put("nombre", varResultado.getString("nombre"));
                varJsonObjectRegistro.put("abreviacion", varResultado.getString("abreviacion"));
                varJsonObjectRegistro.put("min", varResultado.getString("min"));
                varJsonObjectRegistro.put("max", varResultado.getString("max"));
                varJsonObjectRegistro.put("tipo", varResultado.getString("tipo"));
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
            String varSql = "SELECT  x.codigo "
                    + ", x.nombre "
                    + ", x.abreviacion "
                    + ", x.min "
                    + ", x.max "
                    + ", x.tipo "
                    + ", x.estado "
                    + ", x.usucreacion "
                    + ", x.feccreacion "
                    + ", x.usumodificacion "
                    + ", x.fecmodificacion "
                    + " FROM sk_persona.tipodocumento as x "
                    + " LIMIT ? OFFSET ? order by 2; ";

            varConexion = varClsConexion.getConexion();

            varPst = varConexion.prepareStatement(varSql);
            varPst.setInt(1, varJtpageSize);
            varPst.setInt(2, varJtStarIndex);

            varResultado = varPst.executeQuery();
            while (varResultado.next()) {
                varJsonObjectRegistro = new JSONObject();
                varJsonObjectRegistro.put("codigo", varResultado.getString("codigo"));
                varJsonObjectRegistro.put("nombre", varResultado.getString("nombre"));
                varJsonObjectRegistro.put("abreviacion", varResultado.getString("abreviacion"));
                varJsonObjectRegistro.put("min", varResultado.getString("min"));
                varJsonObjectRegistro.put("max", varResultado.getString("max"));
                varJsonObjectRegistro.put("tipo", varResultado.getString("tipo"));
                varJsonObjectRegistro.put("estado", varResultado.getBoolean("estado"));
                varJsonObjectRegistro.put("usucreacion", varResultado.getString("usucreacion"));
                varJsonObjectRegistro.put("feccreacion", varResultado.getString("feccreacion"));
                varJsonObjectRegistro.put("usumodificacion", varResultado.getString("usumodificacion"));
                varJsonObjectRegistro.put("fecmodificacion", varResultado.getString("fecmodificacion"));
                varJsonArrayP.put(varJsonObjectRegistro);
            }
            Long varNumeroRegistros = Long.parseLong("0");
            varSql = "select count(*) as contador  from sk_persona.tipodocumento;";

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

    public JSONObject save(clsTipodocumento varClass) throws SQLException {

        PreparedStatement varPst = null;
        ResultSet varResultado = null;
        Connection varConexion = null;

        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
            varConexion = varClsConexion.getConexion();
            String sql = "INSERT INTO  sk_persona.tipodocumento (  codigo , nombre , abreviacion , min , max , tipo , estado , usucreacion) values "
                    + "( ? , ? , ? , ? , ? , ? , ? , ?)  ; ";
            varPst = varConexion.prepareStatement(sql);

            varPst.setString(1, varClass.getCodigo());
            varPst.setString(2, varClass.getNombre());
            varPst.setString(3, varClass.getAbreviacion());
            varPst.setInt(4, varClass.getMin());
            varPst.setInt(5, varClass.getMax());
            varPst.setString(6, varClass.getTipo());
            varPst.setBoolean(7, varClass.getEstado());
            varPst.setInt(8, varClass.getUsucreacion());
            varPst.execute();
            varJsonObjectRegistro.put("codigo", varClass.getCodigo());
            varJsonObjectRegistro.put("nombre", varClass.getNombre());
            varJsonObjectRegistro.put("abreviacion", varClass.getAbreviacion());
            varJsonObjectRegistro.put("min", varClass.getMin());
            varJsonObjectRegistro.put("max", varClass.getMax());
            varJsonObjectRegistro.put("tipo", varClass.getTipo());
            varJsonObjectRegistro.put("estado", varClass.getEstado());
            varJsonObjectRegistro.put("usucreacion", varClass.getUsucreacion());
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

    public JSONObject update(clsTipodocumento varClass) throws SQLException {
        Connection varConexion = null;
        PreparedStatement varPst = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        try {
            String sql;
            sql = "UPDATE sk_persona.tipodocumento SET "
                    + "nombre = ? , "
                    + "abreviacion = ? , "
                    + "min = ? , "
                    + "max = ? , "
                    + "tipo = ? , "
                    + "estado = ? , "
                    + "usumodificacion = ? , "
                    + "fecmodificacion = now()  "
                    + "WHERE codigo = ? ; ";

            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(sql);
            varPst.setString(1, varClass.getNombre());
            varPst.setString(2, varClass.getAbreviacion());
            varPst.setInt(3, varClass.getMin());
            varPst.setInt(4, varClass.getMax());
            varPst.setString(5, varClass.getTipo());
            varPst.setBoolean(6, varClass.getEstado());
            varPst.setInt(7, varClass.getUsumodificacion());
            varPst.setString(8, varClass.getCodigo());
            varPst.executeUpdate();

            varJsonObjectRegistro.put("codigo", varClass.getCodigo());
            varJsonObjectRegistro.put("nombre", varClass.getNombre());
            varJsonObjectRegistro.put("abreviacion", varClass.getAbreviacion());
            varJsonObjectRegistro.put("min", varClass.getMin());
            varJsonObjectRegistro.put("max", varClass.getMax());
            varJsonObjectRegistro.put("tipo", varClass.getTipo());
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

    public JSONObject delete(clsTipodocumento varClass) throws SQLException {
        Connection varConexion = null;
        JSONObject varJsonObjectResultado = new JSONObject();
        PreparedStatement varPst = null;
        try {
            String sql = "DELETE FROM sk_persona.tipodocumento  WHERE codigo = ? ";
            varConexion = varClsConexion.getConexion();
            varPst = varConexion.prepareStatement(sql);
            varPst.setString(1, varClass.getCodigo());
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
                    + " x.codigo as Value,\n"
                    + " x.nombre as DisplayText\n"
                    + " FROM sk_persona.tipodocumento AS x\n"
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
