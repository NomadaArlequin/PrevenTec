/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.Config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Windows
 */
public class clsSuper {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String path() {
        if (isWindows()) {
            return "C:/PrevenTec/";
        } else if (isMac()) {
            return "/home/Arconte/PrevenTec/";
        } else if (isUnix()) {
            return "/home/Arconte/PrevenTec/";
        } else if (isSolaris()) {
            return "/home/Arconte/PrevenTec/";
        } else {
            return null;
        }
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    private static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    private static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

    public static String metDateSqlString(Date varFechaFormateada) {
        SimpleDateFormat ingreso = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = null;
        try {
            fecha = ingreso.format(varFechaFormateada);
        } catch (Exception e) {
            ////System.out.println("error super fecha " + e);
        }
        return fecha;

    }

    public static Date metDate(String varFechaFormateada) {
        SimpleDateFormat ingreso = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = ingreso.parse(varFechaFormateada);
        } catch (Exception e) {
            ////System.out.println("error super fecha " + e);
        }
        return fecha;

    }

    public static Integer metInteger(String varCadena) {
        Integer varRetur = null;
        try {
            varRetur = Integer.parseInt(varCadena);
        } catch (Exception e) {
            ////System.out.println("error super fecha " + e);
        }
        return varRetur;

    }

    public static Boolean metBoolean(String varCadena) {
        Boolean varRetur = null;
        try {
            varRetur = Boolean.parseBoolean(varCadena);
        } catch (Exception e) {
            ////System.out.println("error super fecha " + e);
        }
        return varRetur;

    }

    public static Double metDouble(String varCadena) {
        Double varRetur = null;
        try {
            varRetur = Double.parseDouble(varCadena);
        } catch (Exception e) {
            ////System.out.println("error super fecha " + e);
        }
        return varRetur;

    }

    public static Long metLong(String varCadena) {
        Long varRetur = null;
        try {
            varRetur = Long.parseLong(varCadena);
        } catch (Exception e) {
            ////System.out.println("error super fecha " + e);
        }
        return varRetur;

    }

    public static Date metDateEscojer(String varFechaFormateada) {
        SimpleDateFormat ingreso = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = ingreso.parse(varFechaFormateada);
        } catch (Exception e) {
            ////System.out.println("error super fecha " + e);
        }
        return fecha;

    }

    public static Date metDateEscojer2(String varFechaFormateada) {
        SimpleDateFormat ingreso = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = ingreso.parse(varFechaFormateada);
        } catch (Exception e) {
            ////System.out.println("error super fecha " + e);
        }
        return fecha;

    }
}
