package com.example.tindergathering;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tindergathering.ui.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccesLocal {
    private static final String TAG = "AccesLocal";
    private String DBName ="MagicTinder.sqlite";
    private Integer DBVersion = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase DB;

    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context,DBName,null,DBVersion);
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

    // Return boolean true if entry found
    public User selectUserSQLite(User u) throws ParseException {
        DB = accesBD.getWritableDatabase();
        String req = "SELECT " +
                "username, \n" +
                "gender, \n" +
                "email, \n" +
                "birthday, \n" +
                "firstName, \n" +
                "name, \n" +
                "password, \n" +
                "description, \n" +
                "ville, \n" +
                "address_id " +
                " FROM user WHERE id = "+u.getId();
        Cursor cursor = DB .rawQuery(req,null);
        if(cursor.getCount() <= 0){
            cursor.moveToFirst();
            if(cursor.isFirst()){
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("username")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("gender")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("email")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("birthday")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("firstName")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("name")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("password")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("description")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("ville")));
                u.setFirstName(cursor.getColumnName(cursor.getColumnIndex("address")));
            }
        }
        boolean haveEntry = (cursor.getCount() <= 0);
        cursor.close();
        return u;
    }

    public ArrayList<User> selectAllUserExceptUserInParamSQLite(User u) throws ParseException {
        DB = accesBD.getWritableDatabase();
        ArrayList<User> users = new ArrayList<>();
        String req = "SELECT " +
                "username, \n" +
                "gender, \n" +
                "email, \n" +
                "birthday, \n" +
                "firstName, \n" +
                "name, \n" +
                "password, \n" +
                "description, \n" +
                "ville, \n" +
                "address_id " +
                " FROM user WHERE id <> 1" /*+u.getId()*/;
        Cursor cursor = DB .rawQuery(req,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                User user = new User();
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("username")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("gender")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("email")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("birthday")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("firstName")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("name")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("password")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("description")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("ville")));
                user.setFirstName(cursor.getColumnName(cursor.getColumnIndex("address")));
                users.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return users;
    }

    public boolean findUserSQLite(User u) throws ParseException {
        DB = accesBD.getWritableDatabase();
        String req = "SELECT " +
                "username, \n" +
                "gender, \n" +
                "email, \n" +
                "birthday, \n" +
                "firstName, \n" +
                "name, \n" +
                "password, \n" +
                "description, \n" +
                "ville, \n" +
                "address_id " +
                " FROM user WHERE id = "+u.getId();
        Cursor cursor = DB .rawQuery(req,null);
        boolean haveEntry = (cursor.getCount() <= 0);
        cursor.close();
        return haveEntry;
    }


    public void updateUserSQLite(User u) throws ParseException {
        DB = accesBD.getWritableDatabase();
        String req = "UPDATE " +
                "SET username=\""+u.getEmail()+"\", \n" +
                "SET gender=\""+u.getEmail()+"\", \n" +
                "SET email=\""+u.getEmail()+"\", \n" +
                "SET birthday=\""+u.getEmail()+"\", \n" +
                "SET firstName=\""+u.getEmail()+"\", \n" +
                "SET name=\""+u.getEmail()+"\", \n" +
                "SET password=\""+u.getEmail()+"\", \n" +
                "SET description=\""+u.getEmail()+"\", \n" +
                "SET ville=\""+u.getEmail()+"\" \n" +
                " FROM user WHERE id = "+u.getId();
        DB.execSQL(req);
    }

    public void insertUserSQLite(User u) throws ParseException {
        DB = accesBD.getWritableDatabase();
        String req = "INSERT INTO user (username, gender, email, birthday, firstName, name, password, description, ville) " +
                "VALUES (" +
                "\""+u.getUsername()  +"\","+
                "\""+u.getGender() +"\","+
                "\""+u.getEmail() +"\","+
                "\""+u.getBirthday() +"\","+
                "\""+u.getFirstName() +"\","+
                "\""+u.getName() +"\","+
                "\""+u.getPassword() +"\","+
                "\""+u.getDescription() +"\","+
                "\""+u.getVille() +"\")";
        DB.execSQL(req);
    }




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