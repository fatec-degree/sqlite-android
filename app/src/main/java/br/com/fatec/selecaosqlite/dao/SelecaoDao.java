package br.com.fatec.selecaosqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import br.com.fatec.selecaosqlite.model.Selecao;

// Data access object
public class SelecaoDao extends SQLiteOpenHelper {

    private final String TABELA = "tb_selecao";

    public SelecaoDao(@Nullable Context context) {
        // nome e versão do database
        super(context, "db_copa", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ddl = "CREATE TABLE " + TABELA + "(" + // sqlite: todo integer pk é identity (auto_increment)
                "id INTEGER PRIMARY KEY," +
                "nome VARCHAR(100) NOT NULL UNIQUE," +
                "titulos INTEGER," +
                "continente VARCHAR(30) NOT NULL)";

        sqLiteDatabase.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // ALTER TABLE ADD nova_coluna de imagem
        // ou
        // DROP TABLE
        // CREATE TABLE
    }

    public long insert(Selecao selecao){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", selecao.getNome());
        contentValues.put("titulos", selecao.getTitulos());
        contentValues.put("continente", selecao.getContinente());

        return getWritableDatabase().insert(TABELA, null, contentValues); // retorna o id do último registro adicionado no banco
    }

    public ArrayList<Selecao> selectAll(){
        String sql = "SELECT * FROM " + TABELA;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null); // é semelhante a uma matriz
        ArrayList<Selecao> selecoes = new ArrayList<>();

        while(cursor.moveToNext()){
            Selecao selecao = new Selecao();
            selecao.setId(cursor.getInt(0));
            selecao.setNome(cursor.getString(1));
            selecao.setTitulos(cursor.getInt(2));
            selecao.setContinente(cursor.getString(3));
            selecoes.add(selecao);
        }
        return selecoes;
    }
}
