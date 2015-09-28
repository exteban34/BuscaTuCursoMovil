package com.ionicframework.cursos476803;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ionicframework.cursos476803.Model.Curso;
import com.ionicframework.cursos476803.util.DataPass;
import com.ionicframework.cursos476803.util.ListaAdaptador;

import java.util.ArrayList;

/**
 * Created by Programador on 28/09/2015.
 */
public class ListaMaterias extends Activity {

    ArrayList<Curso> cursos;
    String nombreMateria;
    ListView lista;
    TextView tvNombre;
    Curso curso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_materias);
        tvNombre = (TextView) findViewById(R.id.txtVw_nombreEnLista);
        lista = (ListView) findViewById(R.id.lstVw_listadoMaterias);
        nombreMateria = getIntent().getStringExtra("nombreMateria");
        Log.i("nombre", nombreMateria);
        tvNombre.setText(nombreMateria);
        DataPass dp = (DataPass) getIntent().getSerializableExtra("cursos");
        cursos = dp.getCursos();

        lista.setAdapter(new ListaAdaptador(this, R.layout.muestra_materias,cursos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    curso = (Curso) entrada;
                    TextView tvGrupo = (TextView) view.findViewById(R.id.txtVw_grupoCurso);
                    if (tvGrupo != null){
                        tvGrupo.setText(curso.getGrupoCurso());
                    }
                    TextView tvHorario = (TextView) view.findViewById(R.id.txtVw_mostrarHorario);
                    if (tvHorario != null){
                        tvHorario.setText(curso.getCalendarizacion().get(0).getHorarios());
                    }
                    TextView tvProfesor = (TextView) view.findViewById(R.id.txtVw_mostrarProfesor);
                    if (tvProfesor != null){
                        tvProfesor.setText(curso.getCalendarizacion().get(0).getProfesores());
                    }
                }

            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view,
                                    int posicion, long id) {

                curso = (Curso) pariente
                        .getItemAtPosition(posicion);

                Intent e= new Intent("com.ionicframework.cursos476803.DetalleCurso");
                e.putExtra("curso", new DataPass(curso));
                startActivity(e);

            }
        });




    }




}
