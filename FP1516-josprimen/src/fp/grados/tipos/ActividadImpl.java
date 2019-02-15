package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionActividadNoValida;

public class ActividadImpl implements Actividad {

	private String codigo;
	private DayOfWeek diaSemana;
	private LocalTime horaComienzo;
	private LocalTime horaFin;
	private Espacio espacio;
	private Integer duracion;
	private TipoActividad tipo;
	private Asignatura asignatura;
	
	public ActividadImpl (String codigo, DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin, 
			Espacio espacio, Integer duracion, TipoActividad tipo, Asignatura asignatura){
		
		checkDuracion(duracion, tipo);
		checkDiaSemana(diaSemana);
		checkEspacio(espacio, tipo);
		
		this.codigo=codigo;
		this.diaSemana=diaSemana;
		this.horaComienzo=horaComienzo;
		this.horaFin=horaFin;
		this.espacio=espacio;
		this.duracion= duracion;
		this.tipo= tipo;
		this.asignatura= asignatura;
	}
	
private void checkDuracion (Integer duracion,TipoActividad tipo){
Boolean res = tipo.equals(TipoActividad.EXAMEN) && duracion <=180 || duracion<= 120;	
if(!res){
	throw new ExcepcionActividadNoValida ("La duración debe ser como máximo de dos horas " + "3 si es un examen.");
}
}

private void checkDiaSemana(DayOfWeek diaSemana){
	if(diaSemana.equals(DayOfWeek.SATURDAY)|| diaSemana.equals(DayOfWeek.SUNDAY)){
		throw new ExcepcionActividadNoValida ("La actividad debe ser en día laboral");
	}
}

private void checkEspacio(Espacio espacio, TipoActividad tipo){
	if(!tipo.toString().equals(espacio.getTipo().toString())){
		throw new ExcepcionActividadNoValida ("El espacio no es adecuado para la actividad");
	}
}


public String getCodigo() {
	return codigo;
}


public DayOfWeek getDiaSemana() {
	return diaSemana;
}

@Override
public LocalTime getHoraComienzo() {
	return horaComienzo;
}

@Override
public LocalTime getHoraFin() {
	return horaFin;
}

@Override
public Espacio getEspacio() {
	return espacio;
}

@Override
public Integer getDuracion() {
	return duracion;
}

@Override
public TipoActividad getTipo() {
	return tipo;
}

@Override
public Asignatura getAsignatura() {
	return asignatura;
}

@Override
public void setEspacio(Espacio espacio) {
checkEspacio(espacio, tipo);
this.espacio=espacio;	
}

private Character getDiaCharacter(){
	Character dia = null;
switch (getDiaSemana()){
	case MONDAY:
		dia = 'L';
		break;
	case TUESDAY:
	dia = 'M';
	break;
	case WEDNESDAY:
		dia = 'X';
		break;
	case THURSDAY:
		dia = 'J';
		break;
	case FRIDAY:
		dia = 'V';
		break;
default:
dia ='?';
}
return dia;
}

public boolean equals (Object o){
	boolean res = false;
	if (o instanceof Actividad){
		Actividad a = (Actividad) o;
		res = getCodigo().equals(a.getCodigo());
	}
	return res;
}

public int hashCode(){
	return getCodigo().hashCode();
}

public int compareTo (Actividad a){
	int res = getCodigo().compareTo(a.getCodigo());
	return res;
}

public String toString(){
	return getCodigo() + ": " + getDiaCharacter() + " " + getHoraComienzo() + "-" + getHoraFin() + ", " + 
 getEspacio() + ", " + getAsignatura().getNombre() + "(" + getTipo() + ")";
}



}
