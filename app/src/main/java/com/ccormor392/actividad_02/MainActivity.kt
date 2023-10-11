package com.ccormor392.actividad_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var calc = Calculo()
    lateinit var numeros:MutableList<Button>
    lateinit var operaciones:MutableList<Button>
    lateinit var botonCE: Button
    lateinit var texto: TextView
    lateinit var igual:Button
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
        igual = findViewById(R.id.equal)
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
        for (botonNum in numeros){
            botonNum.setOnClickListener { listenerNumeros(botonNum) }
        }
        for (botonOp in operaciones){
            botonOp.setOnClickListener { listenerOperaciones(botonOp) }
        }
        igual.setOnClickListener { listenerIgual() }
        botonCE.setOnClickListener { listenerBotonCE() }
    }
    fun listenerNumeros(boton:Button){
        if (esOperacion(getTextViewText(texto))){
            texto.text=""
        }
       texto.text ="${texto.text}${boton.text}"
    }
    fun listenerOperaciones(boton: Button){
        if (!esOperacion(getTextViewText(texto))) {
            if (calc.esPrimeraOperacion) {
                calc.num1 = getTextViewText(texto).toFloat()
                calc.esPrimeraOperacion = false
            } else {
                calc.num2 = getTextViewText(texto).toFloat()
                calc.operar()
                calc.num1 = calc.resultado
            }
        }
        calc.operacion = getButtonText(boton)
        texto.text = boton.text
    }
    fun listenerIgual(){
        if (calc.operacionIncompleta())
            Toast.makeText(this, "Introduce el operando y dos numeros antes de pulsar igual", Toast.LENGTH_SHORT).show()
        else if (!esOperacion(getTextViewText(texto))){
            calc.num2 = getTextViewText(texto).toFloat()
            calc.operar()
            texto.text = calc.resultado.toString()
            //prueba
            calc.reset()
        }
    }
    fun listenerBotonCE(){
        calc.reset() //reinicio todos los atributos del objeto calc
        texto.text = "" //borro lo que haya en la pantalla
    }
    fun esOperacion(string: String):Boolean{
        for (operacion in operaciones) {
            if (string == operacion.text){
                return true
            }
        }
        return false
    }
    fun getButtonText(boton: Button):String{
        return boton.text.toString()
    }
    fun getTextViewText(texto:TextView):String{
        return texto.text.toString()
    }

}