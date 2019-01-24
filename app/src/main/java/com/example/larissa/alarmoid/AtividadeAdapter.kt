package com.example.larissa.alarmoid

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AtividadeAdapter(var context: Context): BaseAdapter() {
    private lateinit var dao: AtividadeDAO
    private lateinit var lista: ArrayList<Atividade>

    init {
        this.dao = AtividadeDAO(context)
        this.lista = this.dao.listar()
    }

    override fun getCount(): Int {
        return this.lista.size
    }

    override fun getItem(position: Int): Any {
        return this.lista[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val atividade = this.lista[position]
        val layout: View

        if(convertView == null) {
            layout = View.inflate(context, R.layout.atividade_layout, null)
        } else {
            layout = convertView
        }

        if(position % 2 == 0){
            layout.setBackgroundColor(Color.WHITE)
        } else {
            layout.setBackgroundColor(Color.GRAY)
        }

        val tvDescricao = layout.findViewById<TextView>(R.id.tvAtividadeDescricao)
        val tvHora = layout.findViewById<TextView>(R.id.tvAtividadeHora)
        val tvMinuto = layout.findViewById<TextView>(R.id.tvAtividadeMinuto)

        tvDescricao.text = atividade.descricao
        tvHora.text = atividade.hora.toString()
        tvMinuto.text = atividade.minuto.toString()

        return layout
    }
}