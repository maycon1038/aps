package cienciasdacomputacao.com.br.aps;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.R.drawable.btn_star_big_on;
import static android.R.drawable.star_big_off;
import static android.R.drawable.star_big_on;


public class MainActivity extends AppCompatActivity {


    private MediaPlayer mp;
    public  int posicao = 0;
    public   int ANIMATION_CIRCLE_DELAY = 3000;
    public   int ANIMATION_TRANSLATE_DELAY = 1;
    public   int ANIMATION_CIRCLE_DELATE = 1;
    public ImageView tvTranslate;

    public ImageView[] view;
    public  int moverd = 130;
    public  int cont = 1;
    final Context context = this;
    public  int pontos=0;
    ObjectAnimator termsConditionsAnim,terms;
    private int contclick;
    private TextView txtp;
    private int contador=0;
    private View star1;
    private View star2;
    private View star3;

    public MainActivity() {
        termsConditionsAnim = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton left = (ImageButton) findViewById(R.id.imageButtonleft);
        ImageButton right = (ImageButton) findViewById(R.id.imageButtonright);
        ImageButton pause = (ImageButton) findViewById(R.id.imageButtonpause);
        txtp = (TextView) findViewById(R.id.txtTextoPontos);

        star1 = findViewById(R.id.btn_star1);
        star2 = findViewById(R.id.btn_star2);
        star3 = findViewById(R.id.btn_star3);



// TRANSLATE ANIMATION
        tvTranslate = (ImageView) findViewById(R.id.btnmover);
// tvTranslate.setTag("CLOSED");



        mp = MediaPlayer.create(getBaseContext(), R.raw.click);
        view = new ImageView[6];

        view[1] = (ImageView) findViewById(R.id.btn1);
        view[2] = (ImageView) findViewById(R.id.btn2);
        view[3] = (ImageView) findViewById(R.id.btn3);
        view[4] = (ImageView) findViewById(R.id.btn4);
        view[5] = (ImageView) findViewById(R.id.btn5);




        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvTranslate.getTranslationX() >= 120) {
                    ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslate, "translationX", tvTranslate.getTranslationX(), tvTranslate.getTranslationX() - moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicao_dr();
                }
            }
        });


        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvTranslate.getTranslationX() < 1080) {
                    ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslate, "translationX", tvTranslate.getTranslationX(), tvTranslate.getTranslationX() + moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                    posicao_dr();
                }
            }

        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contclick++;
                // termsConditionsAnim.cancel(); // congela um objeto
                //   termsConditionsAnim.end(); // finaliza o objeto = volta para a posiçao anterior
                if (contclick == 1) {
                    termsConditionsAnim.cancel();
                    termsConditionsAnim.getListeners().clear();
                } else if (contclick == 2) {
                    termsConditionsAnim.start();
                    termsConditionsAnim.addListener(listener);
                    contclick = 0;
                }
            }
        });


        startCircleAnimation();
        startAnimation();
    }

    private void posicao_dr() {


        if((int) tvTranslate.getTranslationX()< 100){
            posicao = 1;
        }else if((int) tvTranslate.getTranslationX()> 200 && (int) tvTranslate.getTranslationX()< 300){
            posicao = 2;
        }else if((int) tvTranslate.getTranslationX()> 300 && (int) tvTranslate.getTranslationX() < 600){
            posicao = 3;
        }else if((int) tvTranslate.getTranslationX() >600 && (int) tvTranslate.getTranslationX() < 900){
            posicao = 4;
        }else if((int) tvTranslate.getTranslationX() > 900 ){
            posicao = 5;
        }
    }


    public void pontuar(){
          if(posicao==1&& (int) view[1].getTranslationY()>= 590 && (int) view[1].getTranslationY() < 690){
              pontos += 10;
              mp.start();
          }else if(posicao==2 && (int) view[2].getTranslationY()> 590 && (int) view[2].getTranslationY() < 690){
              pontos += 10;
              mp.start();
          }else if(posicao==3 && (int) view[3].getTranslationY()> 590 && (int) view[3].getTranslationY() < 690){
              pontos += 10;
              mp.start();
          }else if(posicao==4 && (int) view[4].getTranslationY()> 590 && (int) view[4].getTranslationY() < 690){
              pontos += 10;
              mp.start();
          }else if(posicao==5 && (int) view[5].getTranslationY()> 590 && (int) view[5].getTranslationY() < 690){
              pontos += 10;
              mp.start();
          }

      }

    public void acelerar(){
        if(pontos>500 && pontos<900){
            ANIMATION_CIRCLE_DELAY = 2500;

        }else if(pontos>900 && pontos<1500){
            ANIMATION_CIRCLE_DELAY = 2000;

        }else if(pontos>1500&& pontos<2000){
            ANIMATION_CIRCLE_DELAY = 1500;

        }else if(pontos>2000){
            ANIMATION_CIRCLE_DELAY = 1000;

        }

    }

    public void perderjogo() {

        if(posicao == 1 && ((int) view[2].getTranslationY()==690) ||
                (posicao == 1 && (int) view[3].getTranslationY()==690 )||
                (posicao == 1 && (int) view[4].getTranslationY()==690 ) ||
                (posicao == 1 && (int) view[5].getTranslationY() == 690 )){
            perderstar();
        }else if(posicao == 2 && ((int) view[1].getTranslationY()==690 ) ||
                (posicao == 2 && (int) view[3].getTranslationY() == 690 )||
                (posicao == 2 && (int) view[4].getTranslationY() == 690 ) ||
                (posicao == 2 && (int) view[5].getTranslationY() == 690 )){
            perderstar();
        }else if(posicao == 3 && ((int) view[1].getTranslationY()== 690 ) ||
                (posicao == 3 && (int) view[2].getTranslationY() == 690 )||
                (posicao == 3 && (int) view[4].getTranslationY() == 690 ) ||
                (posicao == 3 && (int) view[5].getTranslationY() == 690 )){
            perderstar();
        }else if(posicao == 4 && ((int) view[1].getTranslationY()== 690 ) ||
                (posicao == 4 && (int) view[3].getTranslationY() == 690 )||
                (posicao == 4 && (int) view[2].getTranslationY() == 690 ) ||
                (posicao == 4 && (int) view[5].getTranslationY() == 690 )){
            perderstar();
        }else if(posicao == 5 && ((int) view[1].getTranslationY()== 690 ) ||
                (posicao == 5 && (int) view[3].getTranslationY() == 690 )||
                (posicao == 5 && (int) view[4].getTranslationY() == 690 ) ||
                (posicao == 5 && (int) view[2].getTranslationY() == 690 )){
            perderstar();
        }
    }

    private void perderstar() {
        contador +=1;
        if(contador==1 ){
            star1.setBackground(getResources().getDrawable(star_big_off));
            termsConditionsAnim.end();
        }else if(contador==2){
            star2.setBackground(getResources().getDrawable(star_big_off));
            termsConditionsAnim.end();
        }else if(contador==3){
            star3.setBackground(getResources().getDrawable(star_big_off));
            Toast.makeText(context, " você perdeu ", Toast.LENGTH_SHORT).show();
            termsConditionsAnim.cancel();
            termsConditionsAnim.getListeners().clear();
            terms.cancel();
            terms.getListeners().clear();
        }

    }

    private void startCircleAnimation() {
        //    ObjectAnimator termsConditionsAnim = ObjectAnimator.ofFloat(circle, "alpha", 1, 0);
        termsConditionsAnim = ObjectAnimator.ofFloat(view[cont], "translationY", 0, 800);
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
            Random random = new Random();
            cont = random.nextInt(5) + 1;
            startCircleAnimation();
            perderjogo();
        }

        @Override
        public void onAnimationCancel(Animator animation) {



        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            }
    };

    private void startAnimation() {
        terms = ObjectAnimator.ofFloat(txtp, "translationY", 0, 0);
        terms.setDuration(ANIMATION_CIRCLE_DELATE);
        terms.addListener(listener2);
        terms.start();


    }

    private Animator.AnimatorListener listener2 = new Animator.AnimatorListener() {


        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            startAnimation();
            posicao_dr();
            pontuar();
            perderjogo();
            acelerar();
            txtp.setText(String.valueOf(pontos));

        }

        @Override
        public void onAnimationCancel(Animator animation) {



        }

        @Override
        public void onAnimationRepeat(Animator animation) {


        }
    };


    @Override
    protected void onResume() {
        super.onResume();


    }
}
 /*   Intent intent = new Intent(this, IniciarJogo.class);
            PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(System.currentTimeMillis());
            time.add(Calendar.SECOND, 2);
            AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
           // alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), 1000, pendingIntent);*/
//   Intent it = new Intent( "pmam_iniciarservio");
//  startService(intent);

// CIRCLE ANIMATION
//  circle = (ImageView) findViewById(R.id.iv_example_circle);

////////////////////


// STRIPPED ANIMATION
//    AnimationDrawable strippedAnim = (AnimationDrawable) findViewById(R.id.iv_example_stripped).getBackground();
//   strippedAnim.setOneShot(false);
//    strippedAnim.start();



//  startanimacao();

/*   int padding = (int) (getResources().getDisplayMetrics().density);
                ValueAnimator animator = ValueAnimator.ofInt(padding, 0);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int a = (Integer) valueAnimator.getAnimatedValue();
                        //    view[cont].setPadding(a, a, a, a);

                        Toast.makeText(context, " v "+ a, Toast.LENGTH_SHORT).show();
                        Log.i("testedins"," v "+ a);
                    }
                });
                animator.setDuration(ANIMATION_TRANSLATE_DELAY);
                animator.start();
            }*/

/*

        view[cont].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator termsConditionsAnim = ObjectAnimator.ofFloat(view[cont], "alpha", 1, 0);
                termsConditionsAnim.setDuration(ANIMATION_CIRCLE_DELATE);
                termsConditionsAnim.start();
                mp.start();

                Toast.makeText(context, "dsfas", Toast.LENGTH_SHORT).show();

            }
        });
*/
