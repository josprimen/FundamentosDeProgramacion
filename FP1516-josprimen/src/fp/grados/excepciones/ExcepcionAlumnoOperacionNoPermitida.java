package fp.grados.excepciones;

public class ExcepcionAlumnoOperacionNoPermitida extends RuntimeException {
public ExcepcionAlumnoOperacionNoPermitida(){
	super();
}
public ExcepcionAlumnoOperacionNoPermitida(String mensajeError){
	super(mensajeError);
}
}
