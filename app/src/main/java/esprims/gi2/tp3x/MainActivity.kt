package esprims.gi2.tp3x

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import esprims.gi2.tp3x.classes.Mot
import esprims.gi2.tp3x.classes.WorldsManager
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val world: WorldsManager = WorldsManager(this)
    var list : List<Mot> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            list =this.refresh()
            registerForContextMenu(wordsList)

            btn_add.setOnClickListener {
            intent = Intent(applicationContext,AddWorldActivity::class.java)
            startActivityForResult(intent,1)
            }
            btn_phrase.setOnClickListener {
                intent = Intent(applicationContext,ComputeActivity::class.java)
                /*list=this.refresh()
                val i: Int = Random.nextInt(list.lastIndex)
                intent.putExtra("theList",list[i].toString())*/
                startActivityForResult(intent,3)
            }

        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            1->
                when(requestCode){
                    Activity.RESULT_OK ->{
                        Toast.makeText(this, " word  Not added", Toast.LENGTH_LONG).show()

                        //list=this.refresh()
                    }
                else ->   {
                    Toast.makeText(this, " word added", Toast.LENGTH_LONG).show()
                    list=this.refresh()
                    }
                }
            2-> when(requestCode){
                Activity.RESULT_OK -> {
                    Toast.makeText(this, "Not Updated", Toast.LENGTH_LONG).show()
                }
                else ->{
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show()
                    list=this.refresh()
                }
            }
            }
    }
    private fun refresh(): List<Mot> {

        world.openReadDB()
        list=world.getWorld()
        wordsList.adapter = ArrayAdapter<Mot>(this,android.R.layout.simple_list_item_1,list)
        world.closeDB()
        return list
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(Menu.NONE,1,Menu.NONE,"Supprimer")
        menu?.add(Menu.NONE,2, Menu.NONE,"Editer")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1-> {
                val i = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val value = list[i.position]
                world.delete(value.leMot)
                list=this.refresh()
                Toast.makeText(this," item deleted ",Toast.LENGTH_LONG).show()
            }

            2->{
                val i = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val value = list[i.position]
                intent = Intent(applicationContext,EditActivity::class.java)
                intent.putExtra("mot",value.leMot)
                intent.putExtra("genre",value.genre)
                intent.putExtra("type",value.type)
                intent.putExtra("taille",value.taille.toString())
                startActivityForResult(intent,2)
            }
        }

        return super.onContextItemSelected(item)
    }
        
    }



