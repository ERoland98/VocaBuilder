package hu.bme.aut.android.vocabuilder.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.fragments.ResultInfoFragment

class ResultInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultinfo)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ResultInfoFragment>(R.id.fragment1)
        }
    }
}