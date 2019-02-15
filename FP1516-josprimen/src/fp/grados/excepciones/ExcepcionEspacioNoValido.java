package fp.grados.excepciones;

public class ExcepcionEspacioNoValido extends RuntimeException{
public ExcepcionEspacioNoValido(){
	super();
}
public ExcepcionEspacioNoValido(String mensajeError){
	super(mensajeError);
}
}
