package com.ccormor392.actividad_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var calc = Calculo()
    lateinit var numeros:MutableList<Button>
    lateinit var operaciones:MutableList<Button>
    lateinit var botonCE: Button
    lateinit var texto: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVariables()
        initListeners()

    }
    fun initVariables(){
        numeros = crearListaNumeros()
        operaciones = crearListaOperaciones()
        botonCE = findViewById(R.id.ce)
        texto = findViewById(R.id.textNumbers)
    }

    fun crearListaNumeros():MutableList<Button>{
        val listButtons = mutableListOf<Button>()
        listButtons.add(findViewById(R.id.one))
        listButtons.add(findViewById(R.id.two))
        listButtons.add(findViewById(R.id.three))
        listButtons.add(findViewById(R.id.four))
        listButtons.add(findViewById(R.id.five))
        listButtons.add(findViewById(R.id.six))
        listButtons.add(findViewById(R.id.seven))
        listButtons.add(findViewById(R.id.eight))
        listButtons.add(findViewById(R.id.nine))
        return listButtons
    }
    fun crearListaOperaciones():MutableList<Button>{
        val listButtons = mutableListOf<Button>()
        listButtons.add(findViewById(R.id.plus))
        listButtons.add(findViewById(R.id.minus))
        listButtons.add(findViewById(R.id.product))
        listButtons.add(findViewById(R.id.divide))
        return listButtons
    }
    fun initListeners(){
        for (boton in numeros){
            boton.setOnClickListener { listenerNumeros(boton) }
        }
    }
    fun listenerNumeros(boton:Button){
        texto.text = boton.text
    }
}