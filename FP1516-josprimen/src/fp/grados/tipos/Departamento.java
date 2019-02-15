package fp.grados.tipos;

import java.util.Set;

public interface Departamento extends Comparable <Departamento> {

	String getNombre();
	Set<Profesor> getProfesores();
	Set<Asignatura> getAsignaturas();
	
	void nuevoProfesor(Profesor prof);
	void eliminaProfesor(Profesor prof);
	void nuevaAsignatura(Asignatura asig);
	void eliminaAsignatura(Asignatura asig);
	
	//Operaciones T8
	
	void borraTutorias();
	void borraTutorias(Categoria c);
	Boolean existeProfesorAsignado(Asignatura a);
	Boolean estanTodasAsignaturasAsignadas();
	void eliminaAsignacionProfesorado(Asignatura a);
}
