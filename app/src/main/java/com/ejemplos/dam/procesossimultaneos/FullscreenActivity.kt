package com.ejemplos.dam.procesossimultaneos

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fullscreen.*
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.contentView
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Ejemplo de tareas en segundo plano
 */
class FullscreenActivity : AppCompatActivity() {
    // dispatches execution onto the Android main UI thread
    private val uiContext: CoroutineContext = UI
    private val contextoActual : CoroutineContext = CommonPool
    private var contador=0

    companion object {


    }

    fun getCount(): Int {
        return contador
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        // evento click en el círculo
        circulo.setOnClickListener {
            animarCirculo()
            contador++
        }
        // evento click en el triangulo
        triangulo.setOnClickListener {
            animarTriangulo()
            contador++
        }
        // evento click en el cuadrado
        cuadrado.setOnClickListener {
            animarCuadrado()
            contador++
        }

        ciudad.setOnClickListener{
            animarCiudad()
            contador++
        }
    }
    // factor de escala
    var k = 0.5
    // para cambiar los sentidos de las animaciones
    var j : Int = 1
    // tarea en segundo plano del cuadrado
    private fun animarCuadrado() = launch(uiContext) {
            // aumentamos o disminuimos alternadamente
            if (k == 1.5) k = 0.5 else k = 1.5
            // animacion: escalamos en la direccion X
            val objectAnimator = ObjectAnimator.ofFloat(
                    cuadrado,
                    "scaleX",
                    k.toFloat())
            objectAnimator.duration = 1000L
            objectAnimator.interpolator
            objectAnimator.start()
    }
    // tarea en segundo plano del triangulo
    private fun animarTriangulo() = launch(uiContext) {
        // izquierda o derecha alternadamente
        j *= -1
        // animacion: trasladamos en la direccion X
        val objectAnimator = ObjectAnimator.ofFloat(
                triangulo,
                "translationX",
                100f * j)
        objectAnimator.duration = 3000L
        objectAnimator.interpolator
        objectAnimator.start()
    }
    // tarea en segundo plano del circulo
    private fun animarCirculo() = launch(uiContext) {
        //arriba o abajo alternadamente
        j *= -1
        // animacion: trasladamos en la direccion Y
        val objectAnimator = ObjectAnimator.ofFloat(
                circulo,
                "translationY",
                300f * j)
        objectAnimator.duration = 5000L
        objectAnimator.interpolator
        objectAnimator.start()
    }

    var escala=1

    private fun animarCiudad() = launch(uiContext) {
        // aumentamos o disminuimos alternadamente
        if (escala == 1) escala = 3 else escala = 1
        // animacion: escalamos en la direccion X
        val objectAnimator = ObjectAnimator.ofFloat(
                ciudad,
                "scaleY",
                escala.toFloat())
        objectAnimator.duration = 3000L
        objectAnimator.interpolator

        val objectAnimator2 = ObjectAnimator.ofFloat(
                ciudad,
                "scaleX",
                escala.toFloat())
        objectAnimator2.duration = 3000L
        objectAnimator2.interpolator

        val objectAnimator3 = ObjectAnimator.ofFloat(
                ciudad,
                "rotation",
                0f,
                360f)
        objectAnimator3.duration = 3000L
        objectAnimator3.interpolator

        objectAnimator3.start()
        objectAnimator2.start()
        objectAnimator.start()

        delay(2750)

        transitionToActivity2()
    }

    private fun transitionToActivity2(){
        val secActivity = Intent(this@FullscreenActivity, SecondaryActivity::class.java)
        startActivity(secActivity)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}
