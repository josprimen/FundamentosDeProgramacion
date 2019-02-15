package fp.grados.tipos;

import java.util.Set;

public interface Grado {
	
	String getNombre();
	Set<Asignatura> getAsignaturasObligatorias();
	Set<Asignatura> getAsignaturasOptativas();
	Double getNumeroMinimoCreditosOptativas();
	Double getNumeroTotalCreditos(); //D
	Set<Departamento> getDepartamentos(); //D
	Set<Profesor> getProfesores();//D
	
	Set<Asignatura> getAsignaturas(Integer curso);
	Asignatura getAsignatura(String codigo);

}
