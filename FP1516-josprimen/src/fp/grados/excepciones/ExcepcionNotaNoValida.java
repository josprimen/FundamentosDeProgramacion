package fp.grados.excepciones;

public class ExcepcionNotaNoValida extends RuntimeException{
public ExcepcionNotaNoValida(){
	super();
}
public ExcepcionNotaNoValida(String mensajeError){
	super(mensajeError);
}
}
