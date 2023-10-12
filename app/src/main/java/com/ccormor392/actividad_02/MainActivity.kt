package com.ccormor392.actividad_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var calc = Calculo()
    lateinit var numeros:MutableList<Button>
    lateinit var operaciones:MutableList<Button>
    lateinit var botonCE: Button
    lateinit var mainTexto: TextView
    lateinit var secondTexto: TextView
    lateinit var igual:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVariables()
        initListeners()

    }

    /**
     * Inicializa todas las variables de la MainActivity utilizando findViewById()
     */
    fun initVariables(){
        numeros = crearListaNumeros()
        operaciones = crearListaOperaciones()
        botonCE = findViewById(R.id.ce)
        mainTexto = findViewById(R.id.textNumbers)
        secondTexto = findViewById(R.id.textMemoryNumbers)
        igual = findViewById(R.id.equal)
    }

    /**
     * Lee todos los botones que esten del 0 al 9
     * @param listButtons lista que se le añaden los botones
     * @return Una lista con todos los botones numericos
     */
    fun crearListaNumeros():MutableList<Button>{
        val listButtons = mutableListOf<Button>()
        listButtons.add(findViewById(R.id.zero))
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
    /**
     * Lee todos los botones que sean un tipo de operacion
     * @param listButtons lista que se le añaden los botones
     * @return Una lista con todos los botones de operaciones
     */
    fun crearListaOperaciones():MutableList<Button>{
        val listButtons = mutableListOf<Button>()
        listButtons.add(findViewById(R.id.plus))
        listButtons.add(findViewById(R.id.minus))
        listButtons.add(findViewById(R.id.product))
        listButtons.add(findViewById(R.id.divide))
        return listButtons
    }

    /**
     * Define los listeners de todos los botones
     */
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

    /**
     * Segun el boton numerico pulsado se añade al textView para que se muestre por pantalla
     * @param boton el boton numerico
     */
    fun listenerNumeros(boton:Button){
        if (esOperacion(getTextViewText(mainTexto))){
            mainTexto.text=""
        }//si el textView tiene una operacion la borra
        mainTexto.text ="${mainTexto.text}${boton.text}"//concatena los numeros al textView
        concatenarTextoATextView(secondTexto, boton.text.toString())
    }
    fun listenerOperaciones(boton: Button){
        if (!esOperacion(getTextViewText(mainTexto))) {
            if (calc.esperandoA == "num1") {
                calc.num1 = getTextViewText(mainTexto).toFloat()
                secondTexto.text = "${calc.num1}${boton.text}"
                mainTexto.text = boton.text
                calc.esperandoA = "num2"
            } else if (calc.esperandoA == "num2") {
                calc.num2 = getTextViewText(mainTexto).toFloat()
                calc.operar()
                calc.num1 = calc.resultado
                calc.num2 = 0f
                secondTexto.text = "${calc.num1}${boton.text}"
                mainTexto.text = boton.text
            }
        }
        else{
            mainTexto.text = boton.text
            secondTexto.text = "${calc.num1}${boton.text}"
        }
        calc.operacion = getButtonText(boton)

        /*
        if (calc.esPrimeraOperacion) {
            calc.num1 = getTextViewText(mainTexto).toFloat()
            secondTexto.text = "${secondTexto.text}${boton.text}"
            calc.esPrimeraOperacion = false
            mainTexto.text = boton.text
        } else {
            calc.num2 = getTextViewText(mainTexto).toFloat()
            calc.operar()
            calc.num1 = calc.resultado
            calc.num2 = 0f
            secondTexto.text = "${calc.num1}${boton.text}"
            mainTexto.text = boton.text
        }
         */



    }
    fun listenerIgual(){
        if (esOperacion(getTextViewText(mainTexto))||getTextViewText(mainTexto)==""||calc.operacion=="")
            Toast.makeText(this, "Introduce el operando y dos numeros antes de pulsar igual", Toast.LENGTH_SHORT).show()
        else{
            calc.num2 = getTextViewText(mainTexto).toFloat()
            calc.operar()
            concatenarTextoATextView(secondTexto, "=")
            mainTexto.text = calc.resultado.toString()
            concatenarTextoATextView(secondTexto, calc.resultado.toString())
            //prueba
            calc.reset()
        }
    }
    fun listenerBotonCE(){
        calc.reset() //reinicio todos los atributos del objeto calc
        mainTexto.text = ""
        secondTexto.text = "" //borro lo que haya en la pantalla
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
    fun concatenarTextoATextView(texto: TextView, string: String){
        texto.text = "${texto.text}${string}"
    }
}