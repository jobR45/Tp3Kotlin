package esprims.gi2.tp3x

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import esprims.gi2.tp3x.classes.Mot
import esprims.gi2.tp3x.classes.WorldsManager
import kotlinx.android.synthetic.main.activity_compute.*

class ComputeActivity : AppCompatActivity() {
    val world : WorldsManager = WorldsManager(this)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compute)




        btn_masc.setOnClickListener {
           // val l:ArrayList<String> = ArrayList()
            val masc = world.phraseMasculin()

            phraseId.setText("Le "+masc[0]+" est "+masc[1])
        }
        btn_fem.setOnClickListener {
            val fem=world.phraseFeminin()
            phraseId.setText("La "+fem[0]+" est "+fem[1])
        }

    }
}