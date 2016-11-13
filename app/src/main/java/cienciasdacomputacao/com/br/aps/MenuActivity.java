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

   private View btn1, btn2, btn3;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

         btn1 = findViewById(R.id.btnfase1);
         btn2 = findViewById(R.id.btnfase2);
         btn3 = findViewById(R.id.btnfase3);

         btn1.setOnLongClickListener(this);
         btn2.setOnLongClickListener(this);
         btn3.setOnLongClickListener(this);
         btn1.setOnClickListener(this);
         btn2.setOnClickListener(this);
         btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
          if(view==btn1){
              startActivity(new Intent(this, MainActivity.class));
          }else if(view==btn2){
              startActivity(new Intent(this, Main2Activity.class));
          }else if(view==btn3){
              Toast.makeText(this, "Em Construção", Toast.LENGTH_SHORT).show();
          }


    }


    @Override
    public boolean onLongClick(View v) {
        if(v==btn1){
            startActivity(new Intent(this, MainActivity.class));
        }else if(v==btn2){
            startActivity(new Intent(this, Main2Activity.class));
        }else if(v==btn3){
            Toast.makeText(this, "Em Construção", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
