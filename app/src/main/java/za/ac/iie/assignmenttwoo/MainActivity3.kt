package za.ac.iie.assignmenttwoo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.content.Intent

class MainActivity3 : AppCompatActivity() {
    private lateinit var txtTotalScore: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var btnReview: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        txtTotalScore = findViewById(R.id.txtTotalScore)
        txtFeedback = findViewById(R.id.txtFeedback)
        btnReview = findViewById(R.id.btnReview)

        val score = intent.getIntExtra("SCORE", 0)
        val questions = intent.getStringArrayListExtra("QUESTIONS")
        val answers = intent.getBooleanArrayExtra("ANSWERS")

        // Display total score
        txtTotalScore.text = "Your Score: $score/${questions?.size ?: 5}"



        // Display feedback based on total score
        if (score >= 3) {
            txtFeedback.text = "Great job!"
            txtFeedback.setTextColor(resources.getColor(android.R.color.holo_blue_dark, theme))
        } else {
            txtFeedback.text = "Keep practising!"
            txtFeedback.setTextColor(resources.getColor(android.R.color.holo_blue_dark, theme))
        }

        // Review button
        btnReview.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                startActivity(intent)
            }
        }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

    private fun putBooleanArrayExtra(s: String, answers: BooleanArray) {
        TODO("Not yet implemented")
    }
}
