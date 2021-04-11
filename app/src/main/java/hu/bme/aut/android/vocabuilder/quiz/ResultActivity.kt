package hu.bme.aut.android.vocabuilder.quiz

import android.content.Intent
import android.os.Bundle
import hu.bme.aut.android.vocabuilder.database.result.Result
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.vocabuilder.MainActivity
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.database.result.ResultViewModel
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private lateinit var resulViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(QuizBuilder.USER_NAME)
        tv_name.text = userName

        val totalQuestions = intent.getIntExtra(QuizBuilder.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(QuizBuilder.CORRECT_ANSWERS, 0)
        val score = "$correctAnswers/$totalQuestions"
        tv_score.text = "Your Score is $correctAnswers out of $totalQuestions."

        // Defining ViewModel
        resulViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        val result = Result(0, userName, score)
        resulViewModel.addResult(result)

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}