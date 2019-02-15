package fp.grados.tipos;

public interface Asignatura extends Comparable <Asignatura> {
String getNombre();
String getCodigo();
String getAcronimo();
Double getCreditos();
TipoAsignatura getTipo();
Integer getCurso();
Departamento getDepartamento();
void setDepartamento(Departamento departamento);

}
