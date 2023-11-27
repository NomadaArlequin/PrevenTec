/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.Config;

import com.aqp.PrevenTecAppRest.Controller.clsEnfermedad_sintomaDao;
import com.aqp.PrevenTecAppRest.Controller.clsHistorial_sintomasDao;
import org.json.JSONArray;
import org.json.JSONObject;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author NomadaArlequin4
 */
public class clsDiagnosticoEnfermedadesValles {

    public JSONObject datosProbables(Long id) {

        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
            // Cargar el conjunto de datos desde un archivo ARFF
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(clsSuper.path() + "archivo.arff");
            Instances data = source.getDataSet();
            data.setClassIndex(data.numAttributes() - 1);

            // Instanciar y construir el clasificador NaiveBayes
            Classifier nb = new NaiveBayes();
            nb.buildClassifier(data);

            // Crear una nueva instancia con los síntomas del paciente
            Instance nuevaInstancia = new DenseInstance(data.numAttributes());

            nuevaInstancia.setDataset(data);

            clsHistorial_sintomasDao Historial_sintomasDao = new clsHistorial_sintomasDao();

            JSONObject Historial_sintomasList = Historial_sintomasDao.ListDataAllByHitorial(id);
            JSONArray Historial_sintomasRecords = Historial_sintomasList.getJSONArray("Records");
            Integer enfermedadesTotal = Historial_sintomasList.getInt("TotalRecordCount");

            for (int i = 0; i < Historial_sintomasRecords.length(); i++) {
                JSONObject childJSONObject = Historial_sintomasRecords.getJSONObject(i);

                String sintoma_id = childJSONObject.getString("sintoma_id");
                String si_no = childJSONObject.getString("si_no");
                nuevaInstancia.setValue(1, si_no); // Tos

            }

            //nuevaInstancia.setValue(1, "no"); // Segundo atributo de síntomas, etc.
            // Calcular las probabilidades de las clases (enfermedades) dadas los síntomas
            double[] probabilidades = nb.distributionForInstance(nuevaInstancia);

            // Imprimir las probabilidades de las clases (enfermedades)
            for (int i = 0; i < probabilidades.length; i++) {
                String enfermedad = data.classAttribute().value(i);
                //System.out.println("Probabilidad de " + enfermedad + ": " + probabilidades[i]);
                varJsonObjectRegistro = new JSONObject();
                varJsonObjectRegistro.put("enfermedad_id", enfermedad);
                varJsonObjectRegistro.put("probabilidad", probabilidades[i]);
                varJsonArrayP.put(varJsonObjectRegistro);
            }
            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("TotalRecordCount", varJsonArrayP.toList().size());
            varJsonObjectResultado.put("Records", varJsonArrayP);
        } catch (Exception e) {
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e);
            varJsonObjectResultado.put("numError", "-3");
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject sindatos(Long id) {

        try {

            clsHistorial_sintomasDao Historial_sintomasDao = new clsHistorial_sintomasDao();

            // Cargar el conjunto de datos desde un archivo ARFF
            Instances data = new Instances(new java.io.BufferedReader(new java.io.FileReader(clsSuper.path() + "archivo.arff")));
            data.setClassIndex(data.numAttributes() - 1);

            // Instanciar y construir el clasificador (usando J48 como ejemplo)
            Classifier classifier = new J48();
            classifier.buildClassifier(data);

            // Ejemplo de instancia con síntomas para clasificar
            JSONObject Historial_sintomasList = Historial_sintomasDao.ListDataAllByHitorial(id);
            JSONArray Historial_sintomasRecords = Historial_sintomasList.getJSONArray("Records");
            Integer enfermedadesTotal = Historial_sintomasList.getInt("TotalRecordCount");

            Instance nuevaInstancia = new DenseInstance(enfermedadesTotal);
            nuevaInstancia.setDataset(data);

            for (int i = 0; i < Historial_sintomasRecords.length(); i++) {
                JSONObject childJSONObject = Historial_sintomasRecords.getJSONObject(i);

                String sintoma_id = childJSONObject.getString("sintoma_id");
                String si_no = childJSONObject.getString("si_no");
                nuevaInstancia.setValue(1, si_no); // Tos

            }

            // Clasificar la instancia
            double resultado = classifier.classifyInstance(nuevaInstancia);

            // Obtener el nombre de la enfermedad predicha
            String enfermedadPredicha = data.classAttribute().value((int) resultado);

            System.out.println("La enfermedad predicha es: " + enfermedadPredicha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
