package com.ionicframework.cursos476803;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import com.ionicframework.cursos476803.Model.Curso;
import com.ionicframework.cursos476803.util.DataPass;


/**
 * Created by Programador on 14/09/2015.
 */
public class DetalleCurso extends Activity {



    private TextView tvNombreMateria;
    private TextView tvAula;
    private TextView tvProfesor;
    private TextView tvHorario;
    private TextView tvGrupo;

    Curso curso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_consulta);
        DataPass dp = (DataPass) getIntent().getSerializableExtra("curso");
        curso = dp.getCurso() ;

/*        Toast.makeText(
                getApplicationContext(), curso.getNombreMateria() + "\n"
                        + curso.getIdCurso() + "\n"
                        + curso.getCalendarizacion().get(0).getAula() + "\n"
                        + curso.getCalendarizacion().get(0).getProfesores() + "\n"
                        + curso.getCalendarizacion().get(0).getHorarios(),
                Toast.LENGTH_LONG).show();*/

        asignaDetalleCurso();
    }

    /**
     * Establece a cada uno de los componentes de la vista su respectiva información
     */
    public void asignaDetalleCurso(){
        tvNombreMateria = (TextView) findViewById(R.id.txtVw_nombreCurso);
        tvProfesor = (TextView) findViewById(R.id.txtVw_profesorCurso);
        tvHorario = (TextView) findViewById(R.id.txtVw_horarioCurso);
        tvAula = (TextView) findViewById(R.id.txtVw_mostrarAula);
        tvGrupo = (TextView) findViewById(R.id.txtVw_mostrarGrupo);
        asignaNombreMateria();
        asignaProfesor();
        asignaHorario();
        asignaAula();
        asignaGrupo();
    }

    public void asignaNombreMateria(){
        tvNombreMateria.setText(curso.getNombreMateria());
    }

    public void asignaProfesor(){
        String profesor="";
        for(int i=0; i<curso.getCalendarizacion().size();i++){
            profesor=profesor+curso.getCalendarizacion().get(i).getProfesores();
        }
        tvProfesor.setText(profesor);
    }

    public void asignaAula(){
        StringBuilder aula = new StringBuilder();
        String bloqueAula;
        String subAula;
        for(int i=0; i<curso.getCalendarizacion().size();i++){
            bloqueAula = curso.getCalendarizacion().get(i).getAula();
            subAula = bloqueAula.substring(bloqueAula.length() - 3);
            bloqueAula = bloqueAula.replace(subAula, "-" + subAula);
            aula.append(bloqueAula);
            if (i!=curso.getCalendarizacion().size()-1) aula.append("\n");
        }
        tvAula.setText(aula.toString());
    }

    public void asignaHorario(){
        String horario="";
        for(int i=0; i<curso.getCalendarizacion().size();i++){
            horario=horario+curso.getCalendarizacion().get(i).getHorarios();
        }
        tvHorario.setText(horario);
    }

    public void asignaGrupo(){
        tvGrupo.setText(curso.getGrupoCurso());
    }



}
