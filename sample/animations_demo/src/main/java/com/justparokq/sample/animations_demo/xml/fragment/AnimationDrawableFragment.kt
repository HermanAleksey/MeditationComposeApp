package com.justparokq.sample.animations_demo.xml.fragment

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.justparokq.sample.animations_demo.R


class AnimationDrawableFragment : Fragment() {

    private lateinit var iv: ImageView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_animation_drawable,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv = view.findViewById<AppCompatImageView>(R.id.iv_animated_drawable)
        btnStart = view.findViewById(R.id.btn_start)
        btnStop = view.findViewById(R.id.btn_stop)

        iv.setBackgroundResource(R.drawable.animated_drawable_example)

        val frameAnimation = iv.background as AnimationDrawable
        frameAnimation.start()
        btnStart.setOnClickListener {
            frameAnimation.start()
        }
        btnStop.setOnClickListener {
            frameAnimation.stop()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = AnimationDrawableFragment()
    }
}