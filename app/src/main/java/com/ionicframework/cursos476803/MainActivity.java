package com.ionicframework.cursos476803;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * MainActivity, provee dos botones con las opciones para consultar un curso por su codigo y grupo, o
 * por el nombre de la materia.
 *
 * @author Heinner Esteban Alvarez <exteban34@gmail.com>
 * @author Yoiner Esteban Gomez <yoiner.gomez22@gmail.com>
 * @version 1.0 30/09/2015
 *
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void clickCodigo(View view){
        Intent e = new Intent("com.ionicframework.cursos476803.ConsultaCodigoGrupo");
        startActivity(e);
    }

    public void clickNombre(View view){
        Intent e = new Intent("com.ionicframework.cursos476803.ConsultaNombreMateria");
        startActivity(e);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
