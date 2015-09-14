package com.ionicframework.cursos476803.util;

import com.ionicframework.cursos476803.Model.Curso;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase Auxiliar que permite pasar objetos de la clase Curso a traves de Actividades.
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @version 1.0 23/02/2015
 *
 */

public class DataPass implements Serializable{
    private ArrayList<Curso> cursos;

    public DataPass(ArrayList<Curso> data) {
        this.cursos = data;
    }

    public ArrayList<Curso> getCursos() {
        return this.cursos;
    }
}
