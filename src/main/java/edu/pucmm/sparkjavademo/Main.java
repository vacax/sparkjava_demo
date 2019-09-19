package edu.pucmm.sparkjavademo;

import edu.pucmm.sparkjavademo.controladores.*;
import edu.pucmm.sparkjavademo.filtros.Filtros;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

/**
 * Hola Mundo Barcamp 2014
 */
public class Main {

    public static void main(String[] args) {

        //Seteando el puerto en Heroku
        port(getHerokuAssignedPort());

        
        //indicando los recursos publicos.
        //staticFiles.location("/META-INF/resources"); //para utilizar los WebJars.
        staticFiles.location("/publico");

        /**
         * Hola mundo utilizando  SparkJava
         */
        get("/hola", (request, response) -> {
            System.out.println("Entrando al action de la /");
           return  "Proyecto Demostración Funcionalidades SparkJava";
        });

        /**
         *
         */
        get("/basico", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                //Conocer header del cliente.
                System.out.println("El navegador o cliente: "+request.userAgent());
                System.out.println("La IP: "+request.ip());
                System.out.println("La puerto: "+request.port());
                System.out.println("Protocolo del HTPP: "+request.protocol());
                System.out.println("Metodo del HTTP: "+request.requestMethod());
                System.out.println("Los headers: ");

                for(String header : request.headers()){
                    System.out.println(""+header+" = "+request.headers(header));
                }
                //
                response.status(201);

                //
                return "Otra llamada";
            }
        });

        new RecibirDatosControlador().aplicarRutas();

        //Ejemplos de rutas
        new RutasControlador().ejemplosRutas();

        //Ejemplos de Cookies.
        new CookieYSesionesControlador().ejemploCookieSesiones();

        //Aplicando los filtros
        new Filtros().aplicarFiltros();

        //Ejemplo para el manejo de las excepciones.
        new ExcepcionesControlador().ejemplosManejoExcepciones(); //Deshabilitado para ver la ventana de debug.

        //Ejemplos para el manejo de templates.
        new TemplatesControlador().manejoTemplate();

        new TransformacionesControlador().ejemploTransformaciones();

        //
        new DescargaControlador().aplicarRutas();
    }

    /**
     * Metodo para setear el puerto en Heroku
     * @return
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }

}
