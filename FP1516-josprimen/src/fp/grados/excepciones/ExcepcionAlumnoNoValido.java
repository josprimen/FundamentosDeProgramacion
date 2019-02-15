package fp.grados.excepciones;

public class ExcepcionAlumnoNoValido extends RuntimeException {
public ExcepcionAlumnoNoValido(){
	super();
}
public ExcepcionAlumnoNoValido(String mensajeError){
	super(mensajeError);
}
}
