package fp.grados.excepciones;

public class ExcepcionPersonaNoValida extends RuntimeException{
public ExcepcionPersonaNoValida(){
	super();
}
public ExcepcionPersonaNoValida(String mensajeError){
	super(mensajeError);
}
}
