package kr.co.company.finalple

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class EdiyaCustomMenuShowActivity : AppCompatActivity() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var custom_name : TextView
    lateinit var custom_basemenu : TextView
    lateinit var custom_prise : TextView
    lateinit var custom_size : TextView
    lateinit var custom_shotnum : TextView
    lateinit var custom_pearl1 : TextView
    lateinit var custom_hazelnutsyrupnum : TextView
    lateinit var custom_caramelsyrupnum : TextView
    lateinit var custom_vanillasyrupnum : TextView
    lateinit var custom_irishsyrupnum : TextView
    lateinit var custom_cafesyrupnum : TextView
    lateinit var custom_toppingsauces : TextView
    lateinit var custom_toppings : TextView


    lateinit var login_button : TextView


    // db에서 조회 정보 받아서 textview에 전달하기 전 중간 역할 변수
    lateinit var str_customMenuName : String
    lateinit var str_existingMenuName : String
    lateinit var str_price : String
    lateinit var str_size : String
    lateinit var str_espressoShotNumber : String
    lateinit var str_tapiocaPearl : String
    lateinit var str_hazelnutsSyrupNumber : String
    lateinit var str_caramelSyrupNumber : String
    lateinit var str_vanillaSyrupNumber : String
    lateinit var str_irishSyrupNumber : String
    lateinit var str_cafeSyrupNumber :String
    lateinit var str_toppingSauce : String
    lateinit var str_topping : String

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ediya_custom_menu_show)


        custom_name = findViewById(R.id.custom_name)
        custom_basemenu = findViewById(R.id.custom_basemenu)
        custom_prise = findViewById(R.id.custom_prise)
        custom_size = findViewById(R.id.custom_size)
        custom_shotnum = findViewById(R.id.custom_shotnum)
        custom_pearl1 = findViewById(R.id.custom_pearl1)
        custom_hazelnutsyrupnum = findViewById(R.id.custom_hazelnutsyrupnum)
        custom_caramelsyrupnum = findViewById(R.id.custom_caramelsyrupnum)
        custom_vanillasyrupnum = findViewById(R.id.custom_vanillasyrupnum)
        custom_irishsyrupnum = findViewById(R.id.custom_irishsyrupnum)
        custom_cafesyrupnum = findViewById(R.id.custom_cafesyrupnum)
        custom_toppingsauces = findViewById(R.id.custom_toppingsauces)
        custom_toppings = findViewById(R.id.custom_toppings)


        login_button = findViewById(R.id.login_button)

        // 리스트 목록으로 화면 전환
        login_button.setOnClickListener{
            val intent = Intent(this, CustomListActivity::class.java)
            startActivity(intent)
        }



        //초기화
        // MainActivity에서 전달한 intent 받기
        val intent = intent
        str_customMenuName = intent.getStringExtra("intent_name").toString()

        // db 객체 받기
        dbManager = DBManager(this, "ediyaMenuDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        // 결과 얻기
        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM ediyaMenuDB WHERE customMenuName = '"+ str_customMenuName +"';", null)

        if(cursor.moveToNext()) {

            //str_customMenuName = cursor.getString(cursor.getColumnIndex("customMenuName")).toString()



            // 컬럼값으로 인덱스값 알아오기
            str_existingMenuName = cursor.getString(cursor.getColumnIndex("existingMenuName")).toString()
            str_size = cursor.getString(cursor.getColumnIndex("size")).toString()
            str_tapiocaPearl = cursor.getString(cursor.getColumnIndex("tapiocaPearl")).toString()
            str_toppingSauce = cursor.getString(cursor.getColumnIndex("toppingSauce")).toString()
            str_topping = cursor.getString(cursor.getColumnIndex("topping")).toString()

            str_price = cursor.getString(cursor.getColumnIndex("price")).toString()

            str_espressoShotNumber = cursor.getString(cursor.getColumnIndex("espressoShotNumber")).toString()

            str_hazelnutsSyrupNumber = cursor.getString(cursor.getColumnIndex("hazelnutsSyrupNumber")).toString()

            str_caramelSyrupNumber = cursor.getString(cursor.getColumnIndex("caramelSyrupNumber")).toString()

            str_vanillaSyrupNumber = cursor.getString(cursor.getColumnIndex("vanillaSyrupNumber")).toString()

            str_irishSyrupNumber = cursor.getString(cursor.getColumnIndex("irishSyrupNumber")).toString()

            str_cafeSyrupNumber = cursor.getString(cursor.getColumnIndex("cafeSyrupNumber")).toString()
        }
        cursor.close()
        sqlitedb.close()
        dbManager.close()

        // textView에 값 넣기
        custom_name.text = str_customMenuName
        custom_basemenu.text = str_existingMenuName

        custom_prise.text = str_price
        custom_size.text = str_size

        custom_shotnum.text = str_espressoShotNumber
        custom_pearl1.text = str_tapiocaPearl
        custom_hazelnutsyrupnum.text = str_hazelnutsSyrupNumber
        custom_caramelsyrupnum.text = str_caramelSyrupNumber
        custom_vanillasyrupnum.text = str_vanillaSyrupNumber
        custom_irishsyrupnum.text = str_irishSyrupNumber
        custom_cafesyrupnum.text =str_cafeSyrupNumber
        custom_toppingsauces.text = str_toppingSauce
        custom_toppings.text = str_topping + "\n" // 줄바꿈


    }
}