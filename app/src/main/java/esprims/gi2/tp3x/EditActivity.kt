package esprims.gi2.tp3x

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import esprims.gi2.tp3x.classes.Mot
import esprims.gi2.tp3x.classes.WorldsManager
import kotlinx.android.synthetic.main.activity_edit.*


class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

            val world :WorldsManager= WorldsManager(this)

            val oldMot = intent.getStringExtra("mot")
            val oldgenre = intent.getStringExtra("genre")
            val oldtype= intent.getStringExtra("type")
            val oldtaille = intent.getStringExtra("taille")

            motId.setText(oldMot)
            genreId.setText(oldgenre)
            typeId.setText(oldtype)
            tailleId.setText(oldtaille)

        btn_update.setOnClickListener {

            val newmot: Mot = Mot(motId.text.toString(),tailleId.text.toString().toInt(),
                typeId.text.toString(),genreId.text.toString())
            world.openWriteDB()
            world.update(newmot, oldMot)

            setResult(Activity.RESULT_OK)
            finish()
        }



    }
}