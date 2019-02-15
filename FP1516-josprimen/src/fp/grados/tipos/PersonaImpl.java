package fp.grados.tipos;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionPersonaNoValida;

public class PersonaImpl implements Persona{
	private String dni;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String email;
	
	public PersonaImpl (String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email){
		checkDNI(dni);
		checkEmail(email);
		checkFechaNacimiento(fechaNacimiento);
		this.dni=dni;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.fechaNacimiento=fechaNacimiento;
		this.email=email;
	}
	public PersonaImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento){
		//Same, aqui no se ponen checks porque ese constructor invoca al de arriba y el de arriba ya los tiene
		this (dni,nombre,apellidos,fechaNacimiento,"");
	}
	
	private Boolean checkDniTipoCaracteres(String dni){
		return dni.length() == 9 &&
				Character.isDigit(dni.charAt(0)) &&
				Character.isDigit(dni.charAt(1)) &&
				Character.isDigit(dni.charAt(2)) &&
				Character.isDigit(dni.charAt(3)) &&
				Character.isDigit(dni.charAt(4)) &&
				Character.isDigit(dni.charAt(5)) &&
				Character.isDigit(dni.charAt(6)) &&
				Character.isDigit(dni.charAt(7)) &&
				Character.isLetter(dni.charAt(8));
	}
	
	private Boolean checkDniLetra(String dni){
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		Integer numeroDni = new Integer(dni.substring(0, 8));
		return dni.charAt(8)==letras.charAt(numeroDni%23);
	}
	
	private void checkDNI(String dni){
		Boolean dniCorrecto = checkDniTipoCaracteres(dni) && checkDniLetra(dni);
		if (!dniCorrecto){
			throw new ExcepcionPersonaNoValida("DNI incorrecto");
		}
	}
	
	private void checkEmail(String email){
		if (!(email.isEmpty() || email.contains("@"))){
			throw new ExcepcionPersonaNoValida("El email debe estar compuesto de usuario, arroba y servidor de correo");
		}
	}
	
	private void checkFechaNacimiento (LocalDate fechaNacimiento){
		if(!fechaNacimiento.isBefore(LocalDate.now())){
			throw new ExcepcionPersonaNoValida("La fecha de nacimiento de una persona debe ser anterior \n a la fecha actual del sistema");
		}
	}
	public boolean equals (Object o){
		boolean res = false;
		if (o instanceof Persona){
			Persona p = (Persona) o;
			res = getDNI().equals(p.getDNI())&&
					getNombre().equals(p.getNombre())&&
					getApellidos().equals(p.getApellidos());
		}
		return res;
	}
	public int hashCode(){
		return getDNI().hashCode() + getNombre().hashCode() * 31 + getApellidos().hashCode() *31*31;
	}
	public int compareTo(Persona p){
		int res = getApellidos().compareTo(p.getApellidos());
		if( res == 0){
			res = getNombre().compareTo(p.getNombre());
			if (res == 0){
				res = getDNI().compareTo(p.getDNI());
			}
		}
		return res;
	}
	
	
	
	public String getDNI() {
		return dni;
	}
	@Override
	public String getNombre() {
		return nombre;
	}
	@Override
	public String getApellidos() {
		return apellidos;
	}
	@Override
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public Integer getEdad() {
		//return (int) getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
		return Period.between(getFechaNacimiento(), LocalDate.now()).getYears();
	}
	@Override
	public void setNombre(String nombre) {
		this.nombre=nombre;
		
	}
	@Override
	public void setApellidos(String apellidos) {
		this.apellidos=apellidos;
		
	}
	@Override
	public void setDNI(String dni) {
		checkDNI(dni);
		this.dni=dni;
		
	}
	@Override
	public void setFechaNacimiento(LocalDate fecha) {
		checkFechaNacimiento(fecha);
		this.fechaNacimiento=fecha;
		
	}
	@Override
	public void setEmail(String email) {
		checkEmail(email);
		this.email=email;
		
	}
	public String toString(){
		return getDNI()+" - "+getApellidos()+", "+getNombre()+" - "+getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
