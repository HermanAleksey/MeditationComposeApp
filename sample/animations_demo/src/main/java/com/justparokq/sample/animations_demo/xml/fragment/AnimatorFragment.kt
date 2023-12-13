package com.justparokq.sample.animations_demo.xml.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.justparokq.sample.animations_demo.R

private const val SHOW_DURATION: Long = 300
private const val TO_NORMAL_DURATION: Long = 300
private const val HIDE_DURATION: Long = 300
private const val HIDE_DELAY: Long = 300

class AnimatorFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var btn: Button
    private var isAnimationRunning: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_animator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.iv_animatorDemo)
        btn = view.findViewById(R.id.btn_animatorDemo)
        imageView.setOnClickListener {
            launchAnimation(imageView)
        }
        btn.setOnClickListener {
            imageView.visibility = View.VISIBLE
            imageView.setImageDrawable(view.resources.getDrawable(R.drawable.animator_demo))
        }
    }

    private fun launchAnimation(view: ImageView) {
        if (!isAnimationRunning) {
            val set = AnimatorSet()
            set.playSequentially(
                showAnimatorSet(view),
                toNormalAnimatorSet(view),
                hideAnimatorSet(view)
            )
            set.addListener(getLikeEndListener(view))
            set.start()
        }
        view.animate().alphaBy(0F).alpha(1F).start()
    }

    private fun getLikeEndListener(view: ImageView): AnimatorListenerAdapter? {
        return object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                isAnimationRunning = true
                view.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                isAnimationRunning = false
                view.visibility = View.GONE
            }
        }
    }

    private fun showAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.setDuration(SHOW_DURATION).playTogether(
            ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f),
            ObjectAnimator.ofFloat(view, View.SCALE_X, 0.2f, 1.4f),
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.2f, 1.4f)
        )
        return set
    }

    private fun toNormalAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.setDuration(TO_NORMAL_DURATION).playTogether(
            ObjectAnimator.ofFloat(view, View.SCALE_X, 1.4f, 1f),
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.4f, 1f)
        )
        return set
    }

    private fun hideAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.setDuration(HIDE_DURATION).playTogether(
            ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f),
            ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.2f),
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.2f)
        )
        set.startDelay = HIDE_DELAY
        return set
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnimatorFragment()
    }
}