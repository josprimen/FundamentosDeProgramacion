package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado {

	private String nombre;
	private Set<Asignatura> asignaturasObligatorias;
	private Set<Asignatura> asignaturasOptativas;
	private Double creditosMinimosOptativas;
	
	public GradoImpl(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas, Double creditosMinimosOptativas){
		checkCreditosOptativas(asignaturasOptativas);
		checkCreditosMinimosOptativas(creditosMinimosOptativas, asignaturasOptativas);
		this.nombre= nombre;
		this.asignaturasObligatorias= asignaturasObligatorias;
		this.asignaturasOptativas= asignaturasOptativas;
		this.creditosMinimosOptativas= creditosMinimosOptativas;
		
	}

	//CUATRIMESTRAL?
	//((!(creditos.equals(a.getCreditos()))) || a.getTipo().ANUAL)
	private void checkCreditosOptativas(Set<Asignatura> asigOpt){
		Boolean esPrimero = true;
		Double creditos = 0.0;
		for(Asignatura a: asigOpt){
			if(esPrimero){
				esPrimero = false;
				creditos = a.getCreditos();
			}
			else if (creditos.equals(a.getCreditos())){
				throw new ExcepcionGradoNoValido ("Todas las asignaturas optativas de un grado deben tener el mismo número de créditos");
			}
		}
	}
	
	
	private void checkCreditosMinimosOptativas(Double credits, Set<Asignatura> asigOpt){
		Double creditosOpt = 0.0;
		for(Asignatura a: asigOpt){
			creditosOpt += a.getCreditos();
		}
		if(0>credits || credits<creditosOpt){
			throw new ExcepcionGradoNoValido ("El número mínimo de créditos de asignaturas optativas que debe cursar un alumno debe estar comprendido"
					+ " entre cero y el número total de créditos de asignaturas optativas del grado.");
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	
	public Set<Asignatura> getAsignaturasObligatorias() {
		return new HashSet<Asignatura>(asignaturasObligatorias);
	}

	
	public Set<Asignatura> getAsignaturasOptativas() {
		return new HashSet<Asignatura>(asignaturasOptativas);
	}

	
	public Double getNumeroMinimoCreditosOptativas() {
		return creditosMinimosOptativas;
	}

	
	public Double getNumeroTotalCreditos() {
		Double creditos = 0.0;
		for(Asignatura asig: getAsignaturasObligatorias()){
			creditos += asig.getCreditos();
		}
		return creditos + getNumeroMinimoCreditosOptativas();
	}

	/*public Set<Departamento> getDepartamentos(){
		Set<Departamento> departamentos = new HashSet<Departamento>();
		for(Asignatura a: getAsignaturasObligatorias()){
			departamentos.add(a.getDepartamento());
		}
		for(Asignatura a: getAsignaturasOptativas()){
			departamentos.add(a.getDepartamento());
		}
		return departamentos;
	}*/
	
	
	public Set<Departamento> getDepartamentos() {
		Set<Departamento> departamentos = new HashSet<Departamento>();
		for(Asignatura a: getAsignaturas()){
			departamentos.add(a.getDepartamento());
		}
		return departamentos;
	}
	
	//Preguntar si se puede hacer un metodo no estatico para no tener que hacer dos "for" en el de arriba
	public Set<Asignatura> getAsignaturas(){
		Set<Asignatura> asignaturas = getAsignaturasOptativas();
		asignaturas.addAll(getAsignaturasObligatorias());
		return asignaturas;
		
		
	}

	
	public Set<Profesor> getProfesores() {
		Set<Profesor> profesores = new HashSet<Profesor>();
		for(Departamento d: getDepartamentos()){
			profesores.addAll(d.getProfesores());
			
		}
		return profesores;
	}

	
	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> res = new HashSet<Asignatura>();
		for(Asignatura a: getAsignaturasObligatorias()){
			if(a.getCurso().equals(curso)){
				res.add(a);
			}
		}
		for(Asignatura a: getAsignaturasOptativas()){
			if(a.getCurso().equals(curso)){
				res.add(a);
			}
		}
		return res;
	}

	//En el del tio faltan los break y vuelve a usar dos for...
	public Asignatura getAsignatura(String codigo) {
		Asignatura res = null;
		for(Asignatura a: getAsignaturas()){
			if(a.getCodigo().equals(codigo)){
				res = a;
				break;
			}
		}
		return res;
	}
	
	public boolean equals (Object o){
		boolean res = false;
		if (o instanceof Grado){
			Grado g = (Grado) o;
			res = getNombre().equals(g.getNombre());
		}
		return res;
	}
	
	public int hashCode(){
		return getNombre().hashCode();
	}
	
	public int compareTo(Grado g){
		return getNombre().compareTo(g.getNombre());
	}
	
	public String toString(){
		return getNombre();
	}
	
}
