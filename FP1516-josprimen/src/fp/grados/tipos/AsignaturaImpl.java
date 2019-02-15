package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;

public class AsignaturaImpl implements Asignatura {
	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;
	
	
	public AsignaturaImpl (String nombre, String codigo, Double creditos,TipoAsignatura tipo, Integer curso){
		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);
		this.nombre=nombre;
		this.codigo=codigo;
		this.creditos=creditos;
		this.tipo=tipo;
		this.curso=curso;
		this.departamento= null;
		
	}
	
	public AsignaturaImpl(String nombre, String codigo, Double creditos,TipoAsignatura tipo, Integer curso, Departamento departamento){
		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);
		this.nombre=nombre;
		this.codigo=codigo;
		this.creditos=creditos;
		this.tipo=tipo;
		this.curso=curso;
		setDepartamento(departamento);
	}
	
	public String getNombre(){
	return nombre;
}
	//TODO: rellenar método
	public String getAcronimo(){
		String acronimo = "";
		for(char c: getNombre().toCharArray())
			if(Character.isUpperCase(c)){
				acronimo += c;
			}
		return acronimo;
	}
	public String getCodigo(){
		return codigo;
	}
	public Double getCreditos(){
		return creditos;
	}
	public TipoAsignatura getTipo(){
		return tipo;
	}
	public Integer getCurso(){
		return curso;
	}
	
	public Departamento getDepartamento(){
		return departamento;
	}
	
	public void setDepartamento (Departamento nuevoDepartamento){
		if(nuevoDepartamento != this.departamento){
			Departamento antiguoDepartamento = this.departamento;
			this.departamento = nuevoDepartamento;
		
		if(antiguoDepartamento != null){
			antiguoDepartamento.eliminaAsignatura(this);
		}
		if (nuevoDepartamento!= null){
			nuevoDepartamento.nuevaAsignatura(this);
	}
	}
	}
	private void checkCodigo(String codigo){
		Boolean correcto = codigo.length()==7 &&
			Character.isDigit(codigo.charAt(0))	&&
			Character.isDigit(codigo.charAt(1))	&&
			Character.isDigit(codigo.charAt(2))	&&
			Character.isDigit(codigo.charAt(3))	&&
			Character.isDigit(codigo.charAt(4))	&&
			Character.isDigit(codigo.charAt(5))	&&
			Character.isDigit(codigo.charAt(6));
			if(!correcto){
				throw new ExcepcionAsignaturaNoValida(
						"El código debe estar compuesto por 7 digitos.");
			}
	
		}
	
	private void checkCreditos(Double creditos){
	if (creditos<=0){
    throw new ExcepcionAsignaturaNoValida("El número de créditos debe ser mayor que cero");
			}
		}	
	
	private void checkCurso (Integer curso){
		if(curso<1||curso>4){
			throw new ExcepcionAsignaturaNoValida("El curso tiene que estar entre 1 y 4");
		}
	}
	
	public boolean equals (Object o){
		boolean res = false;
		if (o instanceof Asignatura){
			Asignatura a = (Asignatura) o;
			res = getCodigo().equals(a.getCodigo());
		}
		return res;
	}
	public int hashCode(){
		return getCodigo().hashCode();
	}
	public int compareTo(Asignatura a) {
		return getCodigo().compareTo(a.getCodigo());
		}
	
	
	public String toString(){
		return "("+ getCodigo() + ")"+" "+ getNombre();
	}
	
	
	
	//CharAt o IsDigit
	
	
	
}
