package cienciasdacomputacao.com.br.aps;

   import android.animation.Animator;
   import android.animation.ObjectAnimator;
   import android.app.AlarmManager;
   import android.app.PendingIntent;
   import android.content.Context;
   import android.content.Intent;
   import android.graphics.drawable.AnimationDrawable;
   import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
   import android.widget.ImageView;
   import android.widget.Toast;

   import java.util.Calendar;
   import java.util.Random;

   import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {


    private MediaPlayer mp;
    public static int posicao = 1;
    private final int ANIMATION_CIRCLE_DELAY = 3000;
    private final int ANIMATION_TRANSLATE_DELAY = 10;
    private ImageView tvTranslate;

    private ImageView[] view;
    private static int moverd = 120;
     private static int cont=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


            // TRANSLATE ANIMATION
             tvTranslate = (ImageView) findViewById(R.id.btnmover);
            // tvTranslate.setTag("CLOSED");

          //  startanimacao();



        mp = MediaPlayer.create(getBaseContext(), R.raw.click);
            view = new ImageView[6];

            view[1] = (ImageView)findViewById(R.id.btn1);
            view[2] = (ImageView)findViewById(R.id.btn2);
            view[3] = (ImageView)findViewById(R.id.btn3);
            view[4] = (ImageView)findViewById(R.id.btn4);
            view[5] = (ImageView)findViewById(R.id.btn5);



        ImageButton left = (ImageButton) findViewById(R.id.imageButtonleft);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslate, "translationX", tvTranslate.getTranslationX(),  tvTranslate.getTranslationX() - moverd);
                    translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                    translateAnim.start();
                mp.start();
                }

        });

        ImageButton right = (ImageButton) findViewById(R.id.imageButtonright);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslate, "translationX",  tvTranslate.getTranslationX(), tvTranslate.getTranslationX() + moverd);
                translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
                translateAnim.start();
                mp.start();
            }

        });

        startCircleAnimation();

    }

    private void startCircleAnimation() {
        //    ObjectAnimator termsConditionsAnim = ObjectAnimator.ofFloat(circle, "alpha", 1, 0);
        ObjectAnimator termsConditionsAnim = ObjectAnimator.ofFloat(view[cont], "translationY", 0,800);
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
             cont = random.nextInt(5)+1;
            startCircleAnimation();


        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            // startCircleAnimation();
        }
    };


    public void startanimacao(){
        ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslate, "translationY", 0,/* tvTranslate.getHeight()*/ 300);
        translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
        translateAnim.start();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
    }
