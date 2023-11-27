/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aqp.PrevenTecAppRest.Config;

import java.io.File;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ArffLoader;


import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;


import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
/**
 *
 * @author Windows
 */
public class clsDiagnosticoEnfermedades {

    public static void main(String[] args) {
        encontrar();
        System.out.println("----------------------------");
        probabilidad();
        System.out.println("----------------------------");        
        datosProbables();
        System.out.println("----------------------------");        
        sindatos(); 
    }
    //nuevos datos
    public static void encontrar() {
        //clsSuper.path()+ "archivo.arff"
        //clsSuper.path()+ "enfermedades_prueba.arff"
         try {
            // Cargar el archivo ARFF
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(clsSuper.path()+ "archivo.arff"));
            Instances data = loader.getDataSet();

            // Establecer el índice de la clase (si existe)
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            // Filtrar el conjunto de datos para buscar elementos específicos
            Remove filtro = new Remove();
            filtro.setAttributeIndicesArray(new int[]{0}); // Supongamos que eliminamos la columna de enfermedad
            filtro.setInputFormat(data);
            Instances filteredData = Filter.useFilter(data, filtro);

            // Buscar elementos que coincidan con ciertos valores en las columnas restantes
            String enfermedadBuscada = "1";
            String presenciaEnfermedadBuscada = "si";

            for (int i = 0; i < filteredData.numInstances(); i++) {
                if (filteredData.instance(i).stringValue(0).equals(enfermedadBuscada) &&
                        filteredData.instance(i).stringValue(1).equals(presenciaEnfermedadBuscada)) {
                    System.out.println("Enfermedad encontrada: " + filteredData.instance(i));
                    // Haz lo que necesites con la instancia encontrada
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //probabilidad
    public static void probabilidad() {
        try {
            // Cargar el archivo ARFF
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(clsSuper.path()+ "archivo.arff"));
            Instances data = loader.getDataSet();

            // Establecer el índice de la clase (si existe)
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            // Filtrar el conjunto de datos para entrenar el modelo
            Remove filtro = new Remove();
            filtro.setAttributeIndicesArray(new int[]{0}); // Supongamos que eliminamos la columna de enfermedad
            filtro.setInputFormat(data);
            Instances filteredData = Filter.useFilter(data, filtro);

            // Entrenar el clasificador Naive Bayes
            NaiveBayes nb = new NaiveBayes();
            nb.buildClassifier(filteredData);

            // Crear una nueva instancia para buscar por probabilidades
            Instances newData = new Instances(filteredData, 0);
            newData.setClassIndex(filteredData.numAttributes() - 1);

            // Configurar los valores de la instancia para la búsqueda
            newData.add(data.instance(0)); // Supongamos que buscamos el primer elemento del conjunto de datos
            newData.instance(0).setValue(0, "1"); // Valor para el atributo 1
            newData.instance(0).setValue(1, "si"); // Valor para el atributo de presencia de enfermedad

            // Calcular la probabilidad
            double[] probabilidad = nb.distributionForInstance(newData.instance(0));
            System.out.println("Probabilidad de que cumpla con los valores dados: " + probabilidad[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void datosProbables() {
        try {
            // Cargar el conjunto de datos desde un archivo ARFF
            DataSource source = new DataSource(clsSuper.path()+ "archivo.arff");
            Instances data = source.getDataSet();
            data.setClassIndex(data.numAttributes() - 1);

            // Instanciar y construir el clasificador NaiveBayes
            Classifier nb = new NaiveBayes();
            nb.buildClassifier(data);

            // Crear una nueva instancia con los síntomas del paciente
            Instance nuevaInstancia = new DenseInstance(data.numAttributes());
            nuevaInstancia.setDataset(data);
            nuevaInstancia.setValue(1, "si"); // Por ejemplo, el primer atributo de síntomas
            //nuevaInstancia.setValue(1, "no"); // Segundo atributo de síntomas, etc.

            // Calcular las probabilidades de las clases (enfermedades) dadas los síntomas
            double[] probabilidades = nb.distributionForInstance(nuevaInstancia);

            // Imprimir las probabilidades de las clases (enfermedades)
            for (int i = 0; i < probabilidades.length; i++) {
                String enfermedad = data.classAttribute().value(i);
                System.out.println("Probabilidad de " + enfermedad + ": " + probabilidades[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void sindatos() {
        try {
            // Cargar el conjunto de datos desde un archivo ARFF
            Instances data = new Instances(new java.io.BufferedReader(new java.io.FileReader(clsSuper.path()+ "archivo.arff")));
            data.setClassIndex(data.numAttributes() - 1);

            // Instanciar y construir el clasificador (usando J48 como ejemplo)
            Classifier classifier = new J48();
            classifier.buildClassifier(data);

            // Ejemplo de instancia con síntomas para clasificar
            Instance nuevaInstancia = new DenseInstance(4);
            nuevaInstancia.setDataset(data);
            nuevaInstancia.setValue(0, "no"); // Tos
            nuevaInstancia.setValue(1, "no"); // CongestionNasal
            nuevaInstancia.setValue(2, "no"); // DolorGarganta

            // Clasificar la instancia
            double resultado = classifier.classifyInstance(nuevaInstancia);

            // Obtener el nombre de la enfermedad predicha
            String enfermedadPredicha = data.classAttribute().value((int) resultado);
            
            System.out.println("La enfermedad predicha es: " + enfermedadPredicha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
