package com.justparokq.sample.animations_demo.xml.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.justparokq.sample.animations_demo.R

class ViewAnimationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_view_animations, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewAnimationsFragment()
    }
}