package com.example.tindergathering;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AccesLocal {
    private static final String TAG = "AccesLocal";
    private String DBName ="MagicTinder.sqlite";
    private Integer DBVersion = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase DB;

    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context,DBName,null,DBVersion);
        DB = accesBD.getWritableDatabase();
    }

    public String getDBName() {
        return DBName;
    }

    public Integer getDBVersion() {
        return DBVersion;
    }

    public MySQLiteOpenHelper getAccesBD() {
        return accesBD;
    }

    public SQLiteDatabase getDB() {
        return DB;
    }
//
//    //  CLIENTS
//    public void createClient(Client client){
//        DB = accesBD.getWritableDatabase();
//        String req = "INSERT INTO CLIENTS" +
//                "(name,surname,gender,mail,phone,adress)" +
//                "VALUES(\""+client.getName()+"\",\""+client.getSurname()+"\",\""+client.getGender()+"\",\""+client.getMail()+"\",\""+client.getPhone()+"\",\""+client.getAdress()+"\")";
//        DB.execSQL(req);
//    }
//
//    //AVANTAGES
//    public void createAvantage(int idClient,String name, int quantity){
//        Log.d(TAG, "createAvantage: START");
//        DB = accesBD.getWritableDatabase();
//        String req = "INSERT INTO AVANTAGES (idClient,name,quantity) " +
//                "VALUES("+idClient+",\""+name+"\","+quantity+")";
//        DB.execSQL(req);
//        Log.d(TAG, "createAvantage: END");
//    }
//    public boolean existAvantage(int idClient,String name){
//
//        DB = accesBD.getWritableDatabase();
//        String req = "SELECT COUNT(name) FROM AVANTAGES WHERE idClient ="+idClient+" AND name=\""+name+"\"";
//        Cursor cursor = DB .rawQuery(req,null);
//        int counter=-1;
//
//        if (cursor.moveToFirst()) {
//            counter = cursor.getInt(0);
//        }
//        cursor.close();
//        Log.d(TAG, "existAvantage: "+counter);
//        return counter != 0 ;
//    }
//    //  CARDS HOMME
//    public void createCardHomme(int idClient){
//        DB = accesBD.getWritableDatabase();
//        String req = "INSERT INTO CARDS_HOMME" +
//                "(idClient) VALUES ("+idClient+")";
//        DB.execSQL(req);
//    }
//    public void deleteCardHomme(int id){
//        DB = accesBD.getReadableDatabase();
//        String req = "DELETE FROM CARDS_HOMME WHERE IDCLIENT="+id;
//        DB.execSQL(req);
//    }
//    public void addValueCardHomme(int idClient, int caseNumber, String value){
//        DB = accesBD.getWritableDatabase();
//        String req = "UPDATE CARDS_HOMME SET Case"+caseNumber+" ='"+value+"' WHERE idClient ="+idClient;
//        DB.execSQL(req);
//    }


}