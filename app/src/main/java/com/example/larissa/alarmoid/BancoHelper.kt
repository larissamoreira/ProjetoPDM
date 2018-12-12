package com.example.larissa.alarmoid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context): SQLiteOpenHelper(context, "atividades.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not exists atividade(id integer primary key autoincrement,descricao text, hora integer, minuto integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}