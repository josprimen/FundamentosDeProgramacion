package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;


public class TutoriaImpl implements Tutoria {
	private DayOfWeek diaSemana;
	private LocalTime horaComienzo;
	private LocalTime horaFin;
	
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin){
		
		Integer duracion = (int) horaComienzo.until(horaFin, ChronoUnit.MINUTES);
		
		checkDiaSemana(diaSemana);
		checkHoraComienzo(horaComienzo);
		checkHoraFin(horaFin);
		checkDuracion(duracion);
		this.diaSemana=diaSemana;
		this.horaComienzo=horaComienzo;
		this.horaFin=horaFin;
	}
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion){
		checkDuracion(duracion);
		checkDiaSemana(diaSemana);
		checkHoraComienzo(horaComienzo);
		this.diaSemana=diaSemana;
		this.horaComienzo=horaComienzo;
		this.horaFin = (horaComienzo.plusMinutes(duracion));
		
		checkHoraFin(horaFin);
		
		}
	private void checkDiaSemana(DayOfWeek diaSemana){
	if(diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY){
		throw new ExcepcionTutoriaNoValida ("Los Sabados y Domingos no hay tutorías");
	}
	}
	private void checkDuracion(Integer duracion){
		if(duracion<30){
			throw new ExcepcionTutoriaNoValida("La duración debe ser de al menos 30 minutos");
		}
	}
	
	private void checkHoraComienzo(LocalTime horaComienzo){
	LocalTime t = LocalTime.of(8, 30);
	if(horaComienzo.isBefore(t)){
		throw new ExcepcionTutoriaNoValida("La tutoría no puede ser antes de las "+ t);
	}
	}
	
	private void checkHoraFin(LocalTime horaFin){
		LocalTime f = LocalTime.of(21, 30);
		if(horaFin.isAfter(f)){
			throw new ExcepcionTutoriaNoValida("La tutoría no puede ser despues de las "+ f);
		}
	}
	public boolean equals(Object o){
		boolean res = false;
		if (o instanceof Tutoria){
			Tutoria t = (Tutoria) o;
			res = getDiaSemana().equals(t.getDiaSemana())&&
					getHoraComienzo().equals(t.getHoraComienzo());
		}
		return res;
	}
	public int hashCode(){
		return getDiaSemana().hashCode() + getHoraComienzo().hashCode()*31;
	}
	public int compareTo(Tutoria t){
		int res = getDiaSemana().compareTo(t.getDiaSemana());
		if (res == 0){
			res = getHoraComienzo().compareTo(t.getHoraComienzo());
		}
		return res;
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
	public Integer getDuracion() {
		return (int) horaComienzo.until(horaFin, ChronoUnit.MINUTES);
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
	
	public String toString(){
		
		return getDiaCharacter() +" "+ getHoraComienzo()+ "-" + getHoraFin();
		}
	}
