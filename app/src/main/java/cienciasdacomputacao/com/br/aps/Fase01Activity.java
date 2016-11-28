package cienciasdacomputacao.com.br.aps;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;
import static android.R.drawable.star_big_off;
import static android.R.drawable.star_big_on;


public class Fase01Activity extends AppCompatActivity {


    private int posicaoInicial = 3;
    private int ANIMATION_CIRCLE_DELAY = 2500;
    private int ANIMATION_TRANSLATE_DELAY = 1;
    private int ANIMATION_CIRCLE_DELATE = 50;
    private View tvTranslatehoriz, imgemfundo;
    private Context ctx = this;
    public int moverd = 0;
    public int cont = 1;
    public int pontos = 0;
    public static ObjectAnimator termsConditionsAnim, terms;
    private TextView txtp;
    private View star1, star2, star3, vida1, vida2, vida3, btnb1;
    public int total;
    public int vida = 300;
    public int totalestrelas = 0;
    private String jogador = "";
    private View mostrarbotoes;
    private TextView titulo;
    private TextView showpontos;
    private Button reStart;
    private String son="";
    UpdatetableDAO dao = new UpdatetableDAO(this);
    UpdatetableVO vo = new UpdatetableVO();
    private MediaPlayer vidro, pete, lata, organico, mp, mgp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fase01);


        TextView left = (TextView) findViewById(R.id.imageButtonleft);
        TextView right = (TextView) findViewById(R.id.imageButtonright);
        txtp = (TextView) findViewById(R.id.txtTextoPontos);
        imgemfundo = findViewById(R.id.btnmover_lixo2);
        tvTranslatehoriz = findViewById(R.id.btnmover_lixo);

        mostrarbotoes = findViewById(R.id.framelayoutpop);

        vida1 = findViewById(R.id.btn_vida1);
        vida2 = findViewById(R.id.btn_vida2);
        vida3 = findViewById(R.id.btn_vida3);


        star1 = findViewById(R.id.btn_star1);
        star2 = findViewById(R.id.btn_star2);
        star3 = findViewById(R.id.btn_star3);
        findViewById(R.id.imgsom).setBackground(getResources().getDrawable(R.drawable.ic_music_note_off_white_24dp));
        showvida();

        Random random = new Random();
        cont = random.nextInt(5) + 1;
        setarobjetos();
        son = "desligado";

        mp = MediaPlayer.create(getBaseContext(), R.raw.click);
        vidro = MediaPlayer.create(getBaseContext(), R.raw.vidro);
        pete = MediaPlayer.create(getBaseContext(), R.raw.pete);
        lata = MediaPlayer.create(getBaseContext(), R.raw.lata);
        organico = MediaPlayer.create(getBaseContext(), R.raw.organico);

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
                    mp.start();

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
                    mp.start();

                }
            }

        });


        startCircleAnimation();
        startAnimation();

    }

    private void showvida() {
        vida1.setBackground(getResources().getDrawable(R.drawable.coracao_on));
        vida2.setBackground(getResources().getDrawable(R.drawable.coracao_on));
        vida3.setBackground(getResources().getDrawable(R.drawable.coracao_on));
    }

    //verifica a posição do objeto.
    public boolean Verificar() {
        return ((int) tvTranslatehoriz.getTranslationY() > 600 && (int) tvTranslatehoriz.getTranslationY() < 800);
    }

    // atualiza os pontos do jogador
    public void pontuar() {
        switch (posicaoInicial) {

            case 1:
                if (cont == 1) {
                    pontos += 10;
                    organico.start();

                } else {
                    vidas();
                }
                break;
            case 2:
                if (cont == 2) {
                    pontos += 10;
                    vidro.start();

                } else {
                    vidas();
                }
                break;
            case 3:
                if (cont == 3) {
                    pontos += 10;
                    organico.start();

                } else {
                    vidas();
                }

                break;
            case 4:
                if (cont == 4) {
                    pontos += 10;
                    pete.start();

                } else {
                    vidas();
                }

                break;
            case 5:
                if (cont == 5) {
                    pontos += 10;
                    lata.start();

                } else {
                    vidas();
                }

                break;
            default:
                vidas();

        }
    }

    // acelera o jogo conforme o total de pontos
    public void acelerar() {
        if (pontos > 400 && pontos < 700) {
            ANIMATION_CIRCLE_DELAY = 2300;
        } else if (pontos > 700 && pontos < 1100) {
            ANIMATION_CIRCLE_DELAY = 2000;
            star1.setBackground(getResources().getDrawable(star_big_on));
            totalestrelas = 1;

        } else if (pontos > 1100 && pontos < 1500) {
            ANIMATION_CIRCLE_DELAY = 1700;
            star2.setBackground(getResources().getDrawable(star_big_on));
            totalestrelas = 2;
        } else if (pontos > 2000 && pontos < 2500) {
            ANIMATION_CIRCLE_DELAY = 1200;
            star3.setBackground(getResources().getDrawable(star_big_on));
            totalestrelas = 3;
        } else if (pontos > 3000) {
            termsConditionsAnim.pause();
            terms.pause();
            resumodojogovenceu();
        }

    }

    // encera o jogo quando o jogado atinge o objeivo
    private void resumodojogovenceu() {
        titulo = (TextView) findViewById(R.id.txttitulo);
        showpontos = (TextView) findViewById(R.id.txtpontos);
        titulo.setText("Parabéns Você Ganhou!");
        showpontos.setText(" " +pontos);
        reStart = (Button) findViewById(R.id.btnproximafase);
        reStart.setText("Restart");
        showestrelas();
        showProgress(true);
    }

    // atualiza as vidas do jogador
    public void vidas() {
        vida -= 10;
    }

    // verifica a quantidade de vida do jogador e encerra o jogo se o jogado não tiver vidas
    public void perderjogo() {
        if (vida < 300 && vida > 200) {
            vida1.setBackground(getResources().getDrawable(R.drawable.coracao_offf));

        } else if (vida < 200 && vida > 100) {
            vida2.setBackground(getResources().getDrawable(R.drawable.coracao_offf));

        } else if (vida < 100) {
            vida3.setBackground(getResources().getDrawable(R.drawable.coracao_offf));
            termsConditionsAnim.pause();
            terms.pause();
            resumodojogo();


        }

    }
// encerra o jogo
    private void resumodojogo() {
        titulo = (TextView) findViewById(R.id.txttitulo);
        showpontos = (TextView) findViewById(R.id.txtpontos);
        titulo.setText("GAME OVER");
        showpontos.setText(" " + pontos);
        reStart = (Button) findViewById(R.id.btnproximafase);
        reStart.setText("Restart");
        showestrelas();
        showProgress(true);
    }

    // metodo responsável pela animação do jogo, movimentos dos objetos
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
            if (son.equals("ligado")){
                mgp.start();
            }


        }

        @Override
        public void onAnimationCancel(Animator animation) {


        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };

    private void setarobjetos() {
        if (cont == 3) {
            tvTranslatehoriz.setBackground(getResources().getDrawable(R.drawable.papel));
            imgemfundo.setBackground(getResources().getDrawable(R.drawable.papel));
        } else if (cont == 2) {
            tvTranslatehoriz.setBackground(getResources().getDrawable(R.drawable.vidro));
            imgemfundo.setBackground(getResources().getDrawable(R.drawable.vidro));
        } else if (cont == 1) {
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

    private void AlertarMensagem(Context contest, final Activity act) {

        final Dialog alert = new Dialog(contest);
        alert.setContentView(R.layout.dialoglayout);
        alert.setTitle("GAME OVER");
        alert.findViewById(R.id.btn_salvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                act.finish();
            }
        });
        alert.findViewById(R.id.btnproximafase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                act.finish();

            }
        });
        //btnrestart
        alert.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                resert();
            }
        });
        alert.show();
    }

    // metodo para mostrar a quantidade de estrelas de o jogado ganhou.
    public void showestrelas() {
        switch (totalestrelas) {
            case 1:
                findViewById(R.id.star1).setBackground(getResources().getDrawable(star_big_on));
                break;
            case 2:
                findViewById(R.id.star1).setBackground(getResources().getDrawable(star_big_on));
                findViewById(R.id.star2).setBackground(getResources().getDrawable(star_big_on));
                break;
            case 3:
                findViewById(R.id.star1).setBackground(getResources().getDrawable(star_big_on));
                findViewById(R.id.star2).setBackground(getResources().getDrawable(star_big_on));
                findViewById(R.id.star3).setBackground(getResources().getDrawable(star_big_on));
                break;
        }
    }

    // grava o nome e pontoação do usuário
    private void salvar() {

        Cursor c = dao.buscarString("1");
        int pontosbd = 0;
        while (c.moveToNext()) {
            pontosbd = c.getInt(c.getColumnIndex("ponto"));
        }
        if (pontosbd < pontos) {
            exibirMensagemEdt("Novo Recorde", "Digite Seu Nome!");
        } else {
            finish();
            return;
        }
    }

    public void resert() {
        startActivity(new Intent(getBaseContext(), Fase01Activity.class));
        finish();
        mgp.stop();
    }

    public void verificarposicao(int position, int botton) {
        ObjectAnimator translateAnim;
        if (botton == 1) {
            switch (position) {

                case 1:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX());
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 1;
                    break;
                case 2:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 1;

                    break;
                case 3:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - (moverd * 2));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 1;
                    break;
                case 4:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - (moverd * 3));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 1;
                    break;
                case 5:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - (moverd * 4));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 1;
                    break;
            }
        } else if (botton == 2) {
            switch (position) {

                case 1:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 2;
                    break;
                case 2:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX());
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 2;

                    break;
                case 3:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 2;
                    break;
                case 4:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - (moverd * 2));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 2;
                    break;
                case 5:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - (moverd * 3));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 2;
                    break;
            }

        } else if (botton == 3) {
            switch (position) {
                case 1:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd * 2));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 3;
                    break;
                case 2:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 3;

                    break;
                case 3:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX());
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 3;
                    break;
                case 4:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - (moverd));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 3;
                    break;
                case 5:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - (moverd * 2));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 3;
                    break;
            }

        } else if (botton == 4) {
            switch (position) {
                case 1:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd * 3));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 4;
                    break;
                case 2:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd * 2));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 4;

                    break;
                case 3:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 4;
                    break;
                case 4:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 4;
                    break;
                case 5:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() - (moverd));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 4;
                    break;
            }

        } else if (botton == 5) {
            switch (position) {
                case 1:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd * 4));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 5;
                    break;
                case 2:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd * 3));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 5;

                    break;
                case 3:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd * 2));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 5;
                    break;
                case 4:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX() + (moverd));
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 5;
                    break;
                case 5:
                    translateAnim = ObjectAnimator.ofFloat(tvTranslatehoriz, "translationX", tvTranslatehoriz.getTranslationX(), tvTranslatehoriz.getTranslationX());
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicaoInicial = 5;
                    break;
            }

        }

    }

    public void lixeiraOrganica(View v) {
        verificarposicao(posicaoInicial, 1);
        mp.start();

    }

    public void lixeiraVidro(View v) {
        verificarposicao(posicaoInicial, 2);
        mp.start();

    }

    public void lixeiraPapel(View v) {
        verificarposicao(posicaoInicial, 3);
        mp.start();

    }

    public void lixeiraPlastico(View v) {
        verificarposicao(posicaoInicial, 4);
        mp.start();

    }

    public void lixeiraMetal(View v) {
        verificarposicao(posicaoInicial, 5);
        mp.start();

    }

    public void Restart_Start_Fase(View v) {

        Button btnstart = (Button) findViewById(R.id.btnproximafase);

        if (btnstart.getText().toString().equals("Restart")) {
            termsConditionsAnim.start();
            terms.start();
            vida = 300;
            pontos = 0;
            showvida();
            verificarsompause();
            ANIMATION_CIRCLE_DELAY = 2500;
            showstar();
            showProgress(false);
        } else {
            showProgress(false);
            salvar();
         //   mgp.stop();
        }

    }

    private void showstar() {
        totalestrelas = 0;
        findViewById(R.id.btn_star1).setBackground(getResources().getDrawable(star_big_off));
        findViewById(R.id.btn_star1).setBackground(getResources().getDrawable(star_big_off));
        findViewById(R.id.btn_star1).setBackground(getResources().getDrawable(star_big_off));
    }


    private void showProgress(final boolean show) {

        mostrarbotoes.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void SAIR(View v) {
        showProgress(false);
        salvar();
    }

    public void IMG_SOM(View v) {
        verificarsom();
    }

    public void PAUSE(View v) {
        TextView start = (TextView) findViewById(R.id.btnpause);
        if (start.getText().toString().equals("PAUSE")) {
            verificarsompause();
            start.setText("START");
            termsConditionsAnim.pause();
            terms.pause();

        } else if (start.getText().toString().equals("START")) {
            termsConditionsAnim.start();
            terms.start();
            verificarsompause();
            start.setText("PAUSE");
        }


    }


    private void verificarsom() {

        TextView start = (TextView) findViewById(R.id.btnpause);

        if(son.equals("ligado") && start.getText().toString().equals("PAUSE")) {
            son = "desligado";
            mgp.stop();
            findViewById(R.id.imgsom).setBackground(getResources().getDrawable(R.drawable.ic_music_note_off_white_24dp));
        } else if(son.equals("desligado") && start.getText().toString().equals("PAUSE")) {
            son = "ligado";
            mgp = MediaPlayer.create(getBaseContext(), R.raw.megaman);
            mgp.start();
            findViewById(R.id.imgsom).setBackground(getResources().getDrawable(R.drawable.ic_music_circle_white_24dp));
        } else if(son.equals("desligado") && start.getText().toString().equals("START")) {
            son = "ligado";
            findViewById(R.id.imgsom).setBackground(getResources().getDrawable(R.drawable.ic_music_circle_white_24dp));

        }else if(son.equals("ligado") && start.getText().toString().equals("START")) {
            son = "desligado";
            findViewById(R.id.imgsom).setBackground(getResources().getDrawable(R.drawable.ic_music_note_off_white_24dp));

        }}
    private void verificarsompause() {
        TextView start = (TextView) findViewById(R.id.btnpause);
        if(son.equals("ligado") && start.getText().toString().equals("PAUSE")) {
            mgp.stop();
        } else if(son.equals("ligado") && start.getText().toString().equals("START")) {
            mgp = MediaPlayer.create(getBaseContext(), R.raw.megaman);
            mgp.start();
        }
    }
    public void SAIRDOJOGO(View V) {

        termsConditionsAnim.removeAllListeners();
        termsConditionsAnim.resume();
        terms.removeAllListeners();
        terms.resume();
        showProgress(false);
        verificarsompause();
        salvar();
    }

    public void exibirMensagemEdt(String titulo, String texto) {

        AlertDialog.Builder mensagem = new AlertDialog.Builder(Fase01Activity.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        // DECLARACAO DO EDITTEXT
        final EditText input = new EditText(this);
        mensagem.setView(input);
        mensagem.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {


                String nome = input.getText().toString().trim();
                vo.setJogador(nome);
                vo.setFase(1);
                vo.setEstrela(totalestrelas);
                vo.setPonto(pontos);
                dao.insert(vo);
                Fase01Activity.this.finish();

            }

        });
        mensagem.show();
        // FORÇA O TECLADO APARECER AO ABRIR O ALERT
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

 @Override
    protected void onStop() {
        super.onStop();
     verificarsompause();
     finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
