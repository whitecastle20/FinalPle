package kr.co.company.finalple

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(
    context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int

) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        // 이디야 테이블 생성
        db!!.execSQL("CREATE TABLE ediyaMenuDB (customMenuName text, existingMenuName text, price text, size text, espressoShotNumber text, tapiocaPearl text, hazelnutsSyrupNumber text, caramelSyrupNumber text, vanillaSyrupNumber text, irishSyrupNumber text, cafeSyrupNumber text, toppingSauce text, topping text)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}