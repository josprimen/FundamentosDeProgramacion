package fp.grados.excepciones;

public class ExcepcionBecarioNoValido extends RuntimeException{
	public ExcepcionBecarioNoValido(){
		super();
	}
	public ExcepcionBecarioNoValido(String mensajeError){
		super(mensajeError);
	}
}
