package fp.grados.excepciones;

public class ExcepcionProfesorOperacionNoPermitida extends RuntimeException {
public ExcepcionProfesorOperacionNoPermitida(){
	super();
}
public ExcepcionProfesorOperacionNoPermitida(String mensajeError){
	super(mensajeError);
}
}
