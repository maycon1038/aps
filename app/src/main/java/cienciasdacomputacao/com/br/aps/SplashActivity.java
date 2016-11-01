package cienciasdacomputacao.com.br.aps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/********* --- PMAM ---***********
 ********* --- DTI ---***********
 *****----- CAP. RUSO-----********
 ***** SD - MAYCON MEDEIROS*******
 ***** TÉC - GEORGE TAVARES*******
 *****--MANAUS - AM -19/09/2016**/

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent i = new Intent( SplashActivity.this, MenuActivity.class);
                // Fecha esta activity
                finish();
                System.gc();
                startActivity(i);

            }
        }, SPLASH_TIME_OUT);
    }
}
