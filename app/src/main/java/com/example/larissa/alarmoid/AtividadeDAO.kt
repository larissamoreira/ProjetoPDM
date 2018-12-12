package com.example.larissa.alarmoid

import android.content.ContentValues
import android.content.Context

class AtividadeDAO{
    private lateinit var banco: BancoHelper
    private val TABLE = "atividade"

    constructor(context: Context) {
        this.banco = BancoHelper(context)
    }

    fun adicionar(atividade: Atividade) {
        val cv = ContentValues()
        cv.put("descricao", atividade.descricao)
        cv.put("hora", atividade.hora)
        cv.put("minuto", atividade.minuto)
        this.banco.writableDatabase.insert(TABLE, null, cv)
    }

    fun listar(): ArrayList<Atividade>{
        val colunas = arrayOf("id", "descricao", "hora", "minuto")
        val lista = ArrayList<Atividade>()

        val cursor = this.banco.readableDatabase.query(TABLE, colunas, null, null, null, null, null)

        if(cursor.count > 0) {
            cursor.moveToFirst()
            do {
                var atividade = Atividade()
                atividade.id = cursor.getInt(cursor.getColumnIndex("id"))
                atividade.descricao = cursor.getString(cursor.getColumnIndex("descricao"))
                atividade.hora = cursor.getInt(cursor.getColumnIndex("hora"))
                atividade.minuto = cursor.getInt(cursor.getColumnIndex("minuto"))
                lista.add(atividade)
            } while (cursor.moveToNext())
        }
        return lista
    }
}