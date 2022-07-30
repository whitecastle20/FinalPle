package kr.co.company.finalple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationBarView.*
import com.google.android.material.navigation.NavigationView

class TestActivity : AppCompatActivity() , OnNavigationItemReselectedListener {

    lateinit var bottom_navigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        bottom_navigation = findViewById(R.id.bottom_navigation)

        // 하단바 변수 실행
       // bottom_navigation?.setOnItemSelectedListener (this)
        bottom_navigation.setOnNavigationItemReselectedListener(this)
    }



//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            // 홈 메뉴 클릭 시, 홈 메뉴로
//            R.id.action_home -> {
//                val intent = Intent(this,HomeActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.action_myList -> {
//                val intent = Intent(this,MyListActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.action_customList -> {
//                val intent = Intent(this,CustomListActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//        }
//        return false
//    }

    override fun onNavigationItemReselected(item: MenuItem) {
        when(item.itemId) {
            // 홈 메뉴 클릭 시, 홈 메뉴로
            R.id.action_home -> {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                //return true
            }
            R.id.action_myList -> {
                val intent = Intent(this,MyListActivity::class.java)
                startActivity(intent)
                //return true
            }
            R.id.action_customList -> {
                val intent = Intent(this,CustomListActivity::class.java)
                startActivity(intent)
                //return true
            }
        }
        //return false
    }
//    // 하단바 메뉴
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.bottom_navigation_main, menu)
//        return true
//    }
//
//    // 하단바 선택됐을 때
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item?.itemId){
//            // 홈 메뉴 클릭 시, 홈 메뉴로
//            R.id.action_home -> {
//                val intent = Intent(this,HomeActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.action_myList -> {
//                val intent = Intent(this,MyListActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.action_customList -> {
//                val intent = Intent(this,CustomListActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }




}

