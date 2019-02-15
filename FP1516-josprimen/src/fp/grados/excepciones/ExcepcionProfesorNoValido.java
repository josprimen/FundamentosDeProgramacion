package fp.grados.excepciones;

public class ExcepcionProfesorNoValido extends RuntimeException {

	public ExcepcionProfesorNoValido(){
		super();
	}
	public ExcepcionProfesorNoValido(String mensajeError){
		super(mensajeError);
	}
}
