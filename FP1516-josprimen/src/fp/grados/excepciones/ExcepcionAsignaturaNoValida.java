package fp.grados.excepciones;

public class ExcepcionAsignaturaNoValida extends RuntimeException{
public ExcepcionAsignaturaNoValida(){
	super();
}
	public ExcepcionAsignaturaNoValida(String mensajeError){
    super(mensajeError);
}
}