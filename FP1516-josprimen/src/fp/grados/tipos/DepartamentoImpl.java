package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

public class DepartamentoImpl implements Departamento{

	private String nombre;
	private Set<Profesor> profesores;
	private Set<Asignatura> asignaturas;
	
	public DepartamentoImpl (String nombre){
		this.nombre=nombre;
		this.profesores = new HashSet<Profesor>();
		this.asignaturas = new HashSet<Asignatura>();
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public Set<Profesor> getProfesores() {
		return new HashSet<Profesor>(profesores);
	}

	
	public Set<Asignatura> getAsignaturas() {
		return new HashSet<Asignatura>(asignaturas);
	}

	
	public void nuevoProfesor(Profesor prof) {
		profesores.add(prof);
		prof.setDepartamento(this);
		
	}

	
	public void eliminaProfesor(Profesor prof) {
		profesores.remove(prof);
		prof.setDepartamento(null);
	}

	
	public void nuevaAsignatura(Asignatura asig) {
		asignaturas.add(asig);
		asig.setDepartamento(this);
	}

	
	public void eliminaAsignatura(Asignatura asig) {
		asignaturas.remove(asig);
		asig.setDepartamento(null);
	}
	
	//Métodos del T8
	
	public void borraTutorias() {
		for(Profesor p: getProfesores()){
			p.borraTutorias();
		}
	}


	
	public void borraTutorias(Categoria c) {
		for(Profesor p: getProfesores()){
			if(p.getCategoria().equals(c)){
				p.borraTutorias();
			}
		}
	}


	
	public Boolean existeProfesorAsignado(Asignatura a) {
		Boolean res = false;
		for(Profesor p: getProfesores()){
			if(p.getAsignaturas().contains(a)){
				res= true;
				break;
			}
		}
		return res;
	}


	
	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean res = true;
		for(Asignatura a: getAsignaturas()){
			if(!existeProfesorAsignado(a)){
				res = false;
				break;
			}
		}
		return res;
	}


	
	public void eliminaAsignacionProfesorado(Asignatura a) {
			for(Profesor p: getProfesores()){
			if(p.getAsignaturas().contains(a)){
				p.eliminaAsignatura(a);
			}
			}
	}
	
	//********************************
	
	public boolean equals (Object o){
		boolean res = false;
		if (o instanceof Departamento){
			Departamento d = (Departamento) o;
			res = getNombre().equals(d.getNombre());
		}
		return res;
	}
	
	public int hashCode(){
		return getNombre().hashCode();
	}
	
	public int compareTo(Departamento d){
		return getNombre().compareTo(d.getNombre());
	}
	
	public String toString(){
		return getNombre();
	}


	
}
