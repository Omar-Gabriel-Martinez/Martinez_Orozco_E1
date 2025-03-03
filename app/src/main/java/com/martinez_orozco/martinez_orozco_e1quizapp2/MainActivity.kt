package com.martinez_orozco.martinez_orozco_e1quizapp2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.martinez_orozco.martinez_orozco_e1quizapp2.R

class MainActivity : AppCompatActivity() {

    private val preguntas = listOf(
        Triple(R.string.question1, R.string.answer1_true, R.string.answer1_false),
        Triple(R.string.question2, R.string.answer2_true, R.string.answer2_false),
        Triple(R.string.question3, R.string.answer3_true, R.string.answer3_false)
    )

    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val btnVerdadero = findViewById<Button>(R.id.btnVerdadero)
        val btnFalso = findViewById<Button>(R.id.btnFalso)
        val btnNext = findViewById<Button>(R.id.btnNext)

        // Función para actualizar la pregunta
        fun updateQuestion() {
            if (currentQuestionIndex < preguntas.size) {
                val (questionResId, _, _) = preguntas[currentQuestionIndex]
                textView.setText(questionResId)
                btnNext.visibility = if (currentQuestionIndex == preguntas.size - 1) Button.GONE else Button.VISIBLE
            }
        }

        // Evento de clic en Verdadero
        btnVerdadero.setOnClickListener {
            val (_, trueAnswer, _) = preguntas[currentQuestionIndex]
            Toast.makeText(this, getString(trueAnswer), Toast.LENGTH_SHORT).show()
        }

        // Evento de clic en Falso
        btnFalso.setOnClickListener {
            val (_, _, falseAnswer) = preguntas[currentQuestionIndex]
            Toast.makeText(this, getString(falseAnswer), Toast.LENGTH_SHORT).show()
        }

        // Botón "Siguiente" para cambiar de pregunta
        btnNext.setOnClickListener {
            if (currentQuestionIndex < preguntas.size - 1) {
                currentQuestionIndex++
                updateQuestion()
            } else {
                Toast.makeText(this, "¡Has terminado el quiz!", Toast.LENGTH_SHORT).show()
            }
        }

        // Mostrar la primera pregunta al iniciar
        updateQuestion()
    }
}
