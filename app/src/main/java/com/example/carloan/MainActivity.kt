package com.example.carloan


import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.graphics.Color
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Initializing a String Array
        val months = arrayOf("12 เดือน (1ปี)","24 เดือน (2ปี)","36 เดือน (3ปี)","48 เดือน (4ปี)","60 เดือน (5ปี)","72 เดือน (6ปี)","84 เดือน (7ปี)","96 เดือน (8ปี)")

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            months // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        spinner.adapter = adapter;

        // Set an on item selected listener for spinner object
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                val test: TextView = findViewById(R.id.textView)

                var month = 12
                if (position == 0) { month = 12 }
                else if (position == 1) { month = 24}
                else if (position == 2) { month = 36}
                else if (position == 3) { month = 48}
                else if (position == 4) { month = 60}
                else if (position == 5) { month = 72}
                else if (position == 6) { month = 84}
                else if (position == 7) { month = 96}
                test.text = month.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        // Initializing a String Array
        val money = arrayOf("บาท"," % ")

        // Initializing an ArrayAdapter
        val adapter2 = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            money // Array
        )

        // Set the drop down view resource
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        spinner2.adapter = adapter2;

        // Set an on item selected listener for spinner object
        spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                val test: TextView = findViewById(R.id.textViewMoney)
                if (position == 0) { test.text = "%" }
                else if (position == 1) { test.text = "บาท"}


            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }


    }

    public fun onSubmit(view: View) {

        val x = inputX.text.toString()
        var x1 = 0
        val y = inputY.text.toString()
        var y1 = 0
        val z = inputZ.text.toString()
        var z1 = 0.00

        var m = textView.text.toString().toInt()
        var m2 = textViewMoney.text.toString()

        if(x.trim().length>0) {
            x1 = inputX.text.toString().toInt()
        }

        if(y.trim().length>0) {
            y1 = inputY.text.toString().toInt()
        }

        if(z.trim().length>0) {
            z1 = inputZ.text.toString().toDouble()
        }

        // ยอดจัด
        var kk1 = 0.00
        if (m2.equals("%")) { kk1 = x1.toDouble() - y1 }
        else if(m2.equals("บาท")) { kk1 = x1- (y1.toDouble() * x1 / 100) }

        // ค่างวด ต่อดือน
        var kk2 = kk1 / m

        // ดอกเบี้ย ต่อเดือน
        var kk3 = kk1 * z1 / 1200

        // ค่างวด + ดอกเบี้ย ต่อเดือน
        var kk4 = kk2 + kk3

        // +Vat7%
        var kk5 = kk4 * 1.07

        //ยอดดาวน์
        var kk6 = 0.00
        if (x1 == 0) { kk6 = 0.00 }
        else {
            if (m2.equals("%")) {
                kk6 = y1.toDouble() / x1 * 100
            } else if (m2.equals("บาท")) {
                kk6 = y1.toDouble() * x1 / 100
            }
        }

        val dec = DecimalFormat("#,##0.00")
        // ยอดจัดResultA
        val a: TextView = findViewById(R.id.ResultA)
        a.text = dec.format(kk1)

        // ค่างวด ต่อดือน ResultB
        val b: TextView = findViewById(R.id.ResultB)
        b.text = dec.format(kk2)

        // ดอกเบี้ย ต่อเดือน
        val c: TextView = findViewById(R.id.ResultC)
        c.text = dec.format(kk3)

        // ค่างวด + ดอกเบี้ย ต่อเดือน ResultD
        val d: TextView = findViewById(R.id.ResultD)
        d.text = dec.format(kk4)

        // +Vat7%
        val e: TextView = findViewById(R.id.ResultE)
        e.text = dec.format(kk5)

        val f: TextView = findViewById(R.id.ResultF)
        f.text = dec.format(kk6)




    }


}
