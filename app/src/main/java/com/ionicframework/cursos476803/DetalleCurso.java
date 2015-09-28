package com.ionicframework.cursos476803;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


import com.ionicframework.cursos476803.Model.Curso;
import com.ionicframework.cursos476803.util.DataPass;


/**
 * Created by Programador on 14/09/2015.
 */
public class DetalleCurso extends Activity {




    Curso curso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_consulta);
        DataPass dp = (DataPass) getIntent().getSerializableExtra("curso");
        curso = dp.getCurso() ;

        Toast.makeText(
                getApplicationContext(), curso.getNombreMateria() + "\n"
                        + curso.getIdCurso() + "\n"
                        + curso.getCalendarizacion().get(0).getAula() + "\n"
                        + curso.getCalendarizacion().get(0).getProfesores() + "\n"
                        + curso.getCalendarizacion().get(0).getHorarios(),
                Toast.LENGTH_LONG).show();



    }




}
