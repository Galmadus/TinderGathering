package com.example.tindergathering;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tindergathering.ui.user.User;

import java.text.DateFormat;
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
    public User selectUserSQLite(int id) throws ParseException {
        Log.v("selectUserSQLite", "id = "+id);
        DB = accesBD.getWritableDatabase();
        User u = new User();
        String req = "SELECT " +
                "id, \n" +
                "username, \n" +
                "gender, \n" +
                "email, \n" +
                "picture, \n" +
                "birthday, \n" +
                "firstName, \n" +
                "name, \n" +
                "password, \n" +
                "description, \n" +
                "city, \n" +
                "formats, \n" +
                "address_id " +
                " FROM user WHERE id = "+id;
        Cursor cursor = DB .rawQuery(req,null);
        cursor.moveToFirst();
        if(cursor.isFirst()){
            u.setId((cursor.getInt(cursor.getColumnIndex("id"))));
            u.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            u.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            u.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u.setPicture(cursor.getInt(cursor.getColumnIndex("picture")));

            String dateJson = cursor.getString(cursor.getColumnIndex("birthday"));
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date date = format.parse(dateJson);
            u.setBirthday(date);

            u.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
            u.setName(cursor.getString(cursor.getColumnIndex("name")));
            u.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            u.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            u.setCity(cursor.getString(cursor.getColumnIndex("city")));
            u.setFormats(cursor.getString(cursor.getColumnIndex("formats")));
//                u.setIdAddress(Integer.parseInt(cursor.getString(cursor.getColumnIndex("address_id"))));
        }

        Log.v("selectUserSQLite", u.toString());
        boolean haveEntry = (cursor.getCount() <= 0);
        cursor.close();
        return u;
    }

    public ArrayList<User> selectAllUserExceptUserInParamSQLite(int idCurrentUser) throws ParseException {
        DB = accesBD.getWritableDatabase();
        ArrayList<User> users = new ArrayList<>();
        String req = "SELECT " +
                "id, \n" +
                "username, \n" +
                "gender, \n" +
                "picture, \n" +
                "email, \n" +
                "birthday, \n" +
                "firstName, \n" +
                "name, \n" +
                "password, \n" +
                "description, \n" +
                "city, \n" +
                "formats, \n" +
                "address_id " +
                " FROM user WHERE id <> "+idCurrentUser;
        Cursor cursor = DB .rawQuery(req,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                user.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                user.setPicture(cursor.getInt(cursor.getColumnIndex("picture")));

                String dateJson = cursor.getString(cursor.getColumnIndex("birthday"));
                Log.v("AccesLocal", dateJson);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date date = format.parse(dateJson);
                user.setBirthday(date);

                user.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                user.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                user.setCity(cursor.getString(cursor.getColumnIndex("city")));
                user.setFormats(cursor.getString(cursor.getColumnIndex("formats")));
//                user.setIdAddress(Integer.parseInt(cursor.getString(cursor.getColumnIndex("address_id"))));
                users.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return users;
    }

    public long getUsersCount() {
        DB = accesBD.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(DB, "user");
        DB.close();
        return count;
    }
    public long getMatchCount() {
        DB = accesBD.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(DB, "`match`");
        DB.close();
        return count;
    }
    public boolean findMatchByUserNameSQLite(int userId) throws ParseException {
        DB = accesBD.getWritableDatabase();
        String req = "SELECT " +
                "username, \n" +
                "gender, \n" +
                "email, \n" +
                "picture, \n" +
                "birthday, \n" +
                "firstName, \n" +
                "name, \n" +
                "password, \n" +
                "description, \n" +
                "city, \n" +
                "formats, \n" +
                "address_id " +
                " FROM user u JOIN `match` m WHERE m.id = "+userId;
        Cursor cursor = DB .rawQuery(req,null);
        boolean haveEntry = (cursor.getCount() < 0);
        cursor.close();
        return haveEntry;
    }


    public void updateUserSQLite(User u) throws ParseException {
        DB = accesBD.getWritableDatabase();
        String req = "UPDATE " +
                "SET username=\""+u.getUsername()+"\", \n" +
                "SET gender=\""+u.getGender()+"\", \n" +
                "SET picture=\""+u.getPicture()+"\", \n" +
                "SET email=\""+u.getEmail()+"\", \n" +
                "SET birthday=\""+u.getBirthday()+"\", \n" +
                "SET firstName=\""+u.getFirstName()+"\", \n" +
                "SET name=\""+u.getName()+"\", \n" +
                "SET password=\""+u.getPassword()+"\", \n" +
                "SET description=\""+u.getDescription()+"\", \n" +
                "SET formats=\""+u.getFormats()+"\", \n" +
                "SET city=\""+u.getCity()+"\" \n" +
                " FROM user WHERE id = "+u.getId();
        DB.execSQL(req);
    }

    public void insertUserSQLite(User u) throws ParseException {
        DB = accesBD.getWritableDatabase();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(u.getBirthday());
        String req = "INSERT INTO user (username, gender,picture, email, birthday, firstName, name, password, description,formats, city) " +
                "VALUES (" +
                "\""+u.getUsername()  +"\","+
                "\""+u.getGender() +"\","+
                "\""+u.getPicture() +"\","+
                "\""+u.getEmail() +"\","+
                "\""+strDate +"\","+
                "\""+u.getFirstName() +"\","+
                "\""+u.getName() +"\","+
                "\""+u.getPassword() +"\","+
                "\""+u.getDescription() +"\","+
                "\""+u.getFormats() +"\","+
                "\""+u.getCity() +"\")";
        DB.execSQL(req);
    }

    // MATCHS
    public ArrayList<User> selectMatchFromUserSQLite(int id) throws ParseException {
            DB = accesBD.getWritableDatabase();
            ArrayList<User> users = new ArrayList<>();
            String req = "SELECT " +
                    "u.id,\n"+
                    "username, \n" +
                    "gender, \n" +
                    "picture, \n" +
                    "email, \n" +
                    "birthday, \n" +
                    "firstName, \n" +
                    "name, \n" +
                    "password, \n" +
                    "description, \n" +
                    "city, \n" +
                    "formats, \n" +
                    "address_id " +
                    " FROM user u JOIN `match` m ON u.id=m.user2 WHERE m.user1 == "+id;
            Cursor cursor = DB .rawQuery(req,null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()) {
                    User user = new User();
                    user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                    user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                    user.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                    user.setPicture(cursor.getInt(cursor.getColumnIndex("picture")));
                    user.setEmail(cursor.getString(cursor.getColumnIndex("email")));

                    String dateJson = cursor.getString(cursor.getColumnIndex("birthday"));
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = format.parse(dateJson);
                    user.setBirthday(date);

                    user.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    user.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    user.setCity(cursor.getString(cursor.getColumnIndex("city")));
                    user.setFormats(cursor.getString(cursor.getColumnIndex("formats")));
//                    user.setIdAddress(Integer.parseInt(cursor.getString(cursor.getColumnIndex("address_id"))));
                    users.add(user);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            return users;
        }
        public ArrayList<User> selectMatchByNameUserSQLite(String name) throws ParseException {
            DB = accesBD.getWritableDatabase();
            ArrayList<User> users = new ArrayList<>();
            String req = "SELECT " +
                    "u.id,\n"+
                    "username, \n" +
                    "gender, \n" +
                    "picture, \n" +
                    "email, \n" +
                    "birthday, \n" +
                    "firstName, \n" +
                    "name, \n" +
                    "password, \n" +
                    "description, \n" +
                    "city, \n" +
                    "formats, \n" +
                    "address_id " +
                    "FROM user u JOIN `match` m ON u.id=m.user2 " +
                    "WHERE " +
                    " m.user1 == 1"+
                    " AND (u.name LIKE \"%"+ name + "%\"" +
                    " OR u.firstName LIKE \"%"+ name + "%\")";
            Cursor cursor = DB .rawQuery(req,null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()) {
                    User user = new User();
                    user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                    user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                    user.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                    user.setPicture(cursor.getInt(cursor.getColumnIndex("picture")));
                    user.setEmail(cursor.getString(cursor.getColumnIndex("email")));

                    String dateJson = cursor.getString(cursor.getColumnIndex("birthday"));
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = format.parse(dateJson);
                    user.setBirthday(date);

                    user.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    user.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    user.setCity(cursor.getString(cursor.getColumnIndex("city")));
                    user.setFormats(cursor.getString(cursor.getColumnIndex("formats")));
//                    user.setIdAddress(Integer.parseInt(cursor.getString(cursor.getColumnIndex("address_id"))));
                    users.add(user);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            return users;
        }

    public void insertMatchSQLite(int idUserCurrent, int idUserMatched) throws ParseException {
        DB = accesBD.getWritableDatabase();
        String req = "INSERT INTO `match` (user1, user2) " +
                "VALUES ("+idUserCurrent+", "+idUserMatched+")";
        DB.execSQL(req);
    }


}