package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;

public class DespachoImpl extends EspacioImpl implements Despacho{
	private Set<Profesor> profesores = new HashSet<Profesor>();
	
	public DespachoImpl (String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores){
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		checkNumeroProfesores (profesores);
		this.profesores= profesores;
		
	}
	
	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Profesor profesor){
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		checkNumeroProfesores (profesores);
		profesores.add(profesor);
	}
	
	public DespachoImpl(String nombre, Integer capacidad, Integer planta){
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		checkNumeroProfesores (profesores);
		this.profesores= new HashSet<Profesor>();
	}
	
	private void checkNumeroProfesores (Set<Profesor> profesores){
		if(profesores.size()>getCapacidad()){
			throw new ExcepcionDespachoNoValido ("El número de profesores no puede superar la capacidad del espacio");
		}
	}
	
	public void setCapacidad(Integer capacidad){
		checkNumeroProfesores(profesores);
		super.setCapacidad(capacidad);
	}
	
	public void setTipo (TipoEspacio tipo){
		throw new UnsupportedOperationException("El tipo no puede ser cambiado, debe ser otro");
	}
	
	public Set<Profesor> getProfesores(){
		return new HashSet<Profesor>();
	}
	
	public void setProfesores(Set<Profesor> profesores) {
	checkNumeroProfesores(profesores);
		this.profesores=profesores;
		
	}
	
	public String toString(){
		return super.toString()+" "+ getProfesores();
	}


}




