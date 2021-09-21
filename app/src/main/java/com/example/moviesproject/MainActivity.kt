package com.example.moviesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesproject.avengers.AvengersDownFragment
import com.example.moviesproject.avengers.AvengersTopFragment

class MainActivity : AppCompatActivity(), AvengersTopFragment.topMovieButtonClickerOnFragment,AvengersDownFragment.Clicker {


    var tpFragment = AvengersTopFragment.newInstance("avengersTopFragment")
        .apply { setListener(this@MainActivity) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.avengers_container, tpFragment
                    .apply { setListener(this@MainActivity) })
                commit()
            }
        }
    }

    override fun onStart() {
        super.onStart()


    }

    override fun clickOnTopFragment() {
            supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.avengers_container_for_down_fragment, AvengersDownFragment.newInstance("avengersDawnFragment")
                        .apply { setListner(this@MainActivity) })
                    addToBackStack(null)
                    commit()
                }

        }

    override fun clickOnBackButton() {
        supportFragmentManager.popBackStack()
    }
}