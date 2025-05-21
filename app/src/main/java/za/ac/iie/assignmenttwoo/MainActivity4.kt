package za.ac.iie.assignmenttwoo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
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
        val reviewLayout = findViewById<LinearLayout>(R.id.reviewLayout)


        // Build a string to display all questions and their correct answers
        if (questions != null && answers != null) {
            for (i in questions.indices) {
                val questionText = TextView(this)
                questionText.text = "${i + 1}. ${questions[i]} - ${if (answers[i]) "Correct" else "Incorrect"}"
                questionText.setTextColor(
                    resources.getColor(
                        if (answers[i]) android.R.color.holo_green_dark else android.R.color.holo_red_dark,
                        theme
                    )
                )
                questionText.textSize = 18f
                questionText.setPadding(16, 16, 16, 16)
                reviewLayout.addView(questionText)
            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}