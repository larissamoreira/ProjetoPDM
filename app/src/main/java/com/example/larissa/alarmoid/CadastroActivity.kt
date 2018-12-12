package com.example.larissa.alarmoid

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class CadastroActivity : AppCompatActivity() {
    private lateinit var etHora: EditText
    private lateinit var etMinuto: EditText
    private lateinit var btSalvar: Button
    private lateinit var atividade: Atividade
    private lateinit var etDescricao: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
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

        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val broadcastIntent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(this, 0 , broadcastIntent, 0)
        alarmMgr.set(
                AlarmManager.RTC_WAKEUP,
                Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hora)
                    set(Calendar.MINUTE, minuto)
                    set(Calendar.SECOND, 0)
                }.timeInMillis,
                pIntent
        )
    }
}

class AlarmBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Teste", Toast.LENGTH_SHORT).show()
    }
}