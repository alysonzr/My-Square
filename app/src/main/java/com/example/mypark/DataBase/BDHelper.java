package com.example.mypark.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class BDHelper extends SQLiteOpenHelper {

    private  static int versao=1;
    private static String nome= "Banco_de_dados_mypark";

    public BDHelper(@Nullable Context context) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Utilizador(nome TEXT PRIMARY KEY, senha TEXT);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Utilizador");
        onCreate(db);
    }
    public long CriarUtilizador(String nome, String senha){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",nome);
        cv.put("senha",senha);
        long result = db.insert("Utilizador",null,cv);
        return result;
    }
    public String ValidarLogin(String nome, String senha){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor c = bd.rawQuery("SELECT * FROM Utilizador WHERE nome=? AND senha=?", new String[]{nome,senha});
        if(c.getCount() > 0){
            return "OK";
        }
        return "ERRO";
    }
}
