package fp.grados.tipos;

import java.time.LocalDate;

public interface Persona extends Comparable <Persona>{
String getDNI();
String getNombre();
String getApellidos();
LocalDate getFechaNacimiento();
String getEmail();
Integer getEdad();
void setNombre(String nombre);
void setApellidos(String apellidos);
void setDNI (String dni);
void setFechaNacimiento (LocalDate fecha);
void setEmail (String email);
//edad no se pone porque porque al modificar fecha de nacimiento se hace edad tb
}
