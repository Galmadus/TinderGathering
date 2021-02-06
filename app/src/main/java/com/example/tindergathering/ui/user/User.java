package com.example.tindergathering.ui.user;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tindergathering.AccesLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class User {
    private String username;
    private String password;
    private Date birthday;
    private String gender;
    private String email;
    private int id;
    private String name;
    private String firstName;
    private String description;
    private String ville;

    public User(String username) {
        this.username = username;
    }

    public User(Context context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(){
        Random rd = new Random();
        Boolean bool = rd.nextBoolean();
        this.username = bool ? randomNameBoy() : randomNameGirl();
        this.password = "";
        Date birthday = new Date(ThreadLocalRandom.current().nextInt() * 1000L);
        birthday.setYear(new Random().nextInt((2002 - 1950) + 1) + 1950);
        this.birthday = birthday;
        this.gender =  bool ? "Homme" : "Femme";
        this.email = this.username+"@email.com";
        this.name = bool ? randomNameBoy() : randomNameGirl();
        this.firstName = bool ? randomNameBoy() : randomNameGirl();
        this.description = "description";
        this.ville = this.getVille();
    }

    private Context context;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return (2020 - this.getBirthday().getYear());
    }

    public String getVille() {
        List<String> list = new ArrayList<>();
        list.add("Reims");
        list.add("Epernay");
        list.add("Châlons");
        list.add("Paris");
        list.add("Dormans");
        list.add("Chateau-Thierry");
        list.add("Fisme");
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String randomNameGirl(){
        List<String> list = new ArrayList<>();
        list.add("Alice");
        list.add("Lina");
        list.add("Chloé");
        list.add("Rose");
        list.add("Léa");
        list.add("Mila");
        list.add("Mila");
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
    public String randomNameBoy(){
        List<String> list = new ArrayList<>();
        list.add("Gabriel");
        list.add("Léo");
        list.add("Raphaël");
        list.add("Arthur");
        list.add("Louis");
        list.add("Lucas");
        list.add("Adam");
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }


    // Return boolean true if entry found
    public boolean selectUserSQLite(int id) throws ParseException {
        AccesLocal accesLocal = new AccesLocal(this.context);
        SQLiteDatabase DB = accesLocal.getDB();
        String req = "SELECT " +
                "username, password, birthday, gender, email" +
                " FROM users WHERE id = "+id;
        Cursor cursor = DB .rawQuery(req,null);
        if(cursor.getCount() <= 0){
            cursor.moveToFirst();
            if(cursor.isFirst()){
                this.username = cursor.getString(1);
                this.password = cursor.getString(2);
                Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString(3));
                this.birthday = birthday;
                this.gender = cursor.getString(4);
                this.email = cursor.getString(5);

            }
        }
        boolean haveEntry = (cursor.getCount() <= 0);
        cursor.close();
        return haveEntry;
    }

    public void insertUserSQLite() throws ParseException {
        AccesLocal accesLocal = new AccesLocal(this.context);
        SQLiteDatabase DB = accesLocal.getDB();
        String req = "INSERT INTO users" +
                "(username, password, birthday, gender ,email)" +
                "VALUES(\""+this.username+"\",\""+this.password+"\",\""+this.birthday+"\",\""+this.gender+"\",\""+this.email+"\")";
        DB.execSQL(req);
    }

    public void updateUserSQLite() throws ParseException {
        AccesLocal accesLocal = new AccesLocal(this.context);
        SQLiteDatabase DB = accesLocal.getDB();
        String req = "UPDATE users " +
                "SET username"+"="+this.username+" AND " +
                "password"+"="+this.password+" AND " +
                "birthday"+"="+this.birthday+" AND " +
                "gender"+"="+this.gender+" AND " +
                "email"+"="+this.email+" " +
                " WHERE id ="+1;
        DB.execSQL(req);
    }

}

