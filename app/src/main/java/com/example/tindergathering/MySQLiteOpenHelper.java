package com.example.tindergathering;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String createTableUser ="create table \"user\"\n" +
            "(\n" +
            "    id         integer      not null\n" +
            "        constraint user_pkey\n" +
            "            primary key,\n" +
            "    username   varchar(180) not null,\n" +
            "    roles      json         not null,\n" +
            "    password   varchar(255) not null,\n" +
            "    address_id integer\n" +
            "        constraint fk_8d93d649f5b7af75\n" +
            "            references address\n" +
            ");";
    private String createTableAdresse ="create table address\n" +
            "(\n" +
            "    id        integer      not null\n" +
            "        constraint address_pkey\n" +
            "            primary key,\n" +
            "    city      varchar(180) not null,\n" +
            "    street    varchar(180),\n" +
            "    location  varchar(180),\n" +
            "    longitude varchar(180),\n" +
            "    latitude  varchar(180)\n" +
            ");";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //If BD change
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableUser);
        sqLiteDatabase.execSQL(createTableAdresse);
        sqLiteDatabase.execSQL(createTableUser);
    }

    //If BD version change
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}