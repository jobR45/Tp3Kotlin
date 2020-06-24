package esprims.gi2.tp3x

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import esprims.gi2.tp3x.classes.Mot
import esprims.gi2.tp3x.classes.WorldsManager
import kotlinx.android.synthetic.main.activity_add_world.*

class AddWorldActivity : AppCompatActivity() {
    var type:String="something"
    var genre:String="something2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_world)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            if (checkedId==R.id.nomId)
                type= "nom"
            else if (checkedId==R.id.adjectifId)
                type="Adjectif"
        }
        radioGroup2.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId==R.id.masuclinId)
                genre= "masculin"
            else if (checkedId == R.id.femininId)
                genre="feminin"

        }

        btn_register.setOnClickListener {
            val world:WorldsManager = WorldsManager(this)
            val name:Mot = Mot(mot.text.toString(), tailleId.text.toString().toInt(),type,genre)
            world.openWriteDB()
            world.addWorld(name)
            setResult(Activity.RESULT_OK)
            finish()
       }

    }

}


