package com.ionicframework.cursos476803;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ionicframework.cursos476803.Model.Curso;
import com.ionicframework.cursos476803.Model.DetalleCalendario;
import com.ionicframework.cursos476803.util.RequestGetJson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Programador on 18/09/2015.
 */
public class ConsultaCodigoGrupo extends Activity {

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

                JSONArray arreglo= new JSONArray(result);
                Toast.makeText(
                        getApplicationContext(),arreglo.toString(),
                        Toast.LENGTH_LONG).show();
                /**

                JSONObject cursoJson = arreglo.getJSONObject(0);
                JSONArray calendarizacionJSON = cursoJson.getJSONArray("calendarizacion");
                ArrayList<DetalleCalendario> calendarizacion = null;


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


                Toast.makeText(
                        getApplicationContext(), curso.getNombreMateria()+"\n"+ curso.getCalendarizacion().get(0).getAula()+
                        curso.getCalendarizacion().get(0).getProfesores(),
                        Toast.LENGTH_LONG).show();
                    */




            } catch (Exception e) {
                Toast.makeText(
                        getApplicationContext(),
                        "No existe una materia con el codigo y grypo especificado.  \n"
                                + "Por favor verifique la informacion ingresada",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }


    public void clickConsultarCodigoCurso (View view){

        new LeerJSONCursoCodigoGrupo()
                .execute("http://172.21.35.139:1337/calendario?materia="
                        +edCodigo.getText().toString()+"&grupo="
                        +edGrupo.getText().toString());

    }

    @Override
    public void onBackPressed() {
        Intent e = new Intent(this, MainActivity.class);
        startActivity(e);
    }
}
