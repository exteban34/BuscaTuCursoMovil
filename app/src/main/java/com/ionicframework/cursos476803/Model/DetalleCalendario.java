package com.ionicframework.cursos476803.Model;

import java.io.Serializable;

/**
 * Clase para el tranporte de datos de Detalles de calendario en el sistema
 * @author Heinner Esteban Alvarez Rivas <exteban34@gmail.com>
 * @version 1.0 14/09/2015
 *
 */
public class DetalleCalendario  implements Serializable{
    /**
     * Contiene el aula de un curso.
     */
    String aula;
    /**
     * Contiene el/loss horarios de un curso
     */
    String horarios[];
    /**
     * Contiene el/los profesores de un curso
     */
    String profesores[];

    /**
     * Constructor
     * @param aula
     * @param horarios
     * @param profesores
     */
    public DetalleCalendario(String aula, String[] horarios, String[] profesores) {
        this.aula = aula;
        this.horarios = horarios;
        this.profesores = profesores;
    }


    /**
     * Getters and Setters
     */

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String[] getHorarios() {
        return horarios;
    }

    public void setHorarios(String[] horarios) {
        this.horarios = horarios;
    }

    public String[] getProfesores() {
        return profesores;
    }

    public void setProfesores(String[] profesores) {
        this.profesores = profesores;
    }
}
