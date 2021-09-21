package com.example.moviesproject.avengers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.moviesproject.R

class AvengersDownFragment : Fragment() {

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

        buttonOnBack = view.findViewById<Button>(R.id.back_arrow)
            .apply { setOnClickListener { listner?.clickOnBackButton() } }

    }

    fun setListner(l : Clicker){
        listner = l
    }

    interface Clicker {
        fun clickOnBackButton()
    }


}