package cienciasdacomputacao.com.br.aps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "DAO", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

           db.execSQL("CREATE TABLE Updatetable( _id INTEGER PRIMARY KEY AUTOINCREMENT, jogador VARCHAR(100), fase INT, estrela INT, ponto INT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion == 1){
            if (newVersion == 2){
               //db.execSQL("ALTER TABLE clientes ADD blabla");
            }
        }

    }
}
