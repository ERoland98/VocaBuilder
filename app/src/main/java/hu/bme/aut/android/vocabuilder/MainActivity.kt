package hu.bme.aut.android.vocabuilder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.EntryViewModel
import hu.bme.aut.android.vocabuilder.dictionary.Add
import hu.bme.aut.android.vocabuilder.dictionary.Load
import hu.bme.aut.android.vocabuilder.info.InfoActivity
import hu.bme.aut.android.vocabuilder.quiz.QuizCreatorActivity
import hu.bme.aut.android.vocabuilder.result.ResultInfoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var entryViewModel: EntryViewModel
    private var numberOEntriesDictionary: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Defining ViewModels
        entryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        entryViewModel.readAllData.observe(this, {
            numberOEntriesDictionary = entryViewModel.readAllData.value?.size!!
        })

        cardAdd.setOnClickListener {
            try {
                startActivity(Intent(this, Add::class.java))
            }
            catch (exception: Exception) {
                Snackbar.make(
                    it,
                    "Unable to launch this activity. Try again!",
                    Snackbar.LENGTH_LONG
                ).show()
                Log.e("cardAdd", "exception", exception)
            }
        }

        cardHome.setOnClickListener {
            try {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            catch (exception: Exception) {
                Snackbar.make(
                    it,
                    "Unable to launch this activity. Try again!",
                    Snackbar.LENGTH_LONG
                ).show()
                Log.e("cardHome", "exception", exception)
            }
        }

        cardInfo.setOnClickListener {
            try {
                startActivity(Intent(this, InfoActivity::class.java))
            }
            catch (exception: Exception) {
                Snackbar.make(
                    it,
                    "Unable to launch this activity. Try again!",
                    Snackbar.LENGTH_LONG
                ).show()
                Log.e("cardInfo", "exception", exception)
            }
        }

        cardLoad.setOnClickListener {
            try {
                startActivity(Intent(this, Load::class.java))
            }
            catch (exception: Exception) {
                Snackbar.make(it, exception.toString(), Snackbar.LENGTH_LONG).show()
                Log.e("cardLoad", "exception", exception)
            }
        }

        cardResults.setOnClickListener {
            try {
                startActivity(Intent(this, ResultInfoActivity::class.java))
            }
            catch (exception: Exception) {
                Snackbar.make(
                    it,
                    "Unable to launch this activity. Try again!",
                    Snackbar.LENGTH_LONG
                ).show()
                Log.e("cardResults", "exception", exception)
            }
        }

        cardQuiz.setOnClickListener {
            try {
                if (numberOEntriesDictionary > 9) {
                    startActivity(Intent(this, QuizCreatorActivity::class.java))
                } else {
                    Snackbar.make(
                        it,
                        "Please add more than 10 words to your dictionary!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            } catch (exception: Exception) {
                Snackbar.make(
                    it,
                    "Unable to launch this activity. Try again!",
                    Snackbar.LENGTH_LONG
                ).show()
                Log.e("cardQuiz", "exception", exception)
            }
        }
    }
}