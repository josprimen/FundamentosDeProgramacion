package fp.grados.excepciones;

public class ExcepcionActividadNoValida extends RuntimeException {
	public ExcepcionActividadNoValida (){
		super();
	}
	public ExcepcionActividadNoValida(String mensajeError){
		super(mensajeError);
	}

}
