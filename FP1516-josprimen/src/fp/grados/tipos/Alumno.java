package fp.grados.tipos;

import java.util.Set;

public interface Alumno extends Persona {

	Set<Asignatura> getAsignaturas();
	Integer getCurso();
	void matriculaAsignatura (Asignatura a);
	void eliminaAsignatura(Asignatura a);
	Boolean estaMatriculadoEn (Asignatura a);
	Expediente getExpediente();
	
	void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota, Boolean mencionHonor);
	void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota);

}
