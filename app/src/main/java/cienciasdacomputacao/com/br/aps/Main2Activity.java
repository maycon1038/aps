package cienciasdacomputacao.com.br.aps;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.R.drawable.star_big_off;
import static android.R.drawable.star_big_on;


public class Main2Activity extends AppCompatActivity {


    private MediaPlayer mp;
    private static int posicaoInicial = 3;
    public int ANIMATION_CIRCLE_DELAY = 3500;
    public int ANIMATION_TRANSLATE_DELAY = 1;
    public int ANIMATION_CIRCLE_DELATE = 50;
    private View tvTranslatehoriz, imgemfundo;
    private Context ctx = this;
    public int moverd = 0;
    public int cont = 1;
    public int pontos = 0;
    public ObjectAnimator termsConditionsAnim, terms;
    private TextView txtp;
    private View star1, star2, star3,vida1,vida2,vida3,btnb1;
    private int total;
    public String[] obj;
    private  int vida = 300;
    private int totalestrelas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ImageButton left = (ImageButton) findViewById(R.id.imageButtonleft);
        ImageButton right = (ImageButton) findViewById(R.id.imageButtonright);
      /*  ImageButton pause = (ImageButton) findViewById(R.id.imageButtonpause);*/
        txtp = (TextView) findViewById(R.id.txtTextoPontos);
         imgemfundo = findViewById(R.id.btnmover_lixo2);
        tvTranslatehoriz = findViewById(R.id.btnmover_lixo);

        vida1 = findViewById(R.id.btn_vida1);
        vida2 = findViewById(R.id.btn_vida2);
        vida3 = findViewById(R.id.btn_vida3);

        star1 = findViewById(R.id.btn_star1);
        star2 = findViewById(R.id.btn_star2);
        star3 = findViewById(R.id.btn_star3);

        obj = new String[6];
        obj[1] = "papel";
        obj[2] = "vidro";
        obj[3] = "organico";
        obj[4] = "plastico";
        obj[5] = "metal";


        Random random = new Random();
        cont = random.nextInt(5) + 1;
        setarobjetos();


        mp = MediaPlayer.create(getBaseContext(), R.raw.click);

        btnb1 = findViewById(R.id.btnb1);

        total = btnb1.getResources().getDisplayMetrics().densityDpi;
        moverd = total / 2;

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTranslatehoriz.getTranslationX() > -((total / 2) * 2) && (!Verificar())) {
                    ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial--;

                }
            }
        });


        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTranslatehoriz.getTranslationX() < ((total / 2) * 2) && (!Verificar())) {
                    ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial++;

                }
            }

        });


        startCircleAnimation();
        startAnimation();

    }

    public boolean Verificar() {

        return ((int) tvTranslatehoriz.getTranslationY() > 600 && (int) tvTranslatehoriz.getTranslationY() < 750);
    }


    public void pontuar() {
        switch (posicaoInicial) {

            case 1:
                if (cont == 1) {
                    pontos += 10;
                    mp.start();

                }else {
                    vidas();
                }
                break;
            case 2:
                if (cont == 2) {
                    pontos += 10;
                    mp.start();

                }else {
                    vidas();
                }
                break;
            case 3:
                if (cont == 3) {
                    pontos += 10;
                    mp.start();

                }else {
                    vidas();
                }

                break;
            case 4:
                if (cont == 4) {
                    pontos += 10;
                    mp.start();

                }else {
                    vidas();
                }

                break;
            case 5:
                if (cont == 5) {
                    pontos += 10;
                    mp.start();

                }else {
                    vidas();
                }

                break;
            default:
                vidas();

        }
    }

    public void acelerar() {
        if (pontos > 500 && pontos < 900) {
            ANIMATION_CIRCLE_DELAY = 3000;


        } else if (pontos > 900 && pontos < 1500) {
            ANIMATION_CIRCLE_DELAY = 2500;
            star1.setBackground(getResources().getDrawable(star_big_on));
            totalestrelas=1;

        } else if (pontos > 1500 && pontos < 2000) {
            ANIMATION_CIRCLE_DELAY = 2200;
            star2.setBackground(getResources().getDrawable(star_big_on));
            totalestrelas=2;
        } else if (pontos > 2000) {
            ANIMATION_CIRCLE_DELAY = 2000;
            star3.setBackground(getResources().getDrawable(star_big_on));
            totalestrelas=3;
        }

    }

    public void vidas() {
        vida -= 10;

    }

    public void perderjogo() {
        if (vida < 300 && vida > 200) {
            vida1.setBackground(getResources().getDrawable(star_big_off));

        } else if (vida < 200 && vida > 100) {
            vida2.setBackground(getResources().getDrawable(star_big_off));

        } else if (vida < 100) {
            vida3.setBackground(getResources().getDrawable(star_big_off));
            Toast.makeText(Main2Activity.this, " GAME OVER ", Toast.LENGTH_SHORT).show();
            termsConditionsAnim.removeAllListeners();
            termsConditionsAnim.resume();
            terms.removeAllListeners();
            terms.resume();
            verfot(ctx,Main2Activity.this);
        }

    }


    private void startCircleAnimation() {
        //    ObjectAnimator termsConditionsAnim = ObjectAnimator.ofFloat(circle, "alpha", 1, 0);
        termsConditionsAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationY", 0, 800);
        termsConditionsAnim.setDuration(ANIMATION_CIRCLE_DELAY);
        termsConditionsAnim.addListener(listener);
        termsConditionsAnim.start();

        Random random = new Random();
        cont = random.nextInt(5) + 1;
        setarobjetos();
        tvTranslatehoriz.setTranslationX(0);
        posicaoInicial = 3;


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

    private void setarobjetos() {
        if (cont == 1) {
            tvTranslatehoriz.setBackground(getResources().getDrawable(R.drawable.papel));
            imgemfundo.setBackground(getResources().getDrawable(R.drawable.papel));
        } else if (cont == 2) {
            tvTranslatehoriz.setBackground(getResources().getDrawable(R.drawable.vidro));
            imgemfundo.setBackground(getResources().getDrawable(R.drawable.vidro));
        } else if (cont == 3) {
            tvTranslatehoriz.setBackground(getResources().getDrawable(R.drawable.organico));
            imgemfundo.setBackground(getResources().getDrawable(R.drawable.organico));
        } else if (cont == 4) {
            tvTranslatehoriz.setBackground(getResources().getDrawable(R.drawable.plastico));
            imgemfundo.setBackground(getResources().getDrawable(R.drawable.plastico));
        } else if (cont == 5) {
            tvTranslatehoriz.setBackground(getResources().getDrawable(R.drawable.metal));
            imgemfundo.setBackground(getResources().getDrawable(R.drawable.metal));
        }
    }

    private void startAnimation() {
        terms = ObjectAnimator.ofFloat(txtp, "translationY", 0, 0);
        terms.setDuration(ANIMATION_CIRCLE_DELATE);
        terms.addListener(listener2);
        terms.start();


        if (Verificar()) {
            pontuar();
        }


    }

    private Animator.AnimatorListener listener2 = new Animator.AnimatorListener() {


        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            startAnimation();
            acelerar();
            perderjogo();
            txtp.setText(String.valueOf(pontos));


        }

        @Override
        public void onAnimationCancel(Animator animation) {


        }

        @Override
        public void onAnimationRepeat(Animator animation) {


        }
    };


    private void verfot(Context contest, final Activity act) {

        final Dialog alert = new Dialog(contest);
        alert.setContentView(R.layout.dialagofoto);
        showestrelas(alert);
        alert.findViewById(R.id.bbtnsair).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                act.finish();

            }
        });

        alert.findViewById(R.id.btnproximafase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //  execuatarbusca();
                alert.dismiss();
            }
        });
          //btnrestart
        alert.findViewById(R.id.btnrestart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                act.finish();
                startActivity(new Intent(getBaseContext(), Main2Activity.class));
            }
        });
        alert.show();
    }

    public void showestrelas(Dialog alert) {
        switch (totalestrelas) {
            case 1:
                alert.findViewById(R.id.star1).setBackground(getResources().getDrawable(star_big_on));

                break;
            case 2:
                alert.findViewById(R.id.star1).setBackground(getResources().getDrawable(star_big_on));
                alert.findViewById(R.id.star2).setBackground(getResources().getDrawable(star_big_on));

                break;
            case 3:
                alert.findViewById(R.id.star1).setBackground(getResources().getDrawable(star_big_on));
                alert.findViewById(R.id.star2).setBackground(getResources().getDrawable(star_big_on));
                alert.findViewById(R.id.star3).setBackground(getResources().getDrawable(star_big_on));
                TextView tx3 = (TextView) findViewById(R.id.txtmsg);
                tx3.setText("PARABÉNS VOCÊ VBNCEU!");
                TextView txp3 = (TextView) findViewById(R.id.txtpontos);
                txp3.setText("TOTAL: " + pontos);
                break;

        }
    }
}
