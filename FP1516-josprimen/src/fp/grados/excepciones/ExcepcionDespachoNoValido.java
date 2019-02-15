package fp.grados.excepciones;

public class ExcepcionDespachoNoValido extends RuntimeException{
	public ExcepcionDespachoNoValido(){
		super();
	}
public ExcepcionDespachoNoValido(String mensajeError){
	super(mensajeError);
}
}
