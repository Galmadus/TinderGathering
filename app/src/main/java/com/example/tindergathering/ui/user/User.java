package com.example.tindergathering.ui.user;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import com.example.tindergathering.AccesLocal;
import com.example.tindergathering.R;

import java.io.ByteArrayOutputStream;
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
    private int picture;
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

    public User(String username, String password, Date birthday, String gender, String email, int picture, String name, String firstName, String description, String city) {
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


    /*public Bitmap getPictureBitmap() {
        byte[] imageBytes = android.util.Base64.decode(picture, android.util.Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }*/

    public int getPicture() {
        return this.picture;
    }
    public void setPicture(int picture) {
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
        this.picture = randomDrawable();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return firstName+' '+name;
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

    public int randomDrawable(){
        List<Integer>list = new ArrayList<>();
        list.add(R.drawable.sample1);
        list.add(R.drawable.sample2);
        list.add(R.drawable.sample3);
        list.add(R.drawable.sample4);
        list.add(R.drawable.sample5);
        list.add(R.drawable.sample6);
        list.add(R.drawable.sample7);
        list.add(R.drawable.sample8);
        list.add(R.drawable.sample9);
        list.add(R.drawable.sample10);
        list.add(R.drawable.sample11);
        list.add(R.drawable.sample12);
        list.add(R.drawable.sample13);
        list.add(R.drawable.sample14);
        list.add(R.drawable.sample15);
        list.add(R.drawable.sample16);
        list.add(R.drawable.sample17);
        list.add(R.drawable.sample18);
        list.add(R.drawable.sample19);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}

