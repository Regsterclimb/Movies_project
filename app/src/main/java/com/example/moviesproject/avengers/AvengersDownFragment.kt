package com.example.moviesproject.avengers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R

class AvengersDownFragment : Fragment(),Clicker {

    private var recycler: RecyclerView? = null

    private var buttonOnBack: Button? = null

    private var listner:Clicker? = null

    companion object {
        fun newInstance(keyString: String): AvengersDownFragment {
            val args = Bundle()
            args.putString(keyString, "avengersDownFragment")
            val fragment = AvengersDownFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.avengers_fragment_fullscreen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.recycler_actor)
        recycler?.adapter = ActorAdapter()

        buttonOnBack = view.findViewById<Button>(R.id.back_arrow)
            .apply { setOnClickListener { clickOnBackButton() } }

    }

    override fun onStart() {
        super.onStart()

    }

    private fun updateData() {
        TODO("if needed")

    }


    fun setListner(l : Clicker){
        listner = l
    }

    override fun clickOnBackButton() {
        val activity = view?.context as AppCompatActivity
        activity.supportFragmentManager.popBackStack()
    }

}

interface Clicker {
    fun clickOnBackButton()
}