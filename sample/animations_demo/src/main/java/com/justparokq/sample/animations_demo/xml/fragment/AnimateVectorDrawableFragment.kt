package com.justparokq.sample.animations_demo.xml.fragment

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.justparokq.sample.animations_demo.R

class AnimateVectorDrawableFragment : Fragment() {

    private lateinit var iv: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_animate_vector_drawable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv = view.findViewById<AppCompatImageView>(R.id.iv_animated_vector_drawable)
        startAnimation()
        iv.setOnClickListener {
            startAnimation()
        }
    }

    private fun startAnimation() {
        iv.drawable.apply {
            when (this) {
                is AnimatedVectorDrawableCompat -> this.start()
                is AnimatedVectorDrawable -> this.start()
                else -> { /* not an animated icon */
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = AnimateVectorDrawableFragment()
    }
}