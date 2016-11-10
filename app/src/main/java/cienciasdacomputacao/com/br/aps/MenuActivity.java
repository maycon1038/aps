package cienciasdacomputacao.com.br.aps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements ImageView.OnClickListener, ImageView.OnLongClickListener{

   private ImageView btn1, btn2, btn3;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

         btn1 = (ImageView)findViewById(R.id.btnfase1);
         btn2 = (ImageView)findViewById(R.id.btnfase2);
         btn3 = (ImageView)findViewById(R.id.btnfase3);

         btn1.setOnLongClickListener(this);
         btn2.setOnLongClickListener(this);
         btn3.setOnLongClickListener(this);
         btn1.setOnClickListener(this);
         btn2.setOnClickListener(this);
         btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "você clicou no botão Long", Toast.LENGTH_SHORT).show();
        return false;
    }
}
