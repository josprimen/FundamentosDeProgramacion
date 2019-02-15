package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionNotaNoValida;

public class NotaImpl implements Nota{
private Asignatura asignatura;
private Integer cursoAcademico;
private Convocatoria convocatoria;
private Double valor;
private Boolean mencionHonor;

public NotaImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor, Boolean mencionHonor){
	checkValor(valor);
	checkMencionDeHonor(mencionHonor,valor);
	this.asignatura=asignatura;
	this.cursoAcademico=cursoAcademico;
	this.convocatoria=convocatoria;
	this.valor=valor;
	this.mencionHonor=mencionHonor;
}
public NotaImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor){
	//ESTO LO QUE ESTÁ HACIENDO ES LLAMAR AL CONSTRUCTOR DE ARRIBA, POR ESO NO PONEMOS CHECKS, SE USAN LOS DE ARRIBA
	this (asignatura, cursoAcademico, convocatoria, valor,false);
}

private void checkValor(Double valor2){
	if(valor2<0 || valor2>10){
		throw new ExcepcionNotaNoValida("La nota tiene que estar entre 0 y 10");
	}
	}

private void checkMencionDeHonor(Boolean mencionDeHonor, Double valor){
	if(mencionDeHonor && valor<9){
		throw new ExcepcionNotaNoValida("La mención de honor se obtiene con nota superior o igual a 9");
	}
}
	
public boolean equals (Object o){
	boolean res = false;
	if (o instanceof Nota){
		Nota n = (Nota) o;
		res = getCursoAcademico().equals(n.getCursoAcademico())&&
				getAsignatura().equals(n.getAsignatura())&&
				getConvocatoria().equals(n.getConvocatoria());
	}
	return res;
}
public int hashCode(){
	return getCursoAcademico().hashCode() + getAsignatura().hashCode()*31 + getConvocatoria().hashCode()*31*31;
}
public int compareTo(Nota n){
	int res = getCursoAcademico().compareTo(n.getCursoAcademico());
	if (res == 0){
		res = getAsignatura().compareTo(n.getAsignatura());
		if (res == 0){
			res = getConvocatoria().compareTo(n.getConvocatoria());
		}
	}
	return res;
}

@Override
public Asignatura getAsignatura() {
	return asignatura;
}

@Override
public Integer getCursoAcademico() {
	return cursoAcademico;
}

@Override
public Convocatoria getConvocatoria() {
	return convocatoria;
}

@Override
public Double getValor() {
	return valor;
}

@Override
public Boolean getMencionHonor() {
	return mencionHonor;
}


public Calificacion getCalificacion() {
	Calificacion nota = Calificacion.SOBRESALIENTE;
	if(valor<5){
		nota = Calificacion.SUSPENSO;
	}
	else if (valor>=5 && valor<7){
		nota = Calificacion.APROBADO;
	}
	else if (valor>=7 && valor<9 ){
		nota = Calificacion.NOTABLE;
	}
	else if (getMencionHonor()){
		nota = Calificacion.MATRICULA_DE_HONOR;
	}
	return nota;
}
public String toString(){
	Integer g = getCursoAcademico() +1;
	String cadena;
	cadena=String.valueOf(g).substring(2);
	//cadena=g.toString().substring(2, 4);
	
	return getAsignatura()+","+getCursoAcademico()+"-"+ cadena +","+getConvocatoria()+","+getValor()+","+getCalificacion();
}
}