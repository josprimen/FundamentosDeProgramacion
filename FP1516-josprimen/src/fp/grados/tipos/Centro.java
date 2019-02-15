package fp.grados.tipos;

import java.util.Set;

public interface Centro {

	String getNombre();
	String getDireccion();
	Integer getNumeroPlantas();
	Integer getNumeroSotanos();
	Set<Espacio> getEspacios();
	
	void nuevoEspacio (Espacio e);
	void eliminaEspacio (Espacio e);
	
	//Operaciones añadidas en el T8
	
	Integer[] getConteosEspacios();
	Set<Despacho> getDespachos();
	Set<Despacho> getDespachos(Departamento d);
	Set<Profesor> getProfesores();
	Set<Profesor> getProfesores(Departamento d);
	Espacio getEspacioMayorCapacidad();
	
}
