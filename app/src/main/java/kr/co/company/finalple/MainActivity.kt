package kr.co.company.finalple

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {


    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase


    lateinit var custom_name_EditText : EditText


    lateinit var custom_prise_EditText : EditText


    lateinit var sizeRadioGroup : RadioGroup
    lateinit var custom_size1_radioButton : RadioButton
    lateinit var custom_size2_radioButton : RadioButton


    // 증감버튼 동작을 위한 선언
    // 에스프레소 샷
    lateinit var custom_shotnum_sub_button : Button // 감소 버튼
    lateinit var custom_shotnum_textView : TextView // 개수
    lateinit var custom_shotnum_plus_button : Button //증가 버튼
    // 헤이즐넛 시럽
    lateinit var custom_hazelnutsyrupnum_sub_button : Button // 감소 버튼
    lateinit var custom_hazelnutsyrupnum_textView : TextView // 개수
    lateinit var custom_hazelnutsyrupnum_plus_button : Button //증가 버튼
    // 카라멜 시럽
    lateinit var custom_caramelsyrupnum_sub_button : Button // 감소 버튼
    lateinit var custom_caramelsyrupnum_plus_button : Button // 증가 버튼
    lateinit var custom_caramelsyrupnum_textView : TextView //개수
    //  바닐라 시럽
    lateinit var custom_vanillasyrupnum_sub_button : Button // 감소 버튼
    lateinit var custom_vanillasyrupnum_textView : TextView // 개수
    lateinit var custom_vanillasyrupnum_plus_button : Button //증가 버튼
    // 아이리쉬 시럽
    lateinit var custom_irishsyrupnum_sub_button : Button // 감소 버튼
    lateinit var custom_irishsyrupnum_textView : TextView // 개수
    lateinit var custom_irishsyrupnum_plus_button : Button //증가 버튼
    // 카페 시럽
    lateinit var custom_cafesyrupnum_sub_button : Button // 감소 버튼
    lateinit var custom_cafesyrupnum_textView : TextView // 개수
    lateinit var custom_cafesyrupnum_plus_button : Button //증가 버튼



    lateinit var custom_pearl1_checkBox : CheckBox

    lateinit var custom_toppingsauce1_checkBox : CheckBox
    lateinit var custom_toppingsauce2_checkBox : CheckBox

    lateinit var custom_topping1_checkBox : CheckBox
    lateinit var custom_topping2_checkBox : CheckBox
    lateinit var custom_topping3_checkBox : CheckBox

    lateinit var custom_basemenu_spinner : Spinner
    lateinit var login_button : Button

    // 0으로 시럽 개수 초기화 for 증감버튼
    var espressoShotNumber = 0      // 에스프레소 샷 개수를 위한 변수

    var hazelnutsSyrupNumber = 0    // 헤이즐넛 시럽을 위한 변수
    var caramelSyrupNumber = 0      //  카라멜 시럽 개수를 위한 변수
    var vanillaSyrupNumber = 0      // 바닐라 시럽 개수를 위한 변수
    var irishSyrupNumber = 0        // 아이리쉬 시럽 개수를 위한 변수
    var cafeSyrupNumber = 0         // 카페 시럽 개수를 위한 변수

    var str_tapiocaPearl: String = ""

    var str_toppingSauce: String = ""        // 체크박스에서 반환된 값(토핑 소스 : 초콜릿, 카라멜)
    var str_topping: String = ""      // 체크박스에서 반환된 값(토핑 : 초코쿠키 크림, 초콜릿칩, 휘핑크림)


    var listener = CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
        if(isChecked) {
            when(compoundButton.id) {
                // 타피오카 펄 체크
                R.id.custom_pearl1_checkBox -> {
                    //str_tapiocaPearl = custom_pearl1_checkBox.text.toString()    // 체크 O
                    str_tapiocaPearl = " 타피오카 펄 "   // 체크 O
                }

                // 토핑 소스(2) -
                // 초콜릿 체크
                R.id.custom_toppingsauce1_checkBox -> {
                    //str_toppingSauce = str_toppingSauce + " " + custom_toppingsauce1_checkBox.text.toString()
                    str_toppingSauce += " 초콜릿 "
                }
                // 카라멜 체크
                R.id.custom_toppingsauce2_checkBox -> {
                    //str_toppingSauce = str_toppingSauce + " " + custom_toppingsauce2_checkBox.text.toString()
                    str_toppingSauce += "        카라멜        "
                }


                // 토핑(3) -
                //  초코쿠키 크림 체크
                R.id.custom_topping1_checkBox -> {
                    //str_topping = str_topping + " " +  custom_topping1_checkBox.text.toString()
                    str_topping += " 초코쿠키 크림 "
                }
                //  초콜릿칩 체크
                R.id.custom_topping2_checkBox -> {
                    //str_topping = str_topping + " " +  custom_topping2_checkBox.text.toString()
                    str_topping += "        초콜릿칩        "
                }
                //  휘핑크림 체크
                R.id.custom_topping3_checkBox -> {
                    //str_topping = str_topping + " " +  custom_topping3_checkBox.text.toString()
                    str_topping += "        휘핑크림        "
                }
            }
        }


    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        custom_name_EditText = findViewById(R.id.custom_name_EditText)

        custom_prise_EditText = findViewById(R.id.custom_prise_EditText)

        sizeRadioGroup = findViewById(R.id.sizeRadioGroup)
        custom_size1_radioButton = findViewById(R.id.custom_size1_radioButton)
        custom_size2_radioButton = findViewById(R.id.custom_size2_radioButton)

        // 증감버튼 동작을 위한 할당
        // 에스프레소
        custom_shotnum_sub_button = findViewById(R.id.custom_shotnum_sub_button) //감소 버튼
        custom_shotnum_plus_button = findViewById(R.id.custom_shotnum_plus_button) //증가 버튼
        custom_shotnum_textView = findViewById(R.id.custom_shotnum_textView)    // 개수

        // 헤이즐넛 시럽
        custom_hazelnutsyrupnum_sub_button =
            findViewById(R.id.custom_hazelnutsyrupnum_sub_button) //감소 버튼
        custom_hazelnutsyrupnum_plus_button =
            findViewById(R.id.custom_hazelnutsyrupnum_plus_button) //증가 버튼
        custom_hazelnutsyrupnum_textView =
            findViewById(R.id.custom_hazelnutsyrupnum_textView)    // 개수
        // 카라멜 시럽
        custom_caramelsyrupnum_sub_button =
            findViewById(R.id.custom_caramelsyrupnum_sub_button) //감소 버튼
        custom_caramelsyrupnum_plus_button =
            findViewById(R.id.custom_caramelsyrupnum_plus_button) //증가 버튼
        custom_caramelsyrupnum_textView =
            findViewById(R.id.custom_caramelsyrupnum_textView)    // 개수
        //  바닐라 시럽
        custom_vanillasyrupnum_sub_button =
            findViewById(R.id.custom_vanillasyrupnum_sub_button) //감소 버튼
        custom_vanillasyrupnum_plus_button =
            findViewById(R.id.custom_vanillasyrupnum_plus_button) //증가 버튼
        custom_vanillasyrupnum_textView =
            findViewById(R.id.custom_vanillasyrupnum_textView)    // 개수
        // 아이리쉬 시럽
        custom_irishsyrupnum_sub_button = findViewById(R.id.custom_irishsyrupnum_sub_button) //감소 버튼
        custom_irishsyrupnum_plus_button =
            findViewById(R.id.custom_irishsyrupnum_plus_button) //증가 버튼
        custom_irishsyrupnum_textView = findViewById(R.id.custom_irishsyrupnum_textView)    // 개수
        // 카페 시럽
        custom_cafesyrupnum_sub_button = findViewById(R.id.custom_cafesyrupnum_sub_button) //감소 버튼
        custom_cafesyrupnum_plus_button = findViewById(R.id.custom_cafesyrupnum_plus_button) //증가 버튼
        custom_cafesyrupnum_textView = findViewById(R.id.custom_cafesyrupnum_textView)    // 개수


        custom_pearl1_checkBox = findViewById(R.id.custom_pearl1_checkBox)

        custom_toppingsauce1_checkBox = findViewById(R.id.custom_toppingsauce1_checkBox)
        custom_toppingsauce2_checkBox = findViewById(R.id.custom_toppingsauce2_checkBox)

        custom_topping1_checkBox = findViewById(R.id.custom_topping1_checkBox)
        custom_topping2_checkBox = findViewById(R.id.custom_topping2_checkBox)
        custom_topping3_checkBox = findViewById(R.id.custom_topping3_checkBox)

        login_button = findViewById(R.id.login_button)

        custom_basemenu_spinner = findViewById(R.id.custom_basemenu_spinner)


        dbManager = DBManager(this, "ediyaMenuDB", null, 1)


        var str_existingMenuName: String = ""   //custom_basemenu_spinner 에서 반환된 값(기존메뉴 이름)

        // 스피너를 위한 리스트 배열로 만들기
        var ediyaExistingMenu =
            resources.getStringArray(R.array.ediya_menu)  //var ediyaExistingMenu = listOf("선택하세요", "초콜릿칩플래치노", "녹차플랫치노", "오리진쉐이크","딸기쉐이크", "복숭아아이스티", "토피넛라떼")   //arrays 파일에 작성하였음
        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ediyaExistingMenu)
        custom_basemenu_spinner.adapter = adapter

        // 스피너 동작을 감지하는 리스너 연결
        custom_basemenu_spinner.setSelection(0) // 시작 위치 지정
        custom_basemenu_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    // 선택된 메뉴 저장하기 (아직 구현 안함) 텍스트.setText(spinner.selectedItem.toString())
                    str_existingMenuName = custom_basemenu_spinner.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }


        // 감소(-) 버튼 클릭
        // 에스프레소 샷 갯수 감소 버튼
        custom_shotnum_sub_button.setOnClickListener {
            espressoShotNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(espressoShotNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                espressoShotNumber = 0
            }
            custom_shotnum_textView.text = espressoShotNumber.toString()
        }

        //  헤이즐넛 시럽 갯수 감소 버튼
        custom_hazelnutsyrupnum_sub_button.setOnClickListener {
            hazelnutsSyrupNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(hazelnutsSyrupNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                hazelnutsSyrupNumber = 0
            }
            custom_hazelnutsyrupnum_textView.text = hazelnutsSyrupNumber.toString()
        }
        //  카라멜 시럽 갯수 감소 버튼
        custom_caramelsyrupnum_sub_button.setOnClickListener {
            caramelSyrupNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(caramelSyrupNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                caramelSyrupNumber = 0
            }
            custom_caramelsyrupnum_textView.text = caramelSyrupNumber.toString()
        }
        //  바닐라 시럽 갯수 감소 버튼
        custom_vanillasyrupnum_sub_button.setOnClickListener {
            vanillaSyrupNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(vanillaSyrupNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                vanillaSyrupNumber = 0
            }
            custom_vanillasyrupnum_textView.text = vanillaSyrupNumber.toString()
        }
        // 아이리쉬 시럽 갯수 감소 버튼
        custom_irishsyrupnum_sub_button.setOnClickListener {
            irishSyrupNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(irishSyrupNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                irishSyrupNumber = 0
            }
            custom_irishsyrupnum_textView.text = irishSyrupNumber.toString()
        }
        //  카페 시럽 갯수 감소 버튼
        custom_cafesyrupnum_sub_button.setOnClickListener {
            cafeSyrupNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(cafeSyrupNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                cafeSyrupNumber = 0
            }
            custom_cafesyrupnum_textView.text = cafeSyrupNumber.toString()
        }

        // 증가(+) 버튼 클릭
        // 에스프레소 샷 갯수 증가 버튼
        custom_shotnum_plus_button.setOnClickListener {
            espressoShotNumber++
            custom_shotnum_textView.text = espressoShotNumber.toString()
        }

        //  헤이즐넛 시럽 갯수 증가 버튼
        custom_hazelnutsyrupnum_plus_button.setOnClickListener {
            hazelnutsSyrupNumber++
            custom_hazelnutsyrupnum_textView.text = hazelnutsSyrupNumber.toString()
        }
        //  카라멜 시럽 갯수 증가 버튼
        custom_caramelsyrupnum_plus_button.setOnClickListener {
            caramelSyrupNumber++
            custom_caramelsyrupnum_textView.text = caramelSyrupNumber.toString()
        }
        //  바닐라 시럽 갯수 증가 버튼
        custom_vanillasyrupnum_plus_button.setOnClickListener {
            vanillaSyrupNumber++
            custom_vanillasyrupnum_textView.text = vanillaSyrupNumber.toString()
        }
        // 아이리쉬 시럽 갯수 증가 버튼
        custom_irishsyrupnum_plus_button.setOnClickListener {
            irishSyrupNumber++
            custom_irishsyrupnum_textView.text = irishSyrupNumber.toString()
        }
        //  카페 시럽 갯수 증가 버튼
        custom_cafesyrupnum_plus_button.setOnClickListener {
            cafeSyrupNumber++
            custom_cafesyrupnum_textView.text = cafeSyrupNumber.toString()
        }

        // 체크박스 클릭
        custom_pearl1_checkBox.setOnCheckedChangeListener(listener)

        custom_toppingsauce1_checkBox.setOnCheckedChangeListener(listener)
        custom_toppingsauce2_checkBox.setOnCheckedChangeListener(listener)

        custom_topping1_checkBox.setOnCheckedChangeListener(listener)
        custom_topping2_checkBox.setOnCheckedChangeListener(listener)
        custom_topping3_checkBox.setOnCheckedChangeListener(listener)




        // 커스텀메뉴 작성 완료 버튼 클릭
        login_button.setOnClickListener {
            var str_customMenuName: String =
                custom_name_EditText.text.toString()  //custom_name_EditText 에서 반환된 값(커스텀메뉴 이름)

            var str_price: String = custom_prise_EditText.text.toString()
            var str_size: String = ""             // sizeRadioGroup(라디오)에서 반환된 값(사이즈 : 레귤러 or 엑스트라)


            // 사이즈 선택 라디오 버튼
            if (sizeRadioGroup.checkedRadioButtonId == R.id.custom_size1_radioButton)
                str_size = custom_size1_radioButton.text.toString() // 레귤러 사이즈
            if (sizeRadioGroup.checkedRadioButtonId == R.id.custom_size2_radioButton)
                str_size = custom_size2_radioButton.text.toString() // 엑스트라 사이즈


            var str_espressoShotNumber: String = ""

            var str_hazelnutsSyrupNumber: String = ""
            var str_caramelSyrupNumber: String = ""
            var str_vanillaSyrupNumber: String = ""
            var str_irishSyrupNumber: String = ""
            var str_cafeSyrupNumber: String = ""



            str_espressoShotNumber = espressoShotNumber.toString()

            str_hazelnutsSyrupNumber = hazelnutsSyrupNumber.toString()
            str_caramelSyrupNumber = caramelSyrupNumber.toString()
            str_vanillaSyrupNumber = vanillaSyrupNumber.toString()
            str_irishSyrupNumber = irishSyrupNumber.toString()
            str_cafeSyrupNumber = cafeSyrupNumber.toString()

//
//            var str_tapiocaPearl: String = ""
//
//            var str_toppingSauce: String = ""        // 체크박스에서 반환된 값(토핑 소스 : 초콜릿, 카라멜)
//            var str_topping: String = ""      // 체크박스에서 반환된 값(토핑 : 초코쿠키 크림, 초콜릿칩, 휘핑크림)

//            // 타피오카 펄 체크 유무
//            custom_pearl1_checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
//                if (isChecked) {
//                    // 체크 O
//                    str_tapiocaPearl = custom_pearl1_checkBox.text.toString() // 타피오카 펄
//                } else {
//                    // 체크 X
//                    str_tapiocaPearl = "타피오카 펄 선택 안함"
//                }
//            }
//

//            // 토핑 소스 - 초콜릿 체크 유무
//            custom_toppingsauce1_checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
//                if (isChecked) {
//                    // 체크 O
//                    str_toppingSauce = str_toppingSauce + " " + custom_toppingsauce1_checkBox.text.toString()
//                } else {
//                    // 체크 X
//                }
//            }
//            // 토핑 소스 - 카라멜 체크 유무
//            custom_toppingsauce2_checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
//                if (isChecked) {
//                    // 체크 O
//                    str_toppingSauce = str_toppingSauce + " " + custom_toppingsauce2_checkBox.text.toString()
//                } else {
//                    // 체크 X
//                }
//            }
//
//
//            // 토핑  - 초코쿠키 크림 체크 유무
//            custom_topping1_checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
//                if (isChecked) {
//                    // 체크 O
//                    str_topping = str_topping + " " +  custom_topping1_checkBox.text.toString()
//                } else {
//                    // 체크 X
//                }
//            }
//            // 토핑  - 초콜릿칩 체크 유무
//            custom_topping2_checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
//                if (isChecked) {
//                    // 체크 O
//                    str_topping = str_topping + " " + custom_topping2_checkBox.text.toString()
//                } else {
//                    // 체크 X
//                }
//            }
//            // 토핑  - 휘핑크림 체크 유무
//            custom_topping3_checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
//                if (isChecked) {
//                    // 체크 O
//                    str_topping = str_topping + " " + custom_topping3_checkBox.text.toString()
//                } else {
//                    // 체크 X
//                }
//            }

// 이거 버튼 클릭 밖으로 빼보기
//
//            var listener = CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
//                if(isChecked) {
//                    when(compoundButton.id) {
//                        // 타피오카 펄 체크
//                        R.id.custom_pearl1_checkBox -> {
//                            str_tapiocaPearl = custom_pearl1_checkBox.text.toString()    // 체크 O
//                        }
//
//                        // 토핑 소스(2) -
//                        // 초콜릿 체크
//                        R.id.custom_toppingsauce1_checkBox -> {
//                            str_toppingSauce = str_toppingSauce + " " + custom_toppingsauce1_checkBox.text.toString()
//                        }
//                        // 카라멜 체크
//                        R.id.custom_toppingsauce2_checkBox -> {
//                            str_toppingSauce = str_toppingSauce + " " + custom_toppingsauce2_checkBox.text.toString()
//                        }
//
//
//                        // 토핑(3) -
//                        //  초코쿠키 크림 체크
//                        R.id.custom_topping1_checkBox -> {
//                            str_topping = str_topping + " " +  custom_topping1_checkBox.text.toString()
//                        }
//                        //  초콜릿칩 체크
//                        R.id.custom_topping2_checkBox -> {
//                            str_topping = str_topping + " " +  custom_topping2_checkBox.text.toString()
//                        }
//                        //  휘핑크림 체크
//                        R.id.custom_topping3_checkBox -> {
//                            str_topping = str_topping + " " +  custom_topping3_checkBox.text.toString()
//                        }
//                    }
//                }
//                else {
//                    // 타피오카 펄 체크 X
//                    when(compoundButton.id){
//                        R.id.custom_pearl1_checkBox -> str_tapiocaPearl = "타피오카 펄 선택 안함"
//                    }
//                }
//            }


//            // 체크박스 클릭
//            custom_pearl1_checkBox.setOnCheckedChangeListener(listener)
//
//            custom_toppingsauce1_checkBox.setOnCheckedChangeListener(listener)
//            custom_toppingsauce2_checkBox.setOnCheckedChangeListener(listener)
//
//            custom_topping1_checkBox.setOnCheckedChangeListener(listener)
//            custom_topping2_checkBox.setOnCheckedChangeListener(listener)
//            custom_topping3_checkBox.setOnCheckedChangeListener(listener)
//




//            // 아무것도 선택하지 않으면
//            if (str_topping == "") {
//                str_topping = "선택 안함"
//            }
//            if (str_toppingSauce == "") {
//                str_toppingSauce = "선택 안함"
//            }

            // sqlite에 데이터 저장
            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO ediyaMenuDB VALUES ('" + str_customMenuName + "', '" + str_existingMenuName + "', '" + str_price + "', '" + str_size + "', '" + str_espressoShotNumber + "', '" + str_tapiocaPearl + "', '" + str_hazelnutsSyrupNumber + "', '" + str_caramelSyrupNumber + "', '" + str_vanillaSyrupNumber + "', '" + str_irishSyrupNumber + "', '" + str_cafeSyrupNumber + "' ,'" + str_toppingSauce + "','" + str_topping + "');")


            sqlitedb.close()

            val intent = Intent(this, EdiyaCustomMenuShowActivity::class.java)
            intent.putExtra("intent_name", str_customMenuName)
            startActivity(intent)
        }


    }

}