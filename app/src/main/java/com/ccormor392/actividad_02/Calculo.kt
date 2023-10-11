package com.ccormor392.actividad_02

class Calculo {
    var num1 = 0f
    var num2 = 0f
    var operacion = ""
    var resultado = 0f
    var esPrimeraOperacion = true
    fun sumar(): Float {
        return num1 + num2
    }
    fun multiplicar(): Float {
        return num1 * num2
    }
    fun dividir(): Float {
        return num1 / num2
    }
    fun restar(): Float {
        return num1 - num2
    }
    fun operar(){
        when(operacion){
            "÷" -> resultado = dividir()
            "+" -> resultado = sumar()
            "-" -> resultado = restar()
            "×" -> resultado = multiplicar()
        }
    }
    fun reset(){
        num1 = 0f
        num2 = 0f
        operacion = ""
        resultado = 0f
        esPrimeraOperacion = true
    }
    fun operacionIncompleta():Boolean{
        return (num2 == 0f && operacion == "")
    }

}