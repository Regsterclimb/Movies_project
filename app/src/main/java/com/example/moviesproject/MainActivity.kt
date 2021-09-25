package com.example.moviesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesproject.avengers.AvengersDownFragment
import com.example.moviesproject.avengers.AvengersTopFragment

class MainActivity : AppCompatActivity(),AvengersDownFragment.Clicker {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, AvengersTopFragment.newInstance("avengersTopFragment"))
                commit()
            }
        }
    }

    override fun onStart() {
        super.onStart()


    }

    override fun clickOnBackButton() {
        supportFragmentManager.popBackStack()
    }
}