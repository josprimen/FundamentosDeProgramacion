package fp.grados.excepciones;

public class ExcepcionDepartamentoNoValido extends RuntimeException{
public ExcepcionDepartamentoNoValido(){
	super();
}
public ExcepcionDepartamentoNoValido(String mensajeError){
	super(mensajeError);
}
}
