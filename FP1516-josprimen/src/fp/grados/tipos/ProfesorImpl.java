package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl extends PersonaImpl implements Profesor{
	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private Departamento departamento;
	private List<Asignatura> asignaturas;
	private List<Double> creditos;
	private static final Double creditosMax = 32.0;
	private static final Double creditosMaxAyudante = 6.0;
	
	public ProfesorImpl (String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria){
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdadProfesor(fechaNacimiento);
		this.categoria=categoria;
		this.tutorias= new TreeSet<Tutoria>();
		this.departamento= null;
		this.asignaturas= new ArrayList<Asignatura>();
		this.creditos=  new ArrayList<Double>();
	}
	
	public ProfesorImpl (String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria, Departamento departamento){
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdadProfesor(fechaNacimiento);
		this.categoria=categoria;
		this.tutorias= new TreeSet<Tutoria>();
		setDepartamento(departamento);
		this.asignaturas= new ArrayList<Asignatura>();
		this.creditos=  new ArrayList<Double>();
	}

	private void checkEdadProfesor(LocalDate fechaNacimiento){
		if(!(fechaNacimiento.isBefore(LocalDate.now().minusYears(18)))){
			throw new ExcepcionProfesorNoValido("Un profesor debe tener más de 18 años.");
			
		}
	}
	
	private void checkAsignaturaDepartamento (Asignatura asig){
		if(getDepartamento()==null || !getDepartamento().getAsignaturas().contains(asig)){
			throw new ExcepcionProfesorOperacionNoPermitida("El profesor debe tener asignado algún departamento");
		}
	}
	private void checkCreditosAsignatura (Asignatura asig, Double dedicacion){
		
	if(dedicacion<0 || dedicacion > asig.getCreditos() ){
		throw new ExcepcionProfesorOperacionNoPermitida("El número de creditos de dedicación debe ser mayor que cero y menos o igual al número de creditos de la asignatua");
	}
		
	}
	
	public Categoria getCategoria(){
		return categoria;
	}
	
	public SortedSet<Tutoria> getTutorias(){
		return new TreeSet<Tutoria>(this.tutorias);
	}
	
	public Departamento getDepartamento(){
		return departamento;
	}
	
	public List<Asignatura> getAsignaturas(){
		return new ArrayList<Asignatura>(this.asignaturas);
	}
	
	public List<Double> getCreditos(){
		return new ArrayList<Double>(this.creditos);
	}
	
	
	
	
	public void setCategoria (Categoria categoria){
		this.categoria=categoria;
	}
	
	public void setDepartamento (Departamento nuevoDepartamento){
		if(nuevoDepartamento != this.departamento){
			Departamento antiguoDepartamento = this.departamento;
			this.departamento = nuevoDepartamento;
			if(antiguoDepartamento != null){
				antiguoDepartamento.eliminaProfesor(this);
			}
			if(nuevoDepartamento != null){
				nuevoDepartamento.nuevoProfesor(this);
			}
		}
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento){
	checkEdadProfesor(fechaNacimiento);
	super.setFechaNacimiento(fechaNacimiento);
	
	}
	
	public 	void nuevaTutoria (LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia){
		Tutoria t = new TutoriaImpl (dia, horaComienzo, duracionMinutos);
		this.tutorias.add(t);
	}

	
	public void borraTutoria (LocalTime horaComienzo, DayOfWeek dia){
		for (Tutoria t : getTutorias()){
			if (t.getHoraComienzo().equals(horaComienzo) && t.getDiaSemana().equals(dia)){
				this.tutorias.remove(t);
			}
		}
	}
	
	public void borraTutorias(){
		this.tutorias.clear();
	}
	
	//Métodos para el t7
	public void imparteAsignatura(Asignatura asig, Double dedicacion){
		checkAsignaturaDepartamento(asig);
		checkCreditosAsignatura (asig, dedicacion);
		if(getAsignaturas().contains(asig)){
			actualizaDedicacion(asig, dedicacion);	
		}
		else{
			nuevaAsignatura(asig, dedicacion);
		}
	}
	
		public Double dedicacionAsignatura (Asignatura asig){
			Double res = 0.0;
			int posicionAsignatura = asignaturas.indexOf(asig);
			if (posicionAsignatura >= 0){
				res = creditos.get(posicionAsignatura);
			}
			return res;
		}
		
		public void eliminaAsignatura (Asignatura asig){
			int posicionAsignatura = asignaturas.indexOf(asig);
			if(posicionAsignatura >= 0){
				this.creditos.remove(posicionAsignatura);
				this.asignaturas.remove(asig);
			}
		}
	
	
	//Metodos auxiliares para los métodos del t7
	public void nuevaAsignatura (Asignatura asig, Double dedicacion){
		checkMaxCreditos();
		this.asignaturas.add(asig);
		this.creditos.add(dedicacion);
	}
	
	public void actualizaDedicacion (Asignatura asig, Double dedicacion){
		checkMaxCreditos();
		int posicionAsignatura = asignaturas.indexOf(asig);
		this.creditos.set(posicionAsignatura, dedicacion);
	}
	
	//Métodos T8
	
	//Profesor p como atributo de entrada? y p. antes del this y de los gets
	private void checkMaxCreditos(){
		if(getCategoria() == Categoria.AYUDANTE && this.getDedicacionTotal() > creditosMaxAyudante){
			throw new ExcepcionProfesorOperacionNoPermitida ("Un ayudante no puede impartir más de " + creditosMaxAyudante + "creditos");
		}
		else if (this.getDedicacionTotal() > creditosMax){
			throw new ExcepcionProfesorOperacionNoPermitida ("Un profesor no puede impartir más  de " + creditosMax + " créditos");
		}
		
	}
	
	public Double getDedicacionTotal() {
		Double dedicacionTotal = 0.0;
		for(Asignatura a: getAsignaturas()){
			dedicacionTotal += this.dedicacionAsignatura(a);
		}
		if(dedicacionTotal>creditosMax){
			throw new ExcepcionProfesorOperacionNoPermitida("Un profesor no puede impartir más de "+ creditosMax+" créditos");
		}
		return dedicacionTotal;
	}
	
	
	
	
	//**********************
	public String toString(){
		return super.toString()+" ("+getCategoria()+")";
	}

	
	
	
	
}
