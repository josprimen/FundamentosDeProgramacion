package fp.grados.tipos;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionBecarioNoValido;

public class BecarioImpl extends AlumnoImpl implements Becario{
	
	private Beca beca;
	private LocalDate fechaComienzo;
	
	public BecarioImpl(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email,
			Beca beca, LocalDate fechaComienzo){
		super( dni, nombre, apellidos, fechaNacimiento,email);
		checkFechaComienzo(fechaComienzo);
		this.beca = beca;
		this.fechaComienzo=fechaComienzo;
	}

	public BecarioImpl (String dni, String nombre, String apellidos,
	 LocalDate fechaNacimiento, String email, String codigo, Double cuantiaTotal,
	 Integer duracion, TipoBeca tipo, LocalDate fechaComienzo){
		super (dni, nombre, apellidos, fechaNacimiento, email);
		checkFechaComienzo(fechaComienzo);
		this.fechaComienzo=fechaComienzo;
	
	}
	
	
	private void checkFechaComienzo (LocalDate fechaComienzo){
		if(!fechaComienzo.isAfter(LocalDate.now())){
			throw new ExcepcionBecarioNoValido("La fecha de comienzo tiene que ser posterior a la actual");
		}
		}
	public Beca getBeca(){
		return beca;
	}
	public LocalDate getFechaComienzo(){
		return fechaComienzo;
	}
	public void setFechaComienzo(LocalDate fecha){
		checkFechaComienzo(fechaComienzo);
		fechaComienzo = fecha;
	}
	public LocalDate getFechaFin(){
		return getFechaComienzo().plusMonths(getBeca().getDuracion());
	}
	public void setEmail(String email){
		throw new UnsupportedOperationException("El email del becario no se puede modificar");
	}
	
	public String toString(){
		return super.toString()+" "+ getBeca().toString();
	}
	
	
	
	
	
	
	
	
	
	
	

}
