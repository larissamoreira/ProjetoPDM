package com.example.larissa.alarmoid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var lista: ListView
    private lateinit var dao: AtividadeDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        this.dao = AtividadeDAO(this)
        this.lista = findViewById(R.id.lvMainAtividades)
        this.lista.adapter = AtividadeAdapter(this)


        fab.setOnClickListener { view ->
            val intent = Intent(this, CadastroActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    private fun atualizar() {
        this.lista.adapter = AtividadeAdapter(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            val atividade = data?.getSerializableExtra("ATIVIDADE") as Atividade
            if(requestCode == 1) {
                this.dao.adicionar(atividade)
            }
            this.atualizar()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
