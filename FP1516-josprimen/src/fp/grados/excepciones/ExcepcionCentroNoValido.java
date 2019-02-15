package fp.grados.excepciones;

public class ExcepcionCentroNoValido extends RuntimeException{
	public ExcepcionCentroNoValido(){
		super();
	}
public ExcepcionCentroNoValido (String mensajeError){
	super(mensajeError);
}
}
