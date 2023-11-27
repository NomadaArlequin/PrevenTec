/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.Config;

import com.aqp.PrevenTecAppRest.Controller.clsEnfermedadDao;
import com.aqp.PrevenTecAppRest.Controller.clsEnfermedad_sintomaDao;
import com.aqp.PrevenTecAppRest.Controller.clsSintomaDao;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import weka.core.FastVector;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Windows
 */
public class clsCrearArchivoARFF {

    public void CrearOld() {
        try {

            // Definir los atributos (síntomas y enfermedades)
            /* Attribute fiebre = new Attribute("fiebre", 2); // Atributo nominal con 2 valores: si/no
        Attribute tos = new Attribute("tos", 2);
        Attribute dolorGarganta = new Attribute("dolor_garganta", 2);
        Attribute congestiónNasal = new Attribute("congestion_nasal", 2);
        Attribute enfermedad = new Attribute("enfermedad", 3); // Atributo nominal con 3 valores: gripe, resfriado, alergia
             */
            Attribute enfermedad = new Attribute("enfermedad", 3); // Atributo nominal con 3 valores: gripe, resfriado, alergia
            //List<Attribute> nuevo=new ArrayList<>();
            clsEnfermedadDao EnfermedadDao = new clsEnfermedadDao();
            JSONObject Enfermedades = EnfermedadDao.ListAll();

            JSONArray EnfermedadRecords = Enfermedades.getJSONArray("Records");
            FastVector atributos = new FastVector();
            for (int i = 0; i < EnfermedadRecords.length(); i++) {
                JSONObject childJSONObject = EnfermedadRecords.getJSONObject(i);
                String enfermedad_id = childJSONObject.getString("id");
                Attribute Emfermedad_cod = new Attribute(enfermedad_id, 2);
                atributos.addElement(Emfermedad_cod);
            }

            // Crear el conjunto de datos ARFF
            Instances dataset = new Instances("DiagnosticoEnfermedades", atributos, 0);
            dataset.setClass(enfermedad); // Establecer el atributo clase (enfermedad)

            // Agregar instancias al conjunto de datos
            dataset.add(new DenseInstance(1.0, new double[]{dataset.attribute("fiebre").indexOfValue("si"),
                dataset.attribute("tos").indexOfValue("si"),
                dataset.attribute("dolor_garganta").indexOfValue("no"),
                dataset.attribute("congestion_nasal").indexOfValue("si"),
                dataset.attribute("enfermedad").indexOfValue("gripe")})); // Ejemplo de instancia

            // Guardar el conjunto de datos en un archivo ARFF
            ArffSaver arffSaver = new ArffSaver();
            arffSaver.setInstances(dataset);
            try {
                arffSaver.setFile(new File("ruta/a/tu/archivo.arff"));
                arffSaver.writeBatch();
                System.out.println("Archivo ARFF creado con éxito.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
    }

    public void Crear() {
        try {
            // Creamos un objeto FileWriter para escribir en el archivo
            //FileWriter fileWriter = new FileWriter("enfermedades.arff");

            FileWriter fileWriter = new FileWriter(clsSuper.path() + "archivo.arff");

            clsEnfermedadDao EnfermedadDao = new clsEnfermedadDao();
            JSONObject EnfermedadesList = EnfermedadDao.ListAll();

            // Escribimos la cabecera del archivo
            fileWriter.write("@relation enfermedades\n");
            String coma = "";
            JSONArray EnfermedadRecords = EnfermedadesList.getJSONArray("Records");

            clsSintomaDao SintomaDao = new clsSintomaDao();
            JSONObject SintomaList = SintomaDao.ListAll();
            JSONArray SintomaRecords = SintomaList.getJSONArray("Records");

            for (int i = 0; i < SintomaRecords.length(); i++) {
                JSONObject childJSONObject = SintomaRecords.getJSONObject(i);
                String sintoma_id = childJSONObject.getString("id");
                fileWriter.write("@attribute " + sintoma_id + " {si, no}\n");
            }

            ///Enfermedades
            String enfermedades = "@attribute enfermedad {";
            for (int i = 0; i < EnfermedadRecords.length(); i++) {
                JSONObject childJSONObject = EnfermedadRecords.getJSONObject(i);
                String enfermedad_id = childJSONObject.getString("id");
                enfermedades = enfermedades + coma + enfermedad_id;
                coma = ",";
            }
            enfermedades = enfermedades + "}\n";
            fileWriter.write(enfermedades);
            // end enfermedades            

            // Escribimos las instancias de datos
            fileWriter.write("@data\n");

            clsEnfermedad_sintomaDao Enfermedad_sintomaDao = new clsEnfermedad_sintomaDao();
            JSONObject Enfermedad_sintomaList = Enfermedad_sintomaDao.ListDataAll();
            JSONArray Enfermedad_sintomaRecords = Enfermedad_sintomaList.getJSONArray("Records");

            String old = "";
            String nuevo = "";

            String enfermedad_sintoma = "";
            String data = "";
            int primero = 1;
            for (int i = 0; i < Enfermedad_sintomaRecords.length(); i++) {
                JSONObject childJSONObject = Enfermedad_sintomaRecords.getJSONObject(i);

                data = childJSONObject.getString("enfermedad_id");
                String si_no = childJSONObject.getString("si_no");
                old = nuevo;
                nuevo = data;

                if (!old.equals(nuevo)) {
                    if (primero == 1) {
                    } else {

                        fileWriter.write(enfermedad_sintoma + "," + data + "\n");
                    }
                    coma = "";
                    //enfermedad_sintoma="";
                    enfermedad_sintoma = coma + si_no;
                    coma = ",";
                } else {
                    enfermedad_sintoma = enfermedad_sintoma + coma + si_no;
                    //fileWriter.write(data",si,si,si,si,si\n");
                }
            }
            fileWriter.write(enfermedad_sintoma + "," + data + "\n");

            // Cerramos el archivo
            fileWriter.close();

        } catch (Exception e) {
        }
    }

    /*
    public static void main(String[] args) {
        // Definir los atributos (síntomas y enfermedades)
        Attribute fiebre = new Attribute("fiebre", 2); // Atributo nominal con 2 valores: si/no
        Attribute tos = new Attribute("tos", 2);
        Attribute dolorGarganta = new Attribute("dolor_garganta", 2);
        Attribute congestiónNasal = new Attribute("congestion_nasal", 2);
        Attribute enfermedad = new Attribute("enfermedad", 3); // Atributo nominal con 3 valores: gripe, resfriado, alergia

        // Crear un conjunto de instancias
        FastVector atributos = new FastVector();
        atributos.addElement(fiebre);
        atributos.addElement(tos);
        atributos.addElement(dolorGarganta);
        atributos.addElement(congestiónNasal);
        atributos.addElement(enfermedad);

        // Crear el conjunto de datos ARFF
        Instances dataset = new Instances("DiagnosticoEnfermedades", atributos, 0);
        dataset.setClass(enfermedad); // Establecer el atributo clase (enfermedad)

        // Agregar instancias al conjunto de datos
        dataset.add(new DenseInstance(1.0, new double[]{dataset.attribute("fiebre").indexOfValue("si"),
                                                        dataset.attribute("tos").indexOfValue("si"),
                                                        dataset.attribute("dolor_garganta").indexOfValue("no"),
                                                        dataset.attribute("congestion_nasal").indexOfValue("si"),
                                                        dataset.attribute("enfermedad").indexOfValue("gripe")})); // Ejemplo de instancia

        // Guardar el conjunto de datos en un archivo ARFF
        ArffSaver arffSaver = new ArffSaver();
        arffSaver.setInstances(dataset);
        try {
            
            arffSaver.setFile(new File(clsSuper.path()+ "archivo.arff"));
            arffSaver.writeBatch();
            System.out.println("Archivo ARFF creado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
