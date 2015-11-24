package com.ionicframework.cursos476803;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Clase Encargada de realizar la consulta de diferentes cursos con el nombre de la materia,
 * provee un campo para digitar dicho nombre.
 * 
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @author Yoiner Esteban Gomez <yoiner.gomez22@gmail.com>
 * @version 1.0 30/09/2015
 *
 */
public class ConsultaNombreMateria extends AppCompatActivity {

    ProgressDialog pDialog;
    EditText edNombreMateria;
    LeerJSONNombreMateria task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_nombre);
        edNombreMateria = (EditText) findViewById(R.id.edtTxt_nombreCurso);
    }

    /**
     * Clase Asincrona encargada de consultar el arreglo JSON  con los cursos y lanzar la actividad que provee
     * la visualizacion del listado.
     *
     * @author Heinner Esteban Alvarez <exteban34@gmail.com>
     * @author Yoiner Esteban Gomez <yoiner.gomez22@gmail.com>
     * @version 1.0 30/09/2015
     */
    private class LeerJSONNombreMateria extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            pDialog = new ProgressDialog(ConsultaNombreMateria.this);
            pDialog.setMessage(getString(R.string.carga_datos));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    task.cancel(true);
                    cancel(true);
                }
            });
        }


        protected String doInBackground(String... urls) {
            return RequestGetJson.getJson(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                pDialog.dismiss();
                Log.i("result to Http GET", result);
                JSONArray arreglo= new JSONArray(result);
                ArrayList<Curso> cursos = new ArrayList<>();

                for (int l=0; l<arreglo.length();l++) {
                    JSONObject cursoJson = arreglo.getJSONObject(l);
                    JSONArray calendarizacionJSON = cursoJson.getJSONArray("calendarizacion");
                    ArrayList<DetalleCalendario> calendarizacion = new ArrayList<DetalleCalendario>();

                    for (int i = 0; i < calendarizacionJSON.length(); i++) {
                        JSONObject detalleCursoJSON = calendarizacionJSON.getJSONObject(i);
                        String aula= (String) detalleCursoJSON.get("aula");
                        String profesores= (String) detalleCursoJSON.get("profesor")+"\n";
                        String horarios= (String) detalleCursoJSON.get("horario")+"\n";

                        DetalleCalendario detalleCalendario = new DetalleCalendario(aula, horarios, profesores);
                        calendarizacion.add(detalleCalendario);
                    }

                    String codigoMateria = cursoJson.getString("materia");
                    String grupoCurso = cursoJson.getString("grupo");
                    String nombreMateria = cursoJson.getString("nombreMateria");
                    Curso curso = new Curso(codigoMateria, grupoCurso, nombreMateria, calendarizacion);
                    cursos.add(curso);
                }

                Intent e= new Intent("com.ionicframework.cursos476803.ListaMaterias");
                e.putExtra("cursos", new DataPass(cursos));
                e.putExtra("nombreMateria", edNombreMateria.getText().toString().toUpperCase());
                startActivity(e);



            } catch (JSONException e) {
                if (e.getMessage().contains("Index")) {
                    Toast.makeText(
                            getApplicationContext(),
                            getResources().getString(R.string.error_consulta_Codigo_Grupo),
                            Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(
                            getApplicationContext(),
                            getResources().getString(R.string.error_conexion_internet),
                            Toast.LENGTH_LONG).show();
                }
            }

        }
    }



    @Override
    public void onBackPressed() {
        Intent e = new Intent(this, MainActivity.class);
        startActivity(e);
    }

    public void clickConsultarPorNombre(View view){
        String nombreMateria = edNombreMateria.getText().toString();
        if(nombreMateria.isEmpty()){
            Toast.makeText(
                    getApplication(), getResources().getString(R.string.error_ingresar_nombre_materia),
                    Toast.LENGTH_LONG).show();
        } else {
            nombreMateria = nombreMateria.replace(" ", "%20");
            task = new LeerJSONNombreMateria();
                    task.execute(getResources().getString(R.string.urlService)
                            +getResources().getString(R.string.urlConsultaNombre)
                            +"&NOMBRE="
                            +nombreMateria);
        }
    }
}
