package edu.pucmm.sparkjavademo.controladores;

import static spark.Spark.*;

/**
 * Presentando la forma de manejar las excepciones...
 */
public class ExcepcionesControlador {

    /**
     * Permite capturar un error del tipo de conversion.     *
     */
    public void ejemplosManejoExcepciones(){

        /**
         * http://localhost:4567/errorConversion/kasdj
         */
        get("/errorConversion/:entero", (request, response) -> {
            int entero = Integer.parseInt(request.params("entero"));
            return "El numero recibido: "+entero;
        });

        /**
         * La excepción que se genere del tipo NumberFormatException, será atrapada
         * por este bloque.
         */
        exception(NumberFormatException.class, (e, request, response) -> {
            response.status(500);
            response.body("Error convertiendo un número....");
            e.printStackTrace();
        });

        /*exception(Exception.class, (e, request, response) -> {
            response.status(500);
            response.body("Todas las excepciones....");
            e.printStackTrace();
        });*/

        /**
         * Ruta para probar el plugin para visualizar los errores:
         * http://localhost:4567/errorRuntime         *
         */
        get("/errorRuntime", (request, response) -> {
            throw new RuntimeException("Error de ejecución...");
        });

        /**
         * Responde al error 404.
         * http://localhost:4567/no-existe-recurso
         */
        notFound("<html><body><h1>Recurso no encontrado</h1></body></html>");
    }
}
