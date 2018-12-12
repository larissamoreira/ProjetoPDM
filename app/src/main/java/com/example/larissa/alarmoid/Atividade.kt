package com.example.larissa.alarmoid

import java.io.Serializable

class Atividade: Serializable {
    var id: Int
    var descricao: String
    var hora: Int
    var minuto: Int

    constructor() {
        this.id = -1
        this.descricao = ""
        this.hora = 0
        this.minuto = 0
    }

    constructor(descricao: String, hora: Int, minuto: Int) {
        this.id = -1
        this.descricao = descricao
        this.hora = hora
        this.minuto = minuto
    }

    override fun toString(): String {
        return "${this.id} - ${this.descricao} - ${this.hora}:${this.minuto}"
    }
}