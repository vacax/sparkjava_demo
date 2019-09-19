package edu.pucmm.sparkjavademo.controladores;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static spark.Spark.*;

/**
 * Representa la ruta para el manejo de descargas y subidas de
 * archivos.
 */
public class DescargaControlador {

    public void aplicarRutas(){

        /**
         * 
         */
        post("/procesar-subir-archivos", (request, response) -> {
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream is = request.raw().getPart("archivo").getInputStream()) {
                //
                Files.copy(is, Paths.get(File.createTempFile("archivo_","").toURI()), StandardCopyOption.REPLACE_EXISTING);
            }
            return "Archivo procesado...";
        });
    }


}
