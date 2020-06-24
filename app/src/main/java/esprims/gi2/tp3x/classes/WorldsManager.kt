package esprims.gi2.tp3x.classes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt

class WorldsManager(context: Context) {
    private var motHelper: MotDbHelper = MotDbHelper(context)
    lateinit var db:SQLiteDatabase
     fun openReadDB(){
        db=  motHelper.readableDatabase
    }
      fun openWriteDB(){
        db = motHelper.writableDatabase
    }
    fun closeDB(){
        db.close()
    }
    fun addWorld(mot:Mot):Long {
        val contentValues = ContentValues()
        contentValues.put(MotDbHelper.COL_MOT,mot.leMot)
        contentValues.put(MotDbHelper.COL_GENRE,mot.genre)
        contentValues.put(MotDbHelper.COL_TYPE,mot.type)
        contentValues.put(MotDbHelper.COL_TAILLE,mot.taille)
        return motHelper.writableDatabase.insert(MotDbHelper.THE_TABLE,null, contentValues)
    }
    fun getWorld():List<Mot>{
        val motList:ArrayList<Mot> = ArrayList()
        val selectQuery = "SELECT * FROM "+MotDbHelper.THE_TABLE
        val cursor:Cursor?
        cursor = db.rawQuery(selectQuery,null)
        /*try {
            cursor = db.rawQuery(selectQuery,null)

        }catch (e:SQLException){
            db.execSQL(selectQuery)
            return ArrayList()
        }*/
        var mot_:String
        var taille:Int
        var type:String
        var genre:String
        if (cursor.moveToFirst()){
            do {
                mot_ = cursor.getString(cursor.getColumnIndex("leMot"))
                type = cursor.getString(cursor.getColumnIndex("type"))
                genre = cursor.getString(cursor.getColumnIndex("genre"))
                taille = cursor.getInt(cursor.getColumnIndex("taille"))
                val m = Mot(mot_,taille,type,genre)
                motList.add(m)

            }while (cursor.moveToNext())

        }
        return  motList
    }
    fun delete(mot: String){
        this.openWriteDB()
        db.delete(MotDbHelper.THE_TABLE,MotDbHelper.COL_MOT+"=?", arrayOf(mot))
        this.closeDB()

    }
    fun update(motNew: Mot,m:String): Int{
        val contentValues = ContentValues()
        contentValues.put(MotDbHelper.COL_MOT,motNew.leMot)
        contentValues.put(MotDbHelper.COL_TAILLE,motNew.taille)
        contentValues.put(MotDbHelper.COL_GENRE,motNew.genre)
        contentValues.put(MotDbHelper.COL_TYPE,motNew.type)

        return db.update(MotDbHelper.THE_TABLE,contentValues,MotDbHelper.COL_MOT+"=?", arrayOf(m))

    }
    val random = Random()
    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }
    fun phraseMasculin(): ArrayList<String> {

        this.openReadDB()

        val selectQuery = "SELECT * FROM "+MotDbHelper.THE_TABLE


        var m:String
        var adj:String
        val cursor:Cursor?
        val cursor2:Cursor?
        val list1:ArrayList<String> = ArrayList()
        val list2:ArrayList<String> = ArrayList()
        cursor = db.rawQuery(selectQuery+" WHERE "+ MotDbHelper.COL_GENRE +"= 'masculin' and "
                +MotDbHelper.COL_TYPE + "= 'nom' ORDER BY RANDOM() LIMIT 1 ",null)
        cursor2 = db.rawQuery(selectQuery+" WHERE "+ MotDbHelper.COL_TYPE +"= 'Adjectif' and "
                + MotDbHelper.COL_GENRE +"= 'masculin' ORDER BY RANDOM() LIMIT 1",null)

        //m= cursor.getString(cursor.getColumnIndex("leMot"))
        //adj= cursor2.getString(cursor.getColumnIndex("leMot"))

               if (cursor.moveToFirst()){
                   do{
                       m = cursor.getString(cursor.getColumnIndex("leMot"))
                       list1.add(m)
                   }while(cursor.moveToNext())

               }
        if (cursor2.moveToFirst()){
            do{
                adj = cursor2.getString(cursor2.getColumnIndex("leMot"))
                list1.add(adj)

            }while(cursor2.moveToNext())

        }


        this.closeDB()
        return list1
    }
    fun phraseFeminin(): ArrayList<String> {

        this.openReadDB()

        val selectQuery = "SELECT * FROM "+MotDbHelper.THE_TABLE


        var m:String
        var adj:String
        val cursor:Cursor?
        val cursor2:Cursor?
        val list1:ArrayList<String> = ArrayList()
        val list2:ArrayList<String> = ArrayList()
        cursor = db.rawQuery(selectQuery+" WHERE "+ MotDbHelper.COL_GENRE +"= 'feminin' and "
                +MotDbHelper.COL_TYPE + "= 'nom' ORDER BY RANDOM() LIMIT 1 ",null)
        cursor2 = db.rawQuery(selectQuery+" WHERE "+ MotDbHelper.COL_TYPE +"= 'Adjectif' and "
                + MotDbHelper.COL_GENRE +"= 'feminin' ORDER BY RANDOM() LIMIT 1",null)

        //m= cursor.getString(cursor.getColumnIndex("leMot"))
        //adj= cursor2.getString(cursor.getColumnIndex("leMot"))

        if (cursor.moveToFirst()){
            do{
                m = cursor.getString(cursor.getColumnIndex("leMot"))
                list1.add(m)
            }while(cursor.moveToNext())

        }
        if (cursor2.moveToFirst()){
            do{
                adj = cursor2.getString(cursor2.getColumnIndex("leMot"))
                list1.add(adj)

            }while(cursor2.moveToNext())

        }


        this.closeDB()
        return list1
    }




}