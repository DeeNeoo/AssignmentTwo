package za.ac.iie.assignmenttwoo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.TextView
import android.widget.Button
import android.os.Handler
import android.os.Looper


class MainActivity2 : AppCompatActivity() {
    private lateinit var txtQuestions: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    private val questions = arrayOf(
        "Nelson Mandela was the first black president of South Africa.",
        "Nelson Mandela was released from prison in 1915 after serving for 50 years.",
        "Nelson Mandela's middle name is Rolihlahla.",
        "Apartheid is a system of racial segregation and discrimination enforced by the white minority government of South Africa from 1948 to 1994.",
        "South Africans were classified into racial groups based on their perceived races."
    )

    private val answers = booleanArrayOf(
        true,
        false,
        true,
        true,
        true
    )

    private var score = 0
    private var currentQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        // Bind views
        txtQuestions = findViewById(R.id.txtQuestions)
        txtFeedback = findViewById(R.id.txtFeedback2)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)

        // Set click listeners
        btnTrue.setOnClickListener { checkAnswer(true) }
        btnFalse.setOnClickListener { checkAnswer(false) }

        // Kick off the first question
        displayQuestion()

        btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)

        }


        // Edge-to-edge insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

private fun displayQuestion() {
    if (currentQuestion < questions.size) {
        txtQuestions.text  = questions[currentQuestion]
        txtFeedback.text   = ""
        btnTrue.isEnabled  = true
        btnFalse.isEnabled = true
    } else {
        showScoreScreen()
    }
}

private fun checkAnswer(userAnswer: Boolean) {
    // Check answer and update feedback + score
    if (userAnswer == answers[currentQuestion]) {
        txtFeedback.setTextColor(resources.getColor(android.R.color.holo_green_dark, theme))
        txtFeedback.text = "Correct!"
        score++
    } else {
        txtFeedback.setTextColor(resources.getColor(android.R.color.holo_red_dark, theme))
        txtFeedback.text = "Incorrect!"
    }

    // Prevent double-tapping
    btnTrue.isEnabled  = false
    btnFalse.isEnabled = false

    // Move to next question after a short delay
    Handler(Looper.getMainLooper()).postDelayed({
        currentQuestion++
        displayQuestion()
    }, 1500)
}

private fun showScoreScreen() {
    val intent = Intent(this, MainActivity3::class.java).apply {
        putExtra("SCORE", score)
        putStringArrayListExtra("QUESTIONS", ArrayList(questions.toList()))
        putExtra("ANSWERS", answers)
    }
    startActivity(intent)
    finish()
}
}

