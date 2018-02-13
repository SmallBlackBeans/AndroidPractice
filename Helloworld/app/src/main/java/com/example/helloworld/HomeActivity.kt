package com.example.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_home)
        super.onCreate(savedInstanceState)
        val sexGroup = findViewById<View>(R.id.sex_rg) as RadioGroup
        sexGroup.setOnCheckedChangeListener { radioGroup, chedkId ->
            if (chedkId == R.id.sex_man) {
                Toast.makeText(this@HomeActivity, "男", 0).show()
            } else {
                Toast.makeText(this@HomeActivity, "女", 0).show()
            }
        }
    }

    fun myClick(v: View) {
        Toast.makeText(this, "click", Toast.LENGTH_LONG).show()
    }

}
