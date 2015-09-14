package com.ionicframework.cursos476803;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;


import com.ionicframework.cursos476803.Model.Curso;


/**
 * Created by Programador on 14/09/2015.
 */
public class DetalleCurso extends Activity {



    /**
     * String con la direccion base del recurso
     */
    String url = "http://172.21.35.139:1337/calendario/";
    /**
     * ProgressDialog que indica la carga de datos
     */
    ProgressDialog pDialog;
    /**
     * Curso Al cual se mostrara el detalle
     */
    Curso curso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curso = (Curso) getIntent().getSerializableExtra("curso");


    }




}
