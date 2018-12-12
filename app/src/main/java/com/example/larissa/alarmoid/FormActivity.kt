package com.example.larissa.alarmoid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText

class FormActivity: AppCompatActivity() {
    private lateinit var etDescricao: EditText
    private lateinit var etHora: EditText
    private lateinit var etMinuto: EditText
    private lateinit var btSalvar: Button
    private lateinit var atividade: Atividade

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_form)

        this.etDescricao = findViewById(R.id.etFormDescricao)
        this.etHora = findViewById(R.id.etFormHora)
        this.etMinuto = findViewById(R.id.etFormMinuto)

        if(intent.getSerializableExtra("ATIVIDADE") != null) {
            this.atividade = intent.getSerializableExtra("ATIVIDADE") as Atividade
            this.etDescricao.text.append(this.atividade.descricao)
            this.etHora.text.append(this.atividade.hora.toString())
            this.etMinuto.text.append(this.atividade.minuto.toString())
        } else {
            this.atividade = Atividade()
        }

        this.btSalvar.setOnClickListener({salvar(it)})

    }

    private fun salvar(view: View) {
        val descricao = this.etDescricao.text.toString()
        val hora = this.etHora.text as Int
        val minuto = this.etMinuto.text as Int
        this.atividade.descricao = descricao
        this.atividade.hora = hora
        this.atividade.minuto = minuto


        val intent = Intent()
        intent.putExtra("ATIVIDADE", this.atividade)
        setResult(Activity.RESULT_OK, intent)
    }
}