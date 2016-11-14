package cienciasdacomputacao.com.br.aps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.R.drawable.star_big_off;
import static android.R.drawable.star_big_on;
import static cienciasdacomputacao.com.br.aps.R.drawable.circle;


public class MenuActivity extends AppCompatActivity implements ImageView.OnClickListener, ImageView.OnLongClickListener {

    private View btn1, btn2, btn3;
    UpdatetableDAO dao = new UpdatetableDAO(this);
    private Cursor lista;
    private int ANIMATION_CIRCLE_DELAY = 1000;
    private int estrelas, pontos;
    private View titulo, layoutmenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        titulo = findViewById(R.id.txtmenurecord);
        layoutmenu = findViewById(R.id.framelayoutmenu);


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
        if (view == btn1) {
            startActivity(new Intent(this, Fase01Activity.class));
        } else if (view == btn2) {
            Toast.makeText(MenuActivity.this, "Em Construção", Toast.LENGTH_SHORT).show();
        } else if (view == btn3) {
            Toast.makeText(MenuActivity.this, "Em Construção", Toast.LENGTH_SHORT).show();
        }


    }


    @Override

    public boolean onLongClick(View v) {
        if(v==btn1){
            startActivity(new Intent(this, Fase01Activity.class));
        }else if(v==btn2){
            startActivity(new Intent(this, MainActivity.class));
        } else if (v == btn3) {
            Toast.makeText(this, "Em Construção", Toast.LENGTH_SHORT).show();
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


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            layoutmenu.setVisibility(show ? View.VISIBLE : View.GONE);
            layoutmenu.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    layoutmenu.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            layoutmenu.setVisibility(show ? View.VISIBLE : View.GONE);
        }
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
                txtmenu.setText("Recorde " + nome2 + "\n  " + pontos);
                showestrelas(estrelas);
                startCircleAnimation();
            }
        }

    }
}

