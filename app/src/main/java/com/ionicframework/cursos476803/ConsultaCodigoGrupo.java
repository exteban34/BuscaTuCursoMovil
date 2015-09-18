package com.ionicframework.cursos476803;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Programador on 18/09/2015.
 */
public class ConsultaCodigoGrupo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_codigo_grupo);
    }

    @Override
    public void onBackPressed() {
        Intent e = new Intent(this, MainActivity.class);
        startActivity(e);
    }
}
