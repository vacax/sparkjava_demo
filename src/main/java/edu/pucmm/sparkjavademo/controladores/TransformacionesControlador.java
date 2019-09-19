package edu.pucmm.sparkjavademo.controladores;

import com.google.gson.Gson;
import edu.pucmm.sparkjavademo.encapsulacion.Estudiante;
import edu.pucmm.sparkjavademo.utilidades.JsonTransformer;

import static spark.Spark.*;

/**
 * 
 */
public class TransformacionesControlador {

    public void ejemploTransformaciones(){

        /**
         * Trama sin convertir:
         * http://localhost:4567/estudianteJsonSimple/
         *
         */
        get("/estudianteJsonSimple/", (request, response) -> {
            return new Estudiante(20011136, "Carlos Camacho", "ISC");
        });

        /**
         * Trama sin convertir:
         * http://localhost:4567/estudianteJson/
         *
         */
        get("/estudianteJson/", (request, response) -> {
            return new Estudiante(20011136, "Carlos Camacho", "ISC");
        }, new JsonTransformer());

        get("/estudianteJsonAceptado/", "application/json", (request, response) -> {
            return new Estudiante(20011136, "Carlos Camacho", "ISC");
        }, new JsonTransformer());

        Gson gson = new Gson();
        get("/estudianteJsonDirecto", (request, response) -> new Estudiante(20011136, "Carlos Camacho", "ISC"), gson::toJson);

    }
}
