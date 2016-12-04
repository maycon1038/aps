package cienciasdacomputacao.com.br.aps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.drawable.star_big_off;
import static android.R.drawable.star_big_on;


public class MenuActivity extends AppCompatActivity implements ImageView.OnClickListener, ImageView.OnLongClickListener {

    private View btn1, btn2, btn3;
    UpdatetableDAO dao = new UpdatetableDAO(this);
    private Cursor lista;
    private int ANIMATION_CIRCLE_DELAY = 1000;
    private int estrelas, pontos;
    private View titulo, layoutmenu;
    private int estrelas2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        titulo = findViewById(R.id.txtmenurecord);
        layoutmenu = findViewById(R.id.framelayoutmenu);


        btn1 = findViewById(R.id.btnfase1);
        btn2 = findViewById(R.id.btnfase2);
        btn3 = findViewById(R.id.btnfase3);

      /*  btn1.setOnLongClickListener(this);
        btn2.setOnLongClickListener(this);
        btn3.setOnLongClickListener(this);*/
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }


    private void startCircleAnimation() {
        ObjectAnimator termsConditionsAnim = ObjectAnimator.ofFloat(titulo, "alpha", 1, 0);
        //  termsConditionsAnim = ObjectAnimator.ofFloat(view[cont], "translationY", 0, 800);
        termsConditionsAnim.setDuration(ANIMATION_CIRCLE_DELAY);
        termsConditionsAnim.addListener(listener);
        termsConditionsAnim.start();


    }

    private Animator.AnimatorListener listener = new Animator.AnimatorListener() {


        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            startCircleAnimation();
        }

        @Override
        public void onAnimationCancel(Animator animation) {


        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };

    @Override
    public void onClick(View view) {

        if(view==btn1) {
            startActivity(new Intent(this, Fase01Activity.class));
        }
        if(view==btn2) {
            if ( dao.buscarTudo() != null) {
                Cursor c = dao.buscarString("1");
                while (c.moveToNext()) {
                    estrelas = c.getInt(c.getColumnIndex("estrela"));
                }
                if(estrelas>=1) {
                    Toast.makeText(this, "Fase em Construção", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Necessário uma estrelas na fase 1", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Necessário uma estrelas na fase 1", Toast.LENGTH_SHORT).show();
            }
        }
        if(view==btn3){
                        if ( dao.buscarTudo() != null) {
                           Cursor c = dao.buscarString("2");
                            while (c.moveToNext()) {
                                estrelas2 = c.getInt(c.getColumnIndex("estrela"));
                            }
                              if(estrelas2>=1) {
                                  Toast.makeText(this, "Fase em Construção", Toast.LENGTH_SHORT).show();
                              }else {
                                  Toast.makeText(this, "Necessário uma estrelas na fase 2", Toast.LENGTH_SHORT).show();
                              }
                        }else {
                            Toast.makeText(this, "Necessário uma estrelas na fase 2", Toast.LENGTH_SHORT).show();
                        }
         }

    }


    @Override

    public boolean onLongClick(View v) {
        if(v==btn1) {
            startActivity(new Intent(this, Fase01Activity.class));
        }else
        if(v==btn2) {
            if ( dao.buscarString("1") != null) {
                Cursor c = dao.buscarString("1");
                while (c.moveToNext()) {
                    estrelas2 = c.getInt(c.getColumnIndex("estrela"));
                }
                if(estrelas2>=1) {
                    Toast.makeText(this, "Fase em Construção", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Necessário uma estrelas na fase 1", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Necessário uma estrelas na fase 1", Toast.LENGTH_SHORT).show();
            }
        }else
        if(v==btn3){
            if ( dao.buscarString("2") != null) {
                Cursor c = dao.buscarString("2");
                while (c.moveToNext()) {
                    estrelas2 = c.getInt(c.getColumnIndex("estrela"));
                }
                if(estrelas2>=1) {
                    Toast.makeText(this, "Fase em Construção", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Necessário uma estrelas na fase 2", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Necessário uma estrelas na fase 1", Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }

    private void showestrelas(int star) {
        switch (star) {
            case 1:
                findViewById(R.id.fase1star1).setBackground(getResources().getDrawable(star_big_on));
                break;
            case 2:
                findViewById(R.id.fase1star1).setBackground(getResources().getDrawable(star_big_on));
                findViewById(R.id.fase1star2).setBackground(getResources().getDrawable(star_big_on));
                break;
            case 3:
                findViewById(R.id.fase1star1).setBackground(getResources().getDrawable(star_big_on));
                findViewById(R.id.fase1star2).setBackground(getResources().getDrawable(star_big_on));
                findViewById(R.id.fase1star3).setBackground(getResources().getDrawable(star_big_on));
                break;
            default:
                findViewById(R.id.fase1star1).setBackground(getResources().getDrawable(star_big_off));
                findViewById(R.id.fase1star2).setBackground(getResources().getDrawable(star_big_off));
                findViewById(R.id.fase1star3).setBackground(getResources().getDrawable(star_big_off));
        }
    }

    public void LIMPARMENU(View v) {
        showProgress(false);
        findViewById(R.id.fase1star1).setBackground(getResources().getDrawable(star_big_off));
        findViewById(R.id.fase1star2).setBackground(getResources().getDrawable(star_big_off));
        findViewById(R.id.fase1star3).setBackground(getResources().getDrawable(star_big_off));
        dao.deletar();
    }

    public void SAIRMENU(View v) {
       finish();
    }

    private void showProgress(final boolean show) {

            layoutmenu.setVisibility(show ? View.VISIBLE : View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dao.buscarTudo() != null) {
            Cursor c = dao.buscarString("1");

            showProgress(true);
            TextView txtmenu = (TextView) findViewById(R.id.txtmenurecord);

            while (c.moveToNext()) {
                String nome2 = c.getString(c.getColumnIndex("jogador"));
                estrelas = c.getInt(c.getColumnIndex("estrela"));
                pontos = c.getInt(c.getColumnIndex("ponto"));
                txtmenu.setText(" " + nome2 + "\n  " + pontos);
                showestrelas(estrelas);
                startCircleAnimation();
            }
        }

    }
}

