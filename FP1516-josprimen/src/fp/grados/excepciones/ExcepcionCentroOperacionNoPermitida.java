package fp.grados.excepciones;

public class ExcepcionCentroOperacionNoPermitida extends RuntimeException{
public ExcepcionCentroOperacionNoPermitida(){
	super();
}
public ExcepcionCentroOperacionNoPermitida (String mensajeError){
	super(mensajeError);
}
}
