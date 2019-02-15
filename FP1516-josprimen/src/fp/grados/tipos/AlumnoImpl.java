package fp.grados.tipos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl extends PersonaImpl implements Alumno {

	private Expediente expediente;
	private Set<Asignatura> asignaturas;
	
	public AlumnoImpl (String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email){
	super(dni, nombre, apellidos, fechaNacimiento, email);
	checkEmailUniversidad(email);
	this.asignaturas =  new HashSet<Asignatura>();
	this.expediente = new ExpedienteImpl();;
	
	}

	private void checkEmailUniversidad(String email){
		if (!email.endsWith("alum.us.es")){
			throw new ExcepcionAlumnoNoValido("El email del alumno debe terminar en @alum.us.es");
		}
	}
	
	
	public Expediente getExpediente() {
		return expediente;
	}
	
	public Integer getCurso(){
		Integer curso = 0;
		for(Asignatura a: getAsignaturas()){
			if( a.getCurso()>curso){
				curso = a.getCurso();
			}
		}
		return curso;
	}

	
	public Set<Asignatura> getAsignaturas(){
		//new hashset o asignaturas solo?
		return new HashSet<Asignatura>(asignaturas);
	}
	
	public void setEmail(String email){
		checkEmailUniversidad(email);
		super.setEmail(email);
	}
	
	public Boolean estaMatriculadoEn (Asignatura a){
		return asignaturas.contains(a);
	}
	
	public void eliminaAsignatura(Asignatura a){
		if (!estaMatriculadoEn(a)){
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno no está matriculado en esta asignatura");
		}
		asignaturas.remove(a);
	}
	
	public void matriculaAsignatura (Asignatura a){
		if (estaMatriculadoEn (a)){
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno ya está matriculado en la asignatura");
		}
		asignaturas.add(a);
	}
	
	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria,
			Double nota, Boolean mencionHonor){
		if(!estaMatriculadoEn(a)){
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno no está matriculado en la asignatura");
		}
		else{
			expediente.nuevaNota(new NotaImpl(a, curso, convocatoria, nota, mencionHonor));
			
		}
	}
	
	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria,
			Double nota){
		if(!estaMatriculadoEn(a)){
			throw new ExcepcionAlumnoOperacionNoPermitida ("El alumno no está matriculado en la asignatura");
		}
		else{
			expediente.nuevaNota(new NotaImpl (a, curso, convocatoria, nota));
		}
		
	}
	
	public String toString(){
		return "(" + getCurso() + "º) " + super.toString();
	}
	
}
