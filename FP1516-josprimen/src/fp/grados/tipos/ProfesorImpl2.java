package fp.grados.tipos;


	import java.time.DayOfWeek;
	import java.time.LocalDate;
	import java.time.LocalTime;
	import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
	import java.util.TreeSet;

	import fp.grados.excepciones.ExcepcionProfesorNoValido;
	import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

	public class ProfesorImpl2 extends PersonaImpl implements Profesor{
		private Categoria categoria;
		private SortedSet<Tutoria> tutorias;
		private Departamento departamento;
		private static final Double creditosMax = 32.0;
		private static final Double creditosMaxAyudante = 6.0;
		private Map<Asignatura,Double> creditosPorAsignatura;
		
		public ProfesorImpl2 (String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria){
			super(dni, nombre, apellidos, fechaNacimiento, email);
			checkEdadProfesor(fechaNacimiento);
			this.categoria=categoria;
			this.tutorias= new TreeSet<Tutoria>();
			this.departamento= null;
	
			this.creditosPorAsignatura= new HashMap<Asignatura, Double>();
		}
		
		public ProfesorImpl2 (String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria, Departamento departamento){
			super(dni, nombre, apellidos, fechaNacimiento, email);
			checkEdadProfesor(fechaNacimiento);
			this.categoria=categoria;
			this.tutorias= new TreeSet<Tutoria>();
			setDepartamento(departamento);
			
			this.creditosPorAsignatura= new HashMap<Asignatura,Double>();
		}

		private void checkEdadProfesor(LocalDate fechaNacimiento){
			if(!(fechaNacimiento.isBefore(LocalDate.now().minusYears(18)))){
				throw new ExcepcionProfesorNoValido("Un profesor debe tener m�s de 18 a�os.");
				
			}
		}
		
		private void checkAsignaturaDepartamento (Asignatura asig){
			if(getDepartamento()==null || !getDepartamento().getAsignaturas().contains(asig)){
				throw new ExcepcionProfesorOperacionNoPermitida("El profesor debe tener asignado alg�n departamento");
			}
		}
		private void checkCreditosAsignatura (Asignatura asig, Double dedicacion){
			
		if(dedicacion<0 || dedicacion > asig.getCreditos() ){
			throw new ExcepcionProfesorOperacionNoPermitida("El n�mero de creditos de dedicaci�n debe ser mayor que cero y menos o igual al n�mero de creditos de la asignatua");
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
			return new ArrayList<Asignatura>(creditosPorAsignatura.keySet());
		}
		
		public List<Double> getCreditos(){
			return new ArrayList<Double>(creditosPorAsignatura.values());
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
		
		//M�todos para el t7
		public void imparteAsignatura(Asignatura asig, Double dedicacion){
			checkAsignaturaDepartamento(asig);
			checkCreditosAsignatura (asig, dedicacion);
			if(creditosPorAsignatura.containsKey(asig)){
				actualizaDedicacion(asig, dedicacion);	
			}
			else{
				nuevaAsignatura(asig, dedicacion);
			}
		}
		
			public Double dedicacionAsignatura (Asignatura asig){
				Double res = 0.0;
				if(creditosPorAsignatura.containsKey(asig)){
				res= creditosPorAsignatura.get(asig);
				}
				return res;
			}
			
			public void eliminaAsignatura (Asignatura asig){
				creditosPorAsignatura.remove(asig);
				}
			
		
		
		//Metodos auxiliares para los m�todos del t7
		public void nuevaAsignatura (Asignatura asig, Double dedicacion){
			checkMaxCreditos();
			creditosPorAsignatura.put(asig, dedicacion);
		}
		
		public void actualizaDedicacion (Asignatura asig, Double dedicacion){
			checkMaxCreditos();
			creditosPorAsignatura.put(asig, dedicacion);
		}
		
		//M�todos T8
		
		//Profesor p como atributo de entrada? y p. antes del this y de los gets
		private void checkMaxCreditos(){
			if(getCategoria() == Categoria.AYUDANTE && this.getDedicacionTotal() > creditosMaxAyudante){
				throw new ExcepcionProfesorOperacionNoPermitida ("Un ayudante no puede impartir m�s de " + creditosMaxAyudante + "creditos");
			}
			else if (this.getDedicacionTotal() > creditosMax){
				throw new ExcepcionProfesorOperacionNoPermitida ("Un profesor no puede impartir m�s  de " + creditosMax + " cr�ditos");
			}
			
		}
		
		public Double getDedicacionTotal() {
			Double dedicacionTotal = 0.0;
			for(Asignatura a: getAsignaturas()){
				dedicacionTotal += this.dedicacionAsignatura(a);
			}
			if(dedicacionTotal>creditosMax){
				throw new ExcepcionProfesorOperacionNoPermitida("Un profesor no puede impartir m�s de "+ creditosMax+" cr�ditos");
			}
			return dedicacionTotal;
		}
		
		
		
		
		//**********************
		public String toString(){
			return super.toString()+" ("+getCategoria()+")";
		}

		
		
		
		
	

	
}
