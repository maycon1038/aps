package cienciasdacomputacao.com.br.aps;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class UpdatetableDAO {

    //MÉTODOS PAARA TRABALHAR COM A TABELA Updatetable
    private Context ctx;
    private String   table_name = "Updatetable";
    private String[] colunas    = new String[]{
            "_id", "tabelas", "date_time"};

    public UpdatetableDAO(Context ctx) {
        this.ctx = ctx;
    }
    public boolean insert(UpdatetableVO updatetable) {
        SQLiteDatabase db   = new DBHelper(ctx).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("table", updatetable.getTabelas());
        values.put("date_time", updatetable.getDate_time());
        return (db.insert(table_name, null, values) > 0);
    }

    public Cursor buscarTudo() {
        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();
        Cursor c = db.query(table_name, colunas, null, null, null, null, null);
        if (c == null) {
            return null;
        } else if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        return c;
    }
    //-----------------------------------------
    public Cursor buscarString(String txtbusca) {

        SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();

        String[] busca = new String[]{"%" + txtbusca + "%","%" + txtbusca + "%"};

        Cursor cursor = db.query(table_name, colunas, "table LIKE ? or date_time LIKE ?", busca, null, null, "_id ASC", null);
        return cursor;
    }
    public boolean update(UpdatetableVO updatetable , int cod) {

        String   where = "date_time = ?";
        String[] codigocoparar = new String[]{String.valueOf(cod), String.valueOf ( 0 )};

        SQLiteDatabase db     = new DBHelper(ctx).getWritableDatabase();
        ContentValues  values = new ContentValues();
        values.put("table", updatetable.getTabelas());
        values.put("date_time", updatetable.getDate_time());
        return (db.update(table_name, values, where, codigocoparar) > 0);

    }
    public boolean deletaEscala( String codigo) {

        SQLiteDatabase db    = new DBHelper(ctx).getWritableDatabase();
        return (db.delete(table_name, "date_time = ?", new String[]{codigo, String.valueOf ( 0 )}) > 0);
    }
    public int tamDb() {
        SQLiteDatabase db     = new DBHelper(ctx).getWritableDatabase();
        Cursor  cursor = db.query(table_name, colunas, null, null, null, null, null);
        return cursor.getCount();
    }
    public List<UpdatetableVO> lista() {

        List<UpdatetableVO> lista = new ArrayList<UpdatetableVO>();
        try {
            SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();
            Cursor         c  = db.query(table_name, colunas, null, null, null, null, null);

            while (c.moveToNext()) {
                UpdatetableVO updatetable = new UpdatetableVO();
                updatetable.setTabelas(c.getString(c.getColumnIndex("table")));
                updatetable.setDate_time(c.getString(c.getColumnIndex("date_time")));
                lista.add(updatetable);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public boolean VerificaSeTemIdBD_Local ( int coddatetime) {

        boolean tiporetorn = false;
        String  codretorn  = Integer.toString(coddatetime);
        try {
            SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();

            String[] busca = new String[]{codretorn};
            String[] colun = new String[]{"date_time"};

            Cursor c = db.query(table_name, colun, "date_time = ?", busca, null, null, null, null);
            if (c.getCount() >= 1) {
                tiporetorn = true;
            } else {
                tiporetorn = false;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("retunoIdequipeescala", " " + tiporetorn);
        return tiporetorn;
    }
}
