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
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

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
    public ObjectAnimator termsConditionsAnim, terms;
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
    UpdatetableDAO dao = new UpdatetableDAO(this);
    UpdatetableVO vo = new UpdatetableVO();
    private MediaPlayer vidro, pete,lata,organico,mp,mgp;

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

        Random random = new Random();
        cont = random.nextInt(5) + 1;
        setarobjetos();


        mp = MediaPlayer.create(getBaseContext(), R.raw.click);
        mgp = MediaPlayer.create(getBaseContext(), R.raw.megaman);
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
        mgp.start();

    }

    public boolean Verificar() {
       /* View  vl, btn;
         vl = findViewById(R.id.imageButtonleft);
         btn = findViewById(R.id.btnb1);

         int Tvl = (int) vl.getResources().getDisplayMetrics().ydpi;
        int Tbtn = (int) btn.getResources().getDisplayMetrics().ydpi / 2;
        int total = Tbtn + Tvl;
*/

        return ((int) tvTranslatehoriz.getTranslationY() > 600 && (int) tvTranslatehoriz.getTranslationY() < 750);
    }

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
        } else if (pontos > 1500 && pontos < 2000) {
            ANIMATION_CIRCLE_DELAY = 1200;
            star3.setBackground(getResources().getDrawable(star_big_on));
            totalestrelas = 3;
        } else if (pontos > 2000) {
            termsConditionsAnim.removeAllListeners();
            termsConditionsAnim.resume();
            terms.removeAllListeners();
            terms.resume();
            resumodojogovenceu();
        }

    }

    private void resumodojogovenceu() {

        titulo = (TextView)findViewById(R.id.txttitulo);
        showpontos = (TextView)findViewById(R.id.txtpontos);
        titulo.setText("PARABÉNS VOCÊ VENCEU");
        showpontos.setText(pontos);
        reStart =  (Button)findViewById(R.id.btnproximafase);
        reStart.setText("Próxima Fase");
        showestrelas();
        showProgress(true);
    }

    public void vidas() {
        vida -= 10;

    }

    public void perderjogo() {
        if (vida < 300 && vida > 200) {
            vida1.setBackground(getResources().getDrawable(R.drawable.coracao_offf));

        } else if (vida < 200 && vida > 100) {
            vida2.setBackground(getResources().getDrawable(R.drawable.coracao_offf));

        } else if (vida < 100) {
            vida3.setBackground(getResources().getDrawable(R.drawable.coracao_offf));
            termsConditionsAnim.removeAllListeners();
            termsConditionsAnim.resume();
            terms.removeAllListeners();
            terms.resume();
            mgp.stop();
            resumodojogo();


        }

    }

    private void resumodojogo() {

        titulo = (TextView)findViewById(R.id.txttitulo);
        showpontos = (TextView)findViewById(R.id.txtpontos);
        titulo.setText("GAME OVER");
        showpontos.setText(" "+ pontos);
        reStart =  (Button)findViewById(R.id.btnproximafase);
        reStart.setText("Restart");
        showestrelas();
        showProgress(true);
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
              mgp.start();

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
                startActivity(new Intent(getBaseContext(), Main3Activity.class));
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

    private void salvar() {

            Cursor c =  dao.buscarString("1");
            int pontosbd=0;
            while (c.moveToNext()) {
                pontosbd = c.getInt(c.getColumnIndex("ponto"));
            }
        if(pontosbd < pontos) {
                exibirMensagemEdt("Novo Recorde", "Digite Seu Nome!");

        }else{
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

    public void Restart_Start_Fase(View v){

        Button start =  (Button)findViewById(R.id.btnproximafase);
        if(start.getText().toString().equals("Restart")){
            showProgress(false);
             mgp.stop();
            startActivity(new Intent(getBaseContext(), Fase01Activity.class));
            finish();

        }else{
            showProgress(false);
            Toast.makeText(Fase01Activity.this, "Baixe a Version Pro 2.0", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


    private void showProgress(final boolean show) {

        mostrarbotoes.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    public void SAIR(View v){
        showProgress(false);
        salvar();
        mgp.stop();
    }
    public void PAUSE(View v){
        TextView start =  (TextView)findViewById(R.id.btnpause);
           if(start.getText().toString().equals("PAUSE")){
               start.setText("START");
               termsConditionsAnim.pause();
               terms.pause();
               mgp.stop();
           }else if(start.getText().toString().equals("START")){
               termsConditionsAnim.start();
               terms.start();
               start.setText("PAUSE");
               mgp = MediaPlayer.create(getBaseContext(), R.raw.megaman);
               mgp.start();
           }


    }

    public void SAIRDOJOGO(View V){

        termsConditionsAnim.removeAllListeners();
        termsConditionsAnim.resume();
        terms.removeAllListeners();
        terms.resume();
        showProgress(false);
        mgp.stop();
        salvar();
    }

    public void exibirMensagemEdt(String titulo, String texto){

        AlertDialog.Builder mensagem = new AlertDialog.Builder(Fase01Activity.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        // DECLARACAO DO EDITTEXT
        final EditText input = new EditText(this);
        mensagem.setView(input);
        mensagem.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {


               String  nome =  input.getText().toString().trim();
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

}
