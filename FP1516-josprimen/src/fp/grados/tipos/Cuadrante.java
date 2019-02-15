package fp.grados.tipos;

import java.util.List;

public interface Cuadrante {

	Integer getSemana();
	Alumno getAlumno();
	List<Actividad> getActividades();
	
	void nuevaActividad (Actividad a);
	void eliminaActividad(Actividad a);
	Boolean contieneActividad(Actividad a);
	
}
