package com.justparokq.sample.animations_demo.xml.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.justparokq.sample.animations_demo.R

class ViewAnimationsFragment : Fragment() {

    private lateinit var textView: TextView
    private lateinit var clickCounterTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_view_animations, container, false)
    }

    private var clickCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.txt_viewAnimationTest)
        clickCounterTextView = view.findViewById(R.id.txt_clickCountViewAnimation)

        textView.setOnClickListener {
            AnimationUtils.loadAnimation(requireContext(), R.anim.view_animation_demo)
                .also { hyperspaceJumpAnimation ->
                    textView.startAnimation(hyperspaceJumpAnimation)
                }
            clickCount++
            clickCounterTextView.text = "Click count: $clickCount"
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewAnimationsFragment()
    }
}