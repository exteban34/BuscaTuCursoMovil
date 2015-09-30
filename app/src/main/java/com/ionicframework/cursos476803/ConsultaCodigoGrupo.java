package com.ionicframework.cursos476803;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.ionicframework.cursos476803.Model.Curso;
import com.ionicframework.cursos476803.Model.DetalleCalendario;
import com.ionicframework.cursos476803.util.DataPass;
import com.ionicframework.cursos476803.util.RequestGetJson;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Clase Encargada de realizar la consulta de diferentes cursos con el nombre de la materia,
 * provee un campo para digitar dicho nombre.
 *
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @author Yoiner Esteban Gomez <yoiner.gomez22@gmail.com>
 * @version 1.0 30/09/2015
 *
 */
import java.util.ArrayList;

/**
 * Clase Encargada de realizar la consulta de un curso dado el codigo de la materia
 * y el grupo, provee campos para digitar dicha informacion.
 *
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @author Yoiner Esteban Gomez <yoiner.gomez22@gmail.com>
 * @version 1.0 30/09/2015
 *
 */
public class ConsultaCodigoGrupo extends AppCompatActivity {

    ProgressDialog pDialog;
    EditText edCodigo;
    EditText edGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_codigo_grupo);
        edCodigo = (EditText) findViewById(R.id.edtTxt_codigoCurso);
        edGrupo = (EditText) findViewById(R.id.edtTxt_grupoCurso);
    }


    /**
     * Clase Asincrona encargada de consultar el onjeto JSON  con el curso y lanzar la actividad que provee
     * la visualizacion del detalle del curso.
     *
     * @author Heinner Esteban Alvarez <exteban34@gmail.com>
     * @author Yoiner Esteban Gomez <yoiner.gomez22@gmail.com>
     * @version 1.0 30/09/2015
     */
    private class LeerJSONCursoCodigoGrupo extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            pDialog = new ProgressDialog(ConsultaCodigoGrupo.this);
            pDialog.setMessage(getString(R.string.carga_datos));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... urls) {
            return RequestGetJson.getJson(urls[0]);
        }

        protected void onPostExecute(String result) {


            try {
                pDialog.dismiss();
                Log.i("result", result);
                JSONArray arreglo= new JSONArray(result);
                JSONObject cursoJson = arreglo.getJSONObject(0);
                JSONArray calendarizacionJSON = cursoJson.getJSONArray("calendarizacion");
                ArrayList<DetalleCalendario> calendarizacion = new ArrayList<DetalleCalendario>();


                for(int i=0;i<calendarizacionJSON.length();i++){
                    JSONObject detalleCursoJSON = calendarizacionJSON.getJSONObject(i);
                    String aula= (String) detalleCursoJSON.get("aula");
                    String profesores="";
                    String horarios="";
                    JSONArray horariosJSON = detalleCursoJSON.getJSONArray("horario");
                    JSONArray profesoresJSON = detalleCursoJSON.getJSONArray("profesor");

                    for(int j=0;j<horariosJSON.length();j++){
                        horarios=horarios+"\n"+horariosJSON.getString(j);
                    }

                    for(int k=0;k<horariosJSON.length();k++){
                        profesores=profesores+"\n"+profesoresJSON.getString(k);
                    }

                    DetalleCalendario detalleCalendario = new DetalleCalendario(aula,horarios,profesores);
                    calendarizacion.add(detalleCalendario);
                }

                String codigoMateria = cursoJson.getString("materia");
                String grupoCurso = cursoJson.getString("grupo");
                String nombreMateria = cursoJson.getString("nombreMateria");
                String idCurso = cursoJson.getString("id");
                Curso curso = new Curso(codigoMateria,grupoCurso,nombreMateria,idCurso,calendarizacion);
                Intent e= new Intent("com.ionicframework.cursos476803.DetalleCurso");
                e.putExtra("curso", new DataPass(curso));
                startActivity(e);



            } catch (Exception e) {
                Toast.makeText(
                        getApplicationContext(),
                        getResources().getString(R.string.error_consulta_Codigo_Grupo),
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }
    }


    public void clickConsultarCodigoCurso (View view){
        String codigo = edCodigo.getText().toString();
        String grupo = edGrupo.getText().toString();
        if(codigo.isEmpty() || grupo.isEmpty()){
            Toast.makeText(
                    getApplication(), getResources().getString(R.string.error_ingresar_codigo_grupo),
                    Toast.LENGTH_LONG).show();
        }else{
            new LeerJSONCursoCodigoGrupo()
                    .execute(getResources().getString(R.string.urlService)
                            +getResources().getString(R.string.urlConsultaCodigo)
                            +codigo
                            +getResources().getString(R.string.urlConsultaGrupo)
                            +grupo);

            edCodigo.setText("");
            edGrupo.setText("");
        }


    }

    @Override
    public void onBackPressed() {
        Intent e = new Intent(this, MainActivity.class);
        startActivity(e);
    }
}
