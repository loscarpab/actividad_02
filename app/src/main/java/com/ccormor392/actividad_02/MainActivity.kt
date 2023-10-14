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
        concatenarTextoATextView(mainTexto, boton.text.toString())//concatena los numeros al textView
        concatenarTextoATextView(secondTexto, boton.text.toString())
    }

    /**
     * Refleja en los dos TextView la operacion pulsada, modifica el atributo operacion de la clase calc por la operacion pulsada
     * y si es necesario realiza la operacion
     * @param boton el boton pulsado con la operacion
     */
    fun listenerOperaciones(boton: Button){
        if (!esOperacion(getTextViewText(mainTexto))) {
            if (calc.esperandoA == "num1") {// si es la primera vez que se pulsa un boton de operacion
                calc.num1 = getTextViewText(mainTexto).toFloat()
                mainTexto.text = boton.text
                calc.esperandoA = "num2"
            } else if (calc.esperandoA == "num2") { //si ya se ha pulsado antes algun boton de operacion
                calc.num2 = getTextViewText(mainTexto).toFloat()
                calc.operar()
                calc.num1 = calc.resultado
                calc.num2 = 0f
                mainTexto.text = boton.text
            }
        }
        else{// si el ultimo boton pulsado es una operacion, si pulsas otra operacion se sustituye por la nueva pulsada
            mainTexto.text = boton.text

        }
        calc.operacion = getButtonText(boton)
        secondTexto.text = "${calc.num1}${boton.text}"
    }

    /**
     * Realiza la operacion correspondiente dentro de la clase Calculo y la refleja por pantalla
     *
     */
    fun listenerIgual(){
        if (esOperacion(getTextViewText(mainTexto))||getTextViewText(mainTexto)==""||calc.operacion=="")
            Toast.makeText(this, "Introduce el operando y dos numeros antes de pulsar igual", Toast.LENGTH_SHORT).show()
        else{
            calc.num2 = getTextViewText(mainTexto).toFloat()
            calc.operar()
            concatenarTextoATextView(secondTexto, "=")
            mainTexto.text = calc.resultado.toString()
            concatenarTextoATextView(secondTexto, calc.resultado.toString())
            calc.reset()
        }
    }

    /**
     * reinicio de todas las variables de la clase Calculo y de los TextView
     */
    fun listenerBotonCE(){
        calc.reset() //reinicio todos los atributos del objeto calc
        mainTexto.text = ""
        secondTexto.text = "" //borro lo que haya en la pantalla
    }

    /**
     * Detecta si el string es una operacion o no
     */
    fun esOperacion(string: String):Boolean{
        for (operacion in operaciones) {
            if (string == operacion.text){
                return true
            }
        }
        return false
    }

    /**
     * devuelve el texto de un boton
     */
    fun getButtonText(boton: Button):String{
        return boton.text.toString()
    }

    /**
     * devuelve el texto de un TextView
     */
    fun getTextViewText(texto:TextView):String{
        return texto.text.toString()
    }

    /**
     * concatena un string al texto de un TextView
     */
    fun concatenarTextoATextView(texto: TextView, string: String){
        texto.text = "${texto.text}${string}"
    }
}