package hu.bme.aut.android.vocabuilder.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.vocabuilder.R
import kotlinx.android.synthetic.main.activity_quizcreator.*

class QuizCreatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizcreator)

        btn_start.setOnClickListener {
            if (username.text.toString().isEmpty()) {
                Snackbar.make(it, "Please enter your name", Snackbar.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra(QuizBuilder.USER_NAME, username.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}
