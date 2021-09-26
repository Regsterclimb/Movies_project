package com.example.moviesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesproject.avengers.AvengersDownFragment
import com.example.moviesproject.avengers.AvengersTopFragment
import com.example.moviesproject.avengers.MovieAdapter
import com.example.moviesproject.data.Movie

class MainActivity : AppCompatActivity(), MovieAdapter.clickOnMovie {


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

    //override fun clickOnBackButton() {
      //  supportFragmentManager.popBackStack()
    //}

    override fun clickOnTopFragment(movie: Movie) {
        supportFragmentManager.beginTransaction().apply {
            add(
                R.id.avengers_container_for_down_fragment,
                AvengersDownFragment.newInstance("avengersDownFragment")
            )
            commit()
        }
    }
}