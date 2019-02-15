package fp.grados.excepciones;

public class ExcepcionGradoNoValido extends RuntimeException {
	public ExcepcionGradoNoValido(){
		super();
	}
	
	public ExcepcionGradoNoValido(String mensajeError){
		super(mensajeError);
	}

}
