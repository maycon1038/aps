package cienciasdacomputacao.com.br.aps;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class IniciarJogo extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate() {
        super.onCreate();

        Log.i("script", "onStartCommandtestes");

        }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        Log.i("script", "onStartCommand");
        Worker w = new Worker(startId);
        w.start();

        return (super.onStartCommand(intent, flags, startId));
    }

    public  class  Worker extends Thread{

        public int count =0;
        public int startId;
        public boolean ativo = true;

        public Worker(int startId){
            this.startId = startId;
        }

        public void run(){


            while (ativo && count < 100){
                try {
                    Thread.sleep(1000*10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                count++;
                Log.i("Script", "COUNT: "+ count);
            }
            stopSelf(startId);
        }
    }



}
