package fp.grados.excepciones;

public class ExcepcionExpedienteOperacionNoPermitida extends RuntimeException{
	public ExcepcionExpedienteOperacionNoPermitida(){
		super();
	}

	public ExcepcionExpedienteOperacionNoPermitida(String mensajeError){
		super(mensajeError);
	}
	
}
