package edu.pucmm.sparkjavademo.servicios;


import edu.pucmm.sparkjavademo.encapsulacion.Estudiante;
import edu.pucmm.sparkjavademo.encapsulacion.Usuario;

/**
 * Prepresenta un servicio...
 */
public class FakeService {

    private static FakeService fakeServices;

    private FakeService() {
    }

    public static FakeService getInstancia(){
        if(fakeServices==null){
           fakeServices=new FakeService();
        }
        return fakeServices;
    }

    /**
     *
     * @param matricula
     * @return
     */
    public Estudiante getEstudianteMatricula(int matricula){
         return new Estudiante(matricula, "Estudiante "+matricula, "ISC");
    }

    /**
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    public Usuario autenticarUsuario(String usuario, String contrasena){
        if(usuario.equalsIgnoreCase("barcamp") && contrasena.equals("2014")){
            return new Usuario();
        }
        return null;
    }
}
