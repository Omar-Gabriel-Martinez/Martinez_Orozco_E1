package com.martinez_orozco.martinez_orozco_e1quizapp2

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val preguntas = listOf(
        Pair(R.string.question1, Pair(R.string.answer1_true, R.string.answer1_false)),
        Pair(R.string.question2, Pair(R.string.answer2_true, R.string.answer2_false)),
        Pair(R.string.question3, Pair(R.string.answer3_true, R.string.answer3_false))
    )

    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val btnVerdadero = findViewById<Button>(R.id.btnVerdadero)
        val btnFalso = findViewById<Button>(R.id.btnFalso)
        val btnNext = Button(this).apply {
            text = getString(R.string.next_button)
            layoutParams = btnVerdadero.layoutParams
        }

        val layout = findViewById<LinearLayout>(R.id.main)
        layout.addView(btnNext)

        // Función para actualizar la pregunta
        fun updateQuestion() {
            val (questionResId, _) = preguntas[currentQuestionIndex]
            textView.setText(questionResId)
        }

        // Eventos de clic
        btnVerdadero.setOnClickListener {
            val (_, answers) = preguntas[currentQuestionIndex]
            Toast.makeText(this, getString(answers.first), Toast.LENGTH_SHORT).show()
        }

        btnFalso.setOnClickListener {
            val (_, answers) = preguntas[currentQuestionIndex]
            Toast.makeText(this, getString(answers.second), Toast.LENGTH_SHORT).show()
        }

        btnNext.setOnClickListener {
            if (currentQuestionIndex < preguntas.size - 1) {
                currentQuestionIndex++
                updateQuestion()
            } else {
                Toast.makeText(this, "¡Has terminado el quiz!", Toast.LENGTH_SHORT).show()
            }
        }

        // Mostrar la primera pregunta
        updateQuestion()
    }
}
