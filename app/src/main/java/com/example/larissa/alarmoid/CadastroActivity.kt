package com.example.larissa.alarmoid

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.util.Log
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
    private lateinit var alarmMgr: AlarmManager
    private lateinit var pIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        this.etDescricao = findViewById(R.id.etFormDescricao)
        this.etHora = findViewById(R.id.etFormHora)
        this.etMinuto = findViewById(R.id.etFormMinuto)
        this.btSalvar = findViewById(R.id.btFormSalvar)

        if(intent.getSerializableExtra("ATIVIDADE") != null) {
            this.atividade = intent.getSerializableExtra("ATIVIDADE") as Atividade
            this.etDescricao.text.append(this.atividade.descricao)
            this.etHora.text.append(this.atividade.hora.toString())
            this.etMinuto.text.append(this.atividade.minuto.toString())
        } else {
            this.atividade = Atividade()
        }
        this.alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val broadcastIntent = Intent(this, AlarmBroadcastReceiver::class.java)
        this.pIntent = PendingIntent.getBroadcast(this, 0 , broadcastIntent, 0)


        this.btSalvar.setOnClickListener({salvar(it)})
    }

    private fun salvar(view: View) {
        val descricao = this.etDescricao.text.toString()
        val hora = Integer.parseInt(this.etHora.text.toString())
        val minuto = Integer.parseInt(this.etMinuto.text.toString())
        this.atividade.descricao = descricao
        this.atividade.hora = hora
        this.atividade.minuto = minuto

        alarmMgr.set(
                AlarmManager.RTC_WAKEUP,
                Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, hora)
                    set(Calendar.MINUTE, minuto)
                    set(Calendar.SECOND, 0)
                }.timeInMillis,
                pIntent
        )

        Toast.makeText(this@CadastroActivity, "O alarme foi setado.", Toast.LENGTH_LONG).show()

        val intent = Intent()
        intent.putExtra("ATIVIDADE", this.atividade)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}

class AlarmBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        // Create the notification to be shown
        val mBuilder = NotificationCompat.Builder(context!!)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Alarm Manager")
                .setContentText("Atividade")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Get the Notification manager service
        val am = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Generate an Id for each notification
        val id = System.currentTimeMillis()/1000

        // Show a notification
        am.notify(id.toInt(), mBuilder.build())
    }
}