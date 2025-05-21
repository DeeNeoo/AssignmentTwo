package za.ac.iie.assignmenttwoo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)

        val txtReviews = findViewById<TextView>(R.id.txtReviews)

        val questions = intent.getStringArrayListExtra("QUESTIONS")
        val answers = intent.getBooleanArrayExtra("ANSWERS")

        // Build a string to display all questions and their correct answers
        if (questions != null && answers != null && questions.size == answers.size) {
            val reviewContent = StringBuilder()
            for (i in questions.indices) { // Loop through the questions and answers
                reviewContent.append("Q${i + 1}: ${questions[i]}\n")
                reviewContent.append("Correct Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            txtReviews.text = reviewContent.toString()
        } else {
            txtReviews.text = "Error: Review data not loaded."
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}