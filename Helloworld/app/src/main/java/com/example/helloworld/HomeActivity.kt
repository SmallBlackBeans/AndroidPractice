package com.example.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.textviewlayout)
        print("hahahahahah")
    }

    fun myClick(v: View) {
        Toast.makeText(this, "click", Toast.LENGTH_LONG).show()
    }

}
