package edu.pucmm.sparkjavademo.controladores;

import edu.pucmm.sparkjavademo.encapsulacion.Usuario;
import spark.Session;

import java.util.Map;

import static spark.Spark.*;

/**
 * Manejo de Cookies y Sesiones usando Spark.
 */
public class CookieYSesionesControlador {

    /**
     *
     */
    public void ejemploCookieSesiones(){

        /**
         * Lista todos los Cookies enviados desde el cliente.
         * http://localhost:4567/listarCookies/
         */
        get("/listarCookies/", (request, response)->{
            Map<String, String> cookies=request.cookies();
            System.out.println("El cookie: "+request.cookie("nombreCookie"));
            String salida="";
            System.out.println("La cantidad de elementos:"+cookies.size());
            for(String key : cookies.keySet()){
                salida+=String.format("Cookie %s = %s", key, cookies.get(key))+"<br/>";
            }
            return salida;
        });

        /**
         * Creando un cookies en Spark.
         * http://localhost:4567/crearCookie/barcamp/2014
         */
        get("/crearCookie/:nombreCookie/:valor", (request, response)->{
            //creando cookie en para un minuto
            response.cookie("/", request.params("nombreCookie"), request.params("valor"), 3600, false); //incluyendo el path del cookie.

            return "Cookie creado con exito...";
        });

        get("/saludos", (request, response) -> {
            String saludo = "Hola ";
            if(request.cookie("saludo")!=null){
                saludo+=" "+request.cookie("saludo");
            }else{
                saludo+=" Anonimo";
            }
            return saludo;
        });

        /**
         * Registra elementos en el ambito web de sesion.
         * http://localhost:4567/contadorSesion/
         */
        get("/contadorSesion/", (request, response)->{
            //creando cookie en para un minuto
            Session session=request.session(true);
            Integer contador = session.attribute("contador");
            if(contador==null){
               contador=0;
            }
            contador++;
            session.attribute("contador", contador);

            return String.format("Usted a visitado está pagina %d, sesión ID #%s", contador, session.id());
        });

        /**
         * http://localhost:4567/invalidarSesion/
         */
        get("/invalidarSesion/", (request, response)->{
            //creando cookie en para un minuto
            Session session=request.session(true);
            String id = session.id();
            System.out.println("El ID de la sesion: "+id);
            session.invalidate();

            return "Sesion invalidada: "+id;
        });

        /**
         * Registra elementos en el ambito web de sesion.
         * http://localhost:4567/autenticar/barcamp/2014
         */
        get("/autenticar/:usuario/:contrasena", (request, response)->{
            //
            Session session=request.session(true);

            //
            Usuario usuario= null;//FakeServices.getInstancia().autenticarUsuario(request.params("usuario"), request.params("contrasena"));
            if(request.params("usuario").equalsIgnoreCase("barcamp") && request.params("contrasena").equalsIgnoreCase("2014")){
                //Buscar el usuario en la base de datos..
                usuario = new Usuario("Barcamp", "2014");
            }else{
                halt(401,"Credenciales no validas...");
            }

            session.attribute("usuario", usuario);
            //redireccionado a la otra URL.
            response.redirect("/zonaadmin/?param1=adasdasd");

            return "";
        });

    }
}
