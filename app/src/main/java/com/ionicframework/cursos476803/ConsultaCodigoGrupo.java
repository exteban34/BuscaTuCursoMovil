package com.ionicframework.cursos476803;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
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

import java.io.IOException;
import java.io.Serializable;
import java.net.ConnectException;
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
                Log.i("result", result);
                /**
                 * implementar una forma acertada para chequear conexion,
                 * (hacer uso de excepciones personalizadas)
                if (result==":v"){
                    Toast.makeText(
                            getApplicationContext(),
                            "Al parecer tienes errores de conexion"+"\n"
                                    +"Verifica tu conexion y vuelve a intentarlo",
                            Toast.LENGTH_LONG).show();

                } */
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



            }catch (JSONException e) {
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
