package com.ionicframework.cursos476803;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ionicframework.cursos476803.Model.Curso;
import com.ionicframework.cursos476803.util.DataPass;

import java.util.ArrayList;

/**
 * Created by Programador on 28/09/2015.
 */
public class ListaMaterias extends Activity {

    ArrayList<Curso> cursos;
    String nombreMateria;
    ListView lista;
    TextView tvNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_materias);
        tvNombre = (TextView) findViewById(R.id.txtVw_nombreEnLista);
        lista = (ListView) findViewById(R.id.lstVw_listadoMaterias);
        nombreMateria = getIntent().getStringExtra("nombreMateria");
        tvNombre.setText(nombreMateria);
        DataPass dp = (DataPass) getIntent().getSerializableExtra("cursos");
        cursos = dp.getCursos();

        Toast.makeText(
                getApplicationContext(), cursos.get(0).getNombreMateria() + "\n"
                        + cursos.get(0).getIdCurso() + "\n"
                        + cursos.get(0).getCalendarizacion().get(0).getAula() + "\n"
                        + cursos.get(0).getCalendarizacion().get(0).getProfesores() + "\n"
                        + cursos.get(0).getCalendarizacion().get(0).getHorarios(),
                Toast.LENGTH_LONG).show();

    }




}
