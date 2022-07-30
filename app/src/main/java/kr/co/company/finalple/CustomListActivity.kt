package kr.co.company.finalple

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class CustomListActivity : AppCompatActivity() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var layout : LinearLayout

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list)

        dbManager = DBManager(this, "ediyaMenuDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        layout= findViewById(R.id.customList)
        //layout= findViewById(R.id.customlist_card)
        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM ediyaMenuDB", null) // ediyaMenuDB의 모든 데이터

        var num: Int = 0
        while(cursor.moveToNext()) {

            // 현재 커서에 있는 값 변수로 받기
            var str_customMenuName = cursor.getString(cursor.getColumnIndex("customMenuName")).toString()
            var str_existingMenuName = cursor.getString(cursor.getColumnIndex("existingMenuName")).toString()
            var str_size = cursor.getString(cursor.getColumnIndex("size")).toString()
            var str_tapiocaPearl = cursor.getString(cursor.getColumnIndex("tapiocaPearl")).toString()
            var str_toppingSauce = cursor.getString(cursor.getColumnIndex("toppingSauce")).toString()
            var str_topping = cursor.getString(cursor.getColumnIndex("topping")).toString()
            var str_price = cursor.getString(cursor.getColumnIndex("price")).toString()
            var str_espressoShotNumber = cursor.getString(cursor.getColumnIndex("espressoShotNumber")).toString()
            var str_hazelnutsSyrupNumber = cursor.getString(cursor.getColumnIndex("hazelnutsSyrupNumber")).toString()
            var str_caramelSyrupNumber = cursor.getString(cursor.getColumnIndex("caramelSyrupNumber")).toString()
            var str_vanillaSyrupNumber = cursor.getString(cursor.getColumnIndex("vanillaSyrupNumber")).toString()
            var str_irishSyrupNumber = cursor.getString(cursor.getColumnIndex("irishSyrupNumber")).toString()
            var str_cafeSyrupNumber = cursor.getString(cursor.getColumnIndex("cafeSyrupNumber")).toString()

            //textView 가 들어있는 layout
            var layout_item:LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num    // 다른 레이아웃과 구분하기 위함(id: 0, 1, 2, ....)


            // textView 속성 설정 - 커스텀 메뉴 명칭
            var tvCustomMenuName : TextView = TextView(this)
            tvCustomMenuName.text = str_customMenuName
            tvCustomMenuName.textSize = 30f
            //tvCustomMenuName.textStyle="bold"
            layout_item.addView(tvCustomMenuName)// 레이아웃에 추가

            // textView 속성 설정 - 베이스(기존) 메뉴 명칭
            var tvExistingMenuName : TextView = TextView(this)
            tvExistingMenuName.text = str_existingMenuName
            tvExistingMenuName.textSize = 30f
            layout_item.addView(tvExistingMenuName)// 레이아웃에 추가

            // textView 속성 설정 - 가격
            var tvPrice : TextView = TextView(this)
            tvPrice.text = str_price
            tvPrice.textSize = 30f
            layout_item.addView(tvPrice)// 레이아웃에 추가

//
//            //시도
//            // textView 속성 설정 - 커스텀 메뉴 명칭
//            var list_item_name_textView : TextView
//            list_item_name_textView = findViewById(R.id.list_item_name_textView)
//            list_item_name_textView.text = str_customMenuName
//            layout_item.addView(list_item_name_textView)// 레 이아웃에 추가
//            // textView 속성 설정 - 베이스(기존) 메뉴 명칭
//            var list_item_brand_textView : TextView
//            list_item_brand_textView = findViewById(R.id.list_item_brand_textView)
//            list_item_brand_textView.text = str_existingMenuName
//            layout_item.addView(list_item_brand_textView)// 레이아웃에 추가
//            // textView 속성 설정 - 가격
//            var list_item_prise_textView : TextView
//            list_item_prise_textView = findViewById(R.id.list_item_prise_textView)
//            list_item_prise_textView.text = str_price
//            layout_item.addView(list_item_prise_textView)// 레이아웃에 추가


            // 레이아웃 클릭 시
            layout_item.setOnClickListener {
                // 상세 정보로 이동
                val intent = Intent(this,EdiyaCustomMenuShowActivity::class.java)
                intent.putExtra("intent_name", str_customMenuName)
                startActivity(intent)
            }

            // while문에서 생성한 내용들 layout에 넣기
            layout.addView(layout_item)
            num++
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

    }
}
