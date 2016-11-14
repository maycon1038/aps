package cienciasdacomputacao.com.br.aps;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class UpdatetableDAO {

    //MÉTODOS PAARA TRABALHAR COM A TABELA Updatetable
    private Context ctx;
    private String   table_name = "Updatetable";
    private String[] colunas    = new String[]{
            "_id", "jogador", "fase","estrela","ponto"};

    public UpdatetableDAO(Context ctx) {
        this.ctx = ctx;
    }
    public boolean insert(UpdatetableVO tabela) {
        SQLiteDatabase db   = new DBHelper(ctx).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("jogador", tabela.getJogador());
        values.put("fase", tabela.getFase());
        values.put("estrela", tabela.getEstrela());
        values.put("ponto", tabela.getPonto());
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

        String[] busca = new String[]{txtbusca};

        Cursor cursor = db.query(table_name, colunas, "fase = ?", busca, null, null, "_id ASC", null);
        return cursor;
    }
    public boolean update(UpdatetableVO tabela , int cod) {

        String   where = "fase = ?";
        SQLiteDatabase db     = new DBHelper(ctx).getWritableDatabase();
        ContentValues  values = new ContentValues();
        values.put("jogador", tabela.getJogador());
        values.put("fase", tabela.getFase());
        values.put("estrela", tabela.getEstrela());
        values.put("ponto", tabela.getPonto());
        return (db.update(table_name, values, where, new String[]{String.valueOf(cod)}) > 0);

    }
    public boolean deletar() {

        SQLiteDatabase db    = new DBHelper(ctx).getWritableDatabase();
        return (db.delete(table_name, null, null) > 0);
    }
    public int tamDb() {
        SQLiteDatabase db     = new DBHelper(ctx).getWritableDatabase();
        Cursor  cursor = db.query(table_name, colunas, null, null, null, null, null);
        return cursor.getCount();
    }
    public int totalpontos(int codigo) {
        int total=0;
        SQLiteDatabase db     = new DBHelper(ctx).getWritableDatabase();
        Cursor  c = db.query(table_name, colunas, "fase = 1", new String[]{String.valueOf(codigo)}, null, null, null);
        if (c != null) {
            total =  c.getInt(c.getColumnIndex("ponto"));
        }
      return total;
    }
    public List<UpdatetableVO> lista() {

        List<UpdatetableVO> lista = new ArrayList<UpdatetableVO>();
        try {
            SQLiteDatabase db = new DBHelper(ctx).getWritableDatabase();
            Cursor         c  = db.query(table_name, colunas, null, null, null, null, null);

            while (c.moveToNext()) {
                UpdatetableVO tabela = new UpdatetableVO();
                tabela.setJogador(c.getString(c.getColumnIndex("jogador")));
                tabela.setFase(c.getInt(c.getColumnIndex("fase")));
                tabela.setEstrela(c.getInt(c.getColumnIndex("estrela")));
                tabela.setPonto(c.getInt(c.getColumnIndex("pontos")));
                lista.add(tabela);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
