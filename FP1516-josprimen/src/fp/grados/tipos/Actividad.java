package fp.grados.tipos;

	import java.time.DayOfWeek;
	import java.time.LocalTime;


	public interface Actividad extends Comparable<Actividad>{

		String getCodigo();
		DayOfWeek getDiaSemana();
		LocalTime getHoraComienzo();
		LocalTime getHoraFin();
		Espacio getEspacio();
		Integer getDuracion();
		TipoActividad getTipo();
		Asignatura getAsignatura();
		
		void setEspacio(Espacio espacio);
		
	}

	

