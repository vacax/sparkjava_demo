package edu.pucmm.sparkjavademo.controladores;

import spark.Request;
import spark.Response;
import spark.Route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static spark.Spark.*;

/**
 *
 */
public class RecibirDatosControlador {

    public void aplicarRutas(){
        /**
         *
         */
        post("/UnaLlamadaPost", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Otra llamada";
            }
        });

        /**
         * Equivale el modelo 1 de desarrollo APP Web. Decada de los 90.
         */
        get("/formaAntigua", (request, response)->{
            String salida ="";
            salida += "<html><head><title>Forma Antigua</title></head><body>";
            salida += "<h1>Ejemplo de programación en los 90</h1>";
            salida += "<hr/>";
            salida += "</body></html>";
            response.header("Content-Type", "text/html");
            return salida;
        });

        /**
         * Obteniendo los parametros...
         * http://localhost:4567/parametros?param1=valor1&param2=valor2&paramN=valorN
         */
        get("/parametros", (request, response) -> {
            return procesarParametros(request, response); //encapsular las petición y las respuetas.
        });

        /**
         * 
         */
        post("/parametros", RecibirDatosControlador::procesarParametros);



        /**
         * Debes estar autenticado.
         * http://localhost:4567/zonaadmin/
         */
        get("/zonaadmin/", (request, response) -> "Zona Admin Barcamp 2014");

        /**
         * Llamada que provoca un error por redirect infinitos.
         */
        get("/loop", (request, response) -> {
            response.redirect("/loop");
            return "";
        });

        /**
         * Retorna la fecha actual, para ser utilizandos llamadas ajax.
         */
        get("/fecha", (request, response) -> {
            return ""+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        });
    }

    /**
     * Metodo para procesar información, como ejemplo para ser llamado en rutas sin importar el metodo.
     * @param request
     * @param response
     * @return
     */
    private static Object procesarParametros(Request request, Response response){
        System.out.println("Recibiendo mensaje por el metodo: "+request.requestMethod());
        Set<String> parametros = request.queryParams();
        String salida="";
        for(String param : parametros){
            salida+=String.format("Parametro[%s] = %s <br/>", param, request.queryParams(param));
        }
        return salida;
    }
}
