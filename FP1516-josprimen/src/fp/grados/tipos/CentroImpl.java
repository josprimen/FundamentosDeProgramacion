package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro {
	
	private String nombre;
	private String direccion;
	private Integer numeroPlantas;
	private Integer numeroSotanos;
	private Set<Espacio> espacios;
	
	public CentroImpl(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos){
		checkPlanta(numeroPlantas);
		checkSotano(numeroSotanos);
		this.nombre= nombre;
		this.direccion=direccion;
		this.numeroPlantas=numeroPlantas;
		this.numeroSotanos=numeroSotanos;
		this.espacios = new HashSet<Espacio>();
	}

	public void checkPlanta (Integer plantas){
		if(plantas<1){
			throw new ExcepcionCentroNoValido("Un centro debe tener al menos una planta");
		}
	}
	
	public void checkSotano (Integer sotanos){
		if(sotanos<0){
			throw new ExcepcionCentroNoValido("Un centro no puede tener menos de cero sótanos");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getNumeroPlantas() {
		return numeroPlantas;
	}

	public Integer getNumeroSotanos() {
		return numeroSotanos;
	}

	public Set<Espacio> getEspacios() {
		return new HashSet<Espacio>(espacios);
	}

	//EEEEEEEEEEEEEOOO
	public void nuevoEspacio(Espacio e) {
		if(-getNumeroSotanos()<= e.getPlanta() && e.getPlanta()<= getNumeroPlantas()-1 ){
			espacios.add(e);
		}
		else{
			throw new ExcepcionCentroOperacionNoPermitida ("Planta del espacio no válida");
		}
	}

	public void eliminaEspacio(Espacio e) {
		espacios.remove(e);
	}
	
	//Métodos del T8
	
	public Integer[] getConteosEspacios() {
		Integer [] conteos = {0,0,0,0,0};
		for(Espacio e: getEspacios()){
			switch(e.getTipo()){
			case TEORIA:
				conteos[0]++;
				break;
			case LABORATORIO:
				conteos[1]++;
				break;
			case SEMINARIO:
				conteos[2]++;
				break;
			case EXAMEN:
				conteos[3]++;
				break;
			case OTRO:
				conteos[4]++;
				break;
			}
			
		}
		return conteos;
	}

	
	public Set<Despacho> getDespachos() {
		Set<Despacho> despachos = new HashSet<Despacho>();
		for(Espacio e: getEspacios()){
			if(e instanceof Despacho){
				despachos.add((Despacho) e);
			}
		}
		return despachos;
	}

	//Devuelve los depachos que contienen al menos un profesor del departamento d
	public Set<Despacho> getDespachos(Departamento d) {
		Set<Despacho> despachos = new HashSet<Despacho>();
		for(Despacho t: getDespachos()){
			if(existeProfesorDepartamento(t.getProfesores(), d)){
				despachos.add(t);
			}
		}
		return despachos;
	}
	
	//Método auxiliar para el método encima de este: Pilla una lista de profesores(en este caso la lista de 
	//profesores que están en el despacho t) y mira si alguno tiene como departamento el d
	
	private boolean existeProfesorDepartamento (Set<Profesor> profesores,
			Departamento d){
		Boolean existe = false;
		for(Profesor p: profesores){
			if(p.getDepartamento().equals(d)){
				existe = true;
				break;
			}
		}
		return existe;
	}

	
	public Set<Profesor> getProfesores() {
		Set<Profesor> profesores = new HashSet<Profesor>();
		for(Despacho d: getDespachos()){
			profesores.addAll(d.getProfesores());
		}
		return null;
	}

	
	public Set<Profesor> getProfesores(Departamento d) {
		Set<Profesor> profesores = new HashSet<Profesor>();
		for(Profesor p: getProfesores()){
			if(p.getDepartamento().equals(d)){
				profesores.add(p);
			}
		}
		return profesores;
	}

	
	public Espacio getEspacioMayorCapacidad() {
		Integer capacidad = 0;
		Espacio esp = null;
		for(Espacio e: getEspacios()){
			//aqui en el if puedes meter capacidad == 0 tb
			if(esp == null || e.getCapacidad()>capacidad ){
				capacidad = e.getCapacidad();
				esp = e;
			}
		}
		if(esp == null){
			throw new ExcepcionCentroOperacionNoPermitida ("No hay espacios en el centro");
		}
		
			return esp;
		
	}

	
	//**************
	
	
	public boolean equals (Object o){
		boolean res = false;
		if (o instanceof Centro){
			Centro c = (Centro) o;
			res = getNombre().equals(c.getNombre());
		}
		return res;
	}
	
	public int hashCode(){
		return getNombre().hashCode();
	}
	
	public int compareTo (Centro c){
		return getNombre().compareTo(c.getNombre());
	}
	
	public String toString(){
		return getNombre();
	}

	
}
