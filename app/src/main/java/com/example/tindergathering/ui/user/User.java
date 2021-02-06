package com.example.tindergathering.ui.user;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.tindergathering.AccesLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private String picture;
    private String name;
    private String firstName;
    private String description;
    private String city;
    private int idAddress;

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User(String username, String password, Date birthday, String gender, String email, String picture, String name, String firstName, String description, String city) {
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.picture = picture;
        this.name = name;
        this.firstName = firstName;
        this.description = description;
        this.city = city;
    }

    public User(String username) {
        this.username = username;
    }

    public User(Context context) {
        this.context = context;
    }

    public Bitmap getPictureBitmap() {
        byte[] imageBytes = android.util.Base64.decode(picture, android.util.Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }

    public String getPicture() {
        return this.picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
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
        this.city = this.getVille();
        this.idAddress = 1;
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
        GregorianCalendar dateNow = new GregorianCalendar();
        GregorianCalendar birthday = new GregorianCalendar();
        GregorianCalendar birthdayCurrentYear = new GregorianCalendar();

        birthday.setTime(this.birthday);
        birthdayCurrentYear.setTime(this.birthday);
        birthdayCurrentYear.set(Calendar.YEAR, dateNow.get(Calendar.YEAR));

        int age = dateNow.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

        if(dateNow.getTimeInMillis() < birthdayCurrentYear.getTimeInMillis())
            age--;

        return age;
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

    public String getRandomFormat(){
        List<String> list = new ArrayList<>();
        list.add("Standard");
        list.add("Commander");
        list.add("Brawl");
        list.add("Pioneer");
        list.add("Vintage");
        int randomNumber = 1 + (int)(Math.random() * ((list.size() - 1) + 1));
        String listeFormat = "";
        Random rand = new Random();
        for(int i = 0; i < randomNumber; i++){
            String t = list.get(rand.nextInt(list.size()));
            listeFormat = listeFormat + t + ",";
            list.remove(t);
        }
        return listeFormat;
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
        list.add("Chloe");
        list.add("Rose");
        list.add("Lea");
        list.add("Mila");
        list.add("Mila");
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
    public String randomNameBoy(){
        List<String> list = new ArrayList<>();
        list.add("Gabriel");
        list.add("Leo");
        list.add("Raphaël");
        list.add("Arthur");
        list.add("Louis");
        list.add("Lucas");
        list.add("Adam");
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}

