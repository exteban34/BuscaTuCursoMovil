package com.ionicframework.cursos476803.Model;

import com.ionicframework.cursos476803.DetalleCurso;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase para el tranporte de datos de Cursos en el sistema
 * @author Heinner Esteban Alvarez Rivas <exteban34@gmail.com>
 * @version 1.0 25/02/2015
 *
 */
public class Curso implements Serializable{
    /**
     * Contiene el  codigo de la materia  a la que pertenece el curso
     */
    String codigoMateria;
    /**
     * Contiene el grupo del curso
     */
    String grupoCurso;
    /**
     * Contiene el nombre de la materia a la que pertenece el curso
     */
    String nombreMateria;

    ArrayList<DetalleCalendario> calendarizacion;



    /**
     * Contructor de la clase
     * @param codigoMateria
     * @param grupoCurso
     * @param nombreMateria
     * @param calendarizacion
     */
    public Curso(String codigoMateria, String grupoCurso, String nombreMateria, ArrayList<DetalleCalendario> calendarizacion) {
        this.codigoMateria = codigoMateria;
        this.grupoCurso = grupoCurso;
        this.nombreMateria = nombreMateria;
        this.calendarizacion = calendarizacion;
    }

    /**
     * Getters and Setters
     */

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getGrupoCurso() {
        return grupoCurso;
    }

    public void setGrupoCurso(String grupoCurso) {
        this.grupoCurso = grupoCurso;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public ArrayList<DetalleCalendario> getCalendarizacion() {
        return calendarizacion;
    }

    public void setCalendarizacion(ArrayList<DetalleCalendario> calendarizacion) {
        this.calendarizacion = calendarizacion;
    }
}
