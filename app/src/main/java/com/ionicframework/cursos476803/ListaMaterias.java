package com.ionicframework.cursos476803;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.ionicframework.cursos476803.Model.Curso;
import com.ionicframework.cursos476803.util.DataPass;
import com.ionicframework.cursos476803.util.ListaAdaptador;
import java.util.ArrayList;


/**
 * Clase encargada de mostrar el listado de cursos consultados por un nombre de materia.
 *
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @author Yoiner Esteban Gomez <yoiner.gomez22@gmail.com>
 * @version 1.0 30/09/2015
 *
 */
public class ListaMaterias extends AppCompatActivity {

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

        tvNombre.setText((String) getIntent().getSerializableExtra("nombreMateria"));
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
                    TextView tvHorario = (TextView) view.findViewById(R.id.txtVw_horarioCurso);
                    if (tvHorario != null){
                        tvHorario.setText(curso.getCalendarizacion().get(0).getHorarios());
                    }
                    TextView tvProfesor = (TextView) view.findViewById(R.id.txtVw_profesorCurso);
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
