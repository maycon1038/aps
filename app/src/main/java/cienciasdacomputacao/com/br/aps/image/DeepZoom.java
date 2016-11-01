package cienciasdacomputacao.com.br.aps.image;/*
package pm.am.gov.image;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import pm.am.gov.br.comandomobile.Militar01Activity;
import uk.co.senab.photoview.PhotoView;

*/
/**
 * Created by MSM on 15/10/2016.
 *//*


public class DeepZoom extends Activity {
    private String token;
    private String id_militar;
    private  String URL_I = "https://pm.am.gov.br/comandopmam/index.php/dpa/PessoasFotografia/carregarFotoApp/id/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final PhotoView photoView = new PhotoView(this);
    photoView.setMaximumScale(8);
    setContentView(photoView);

        Militar01Activity tk = new Militar01Activity( );
        token = tk.getToKen();

        Intent it = getIntent ( );
        id_militar = it.getStringExtra ( "token" );
        URL_I += id_militar;

    final ProgressDialog dlg = new ProgressDialog(this);
    dlg.setTitle("Loading...");
    dlg.setIndeterminate(false);
    dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    dlg.show();
    // this is going to load a 30mb download...
    Ion.with(getBaseContext () )
     .load(URL_I)
            .setLogging("DeepZoom", Log.VERBOSE)
           .progressDialog(dlg)
           .setBodyParameter ( "token", token )
            .withBitmap()
            .deepZoom()
    .intoImageView(photoView)
    .setCallback(new FutureCallback<ImageView>() {
        @Override
        public void onCompleted(Exception e, ImageView result) {
            dlg.cancel();
            Toast.makeText(DeepZoom.this, URL_I, Toast.LENGTH_SHORT).show();
            Toast.makeText(DeepZoom.this, id_militar, Toast.LENGTH_SHORT).show();
        }
    });
}
}
*/
