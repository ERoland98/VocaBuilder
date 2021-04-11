package hu.bme.aut.android.vocabuilder.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.Entry
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.EntryViewModel
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var entryViewModel: EntryViewModel
    private var currentPosition: Int = 1

    private var checked: Boolean = false
    private var correctAnswers: Int = 0
    private var userName: String? = null

    private var entryList = emptyList<Entry>()
    private var questionsList= ArrayList<Entry>()

    private var correctAnswer: String? = null
    private var questionWord: String? = null
    private var questionLanguage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        userName = intent.getStringExtra(QuizBuilder.USER_NAME)

        // Defining ViewModels
        entryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        entryViewModel.readAllData.observe(this, {
                entry -> entryList = entry
            
                // Get enough time to load the data
                getQuestions()
                setQuestion()
        })

        btn_submit.setOnClickListener(this)
    }

    /*
     Creating the 10 questions
     */
    private fun getQuestions() {
        val list  = IntArray(10) { Random.nextInt(entryList.size) }
        for (x in 0 until 10) {
            val entry = entryList[list[x]]
            questionsList.add(entry)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_submit -> {
                if (checked) {
                    currentPosition++
                    when {
                        currentPosition <= questionsList.size -> {
                            setQuestion()
                            resultTextView.text = ""
                            given_answer.text.clear()
                            checked = false
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(QuizBuilder.USER_NAME, userName)
                            intent.putExtra(QuizBuilder.CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(QuizBuilder.TOTAL_QUESTIONS, questionsList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val answer = given_answer.text.toString().capitalize(Locale.getDefault())
                    questionTextView.text = ""
                    resultTextView.text = "Answer: $correctAnswer\nYour answer: $answer"


                    if (correctAnswer.equals(answer)) {
                        correctAnswers++
                        answerView(R.drawable.correct_answer)
                    } else {
                        answerView(R.drawable.incorrect_answer)
                    }

                    if (currentPosition == questionsList.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }
                    checked = true
                }
            }
        }
    }

    private fun setQuestion() {
        answerView(R.drawable.none_answer)
        val entry = questionsList[currentPosition - 1]

        if (currentPosition == questionsList.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }

        val random = Random.nextInt(0, 100)
        if (random % 2 == 0) {
            correctAnswer = entry.word_1
            questionWord = entry.word_2
            questionLanguage = entry.language_1
        } else {
            correctAnswer = entry.word_2
            questionWord = entry.word_1
            questionLanguage = entry.language_2
        }

        progressBar.progress = currentPosition
        tv_progress.text = "$currentPosition" + "/" + progressBar.max

        questionTextView.text = "What is '$questionWord' in $questionLanguage?"
    }

    private fun answerView(drawableView: Int) {
        btn_submit.background = ContextCompat.getDrawable(
                    this,
                    drawableView
        )
    }
}