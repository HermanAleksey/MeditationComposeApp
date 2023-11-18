package com.justparokq.sample.animations_demo.xml

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.justparokq.sample.animations_demo.xml.fragment.AnimateVectorDrawableFragment
import com.justparokq.sample.animations_demo.xml.fragment.AnimationDrawableFragment
import com.justparokq.sample.animations_demo.xml.fragment.AnimatorFragment
import com.justparokq.sample.animations_demo.xml.fragment.MotionLayoutFragment
import com.justparokq.sample.animations_demo.xml.fragment.ViewAnimationsFragment

class XmlAnimationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var changeView: ((Fragment) -> Unit)? = null

        fun setFragment(type: XmlAnimationType) {
            val fragment = when (type) {
                XmlAnimationType.AnimationDrawable -> AnimationDrawableFragment.newInstance()
                XmlAnimationType.AnimateVectorDrawable -> AnimateVectorDrawableFragment.newInstance()
                XmlAnimationType.ViewAnimations -> ViewAnimationsFragment.newInstance()
                XmlAnimationType.Animator -> AnimatorFragment.newInstance()
                XmlAnimationType.MotionLayout -> MotionLayoutFragment.newInstance()
            }
            changeView?.invoke(fragment)
        }

        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                AnimationTypesTabBar(::setFragment)
                FragmentContainerViewComposable { changeView = it }
            }
        }
    }
}

@Composable
private fun AnimationTypesTabBar(setFragment: (XmlAnimationType) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(rememberScrollState(), Orientation.Horizontal),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        userScrollEnabled = true,
    ) {
        items(XmlAnimationType.values()) { type ->
            Button(onClick = { setFragment(type) }) {
                Text(text = type.name)
            }
        }
    }
}

@Composable
fun AppCompatActivity.FragmentContainerViewComposable(setView: ((Fragment) -> Unit) -> Unit) {
    AndroidView(
        factory = {
            FragmentContainerView(it).apply {
                id = View.generateViewId()
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { fragmentContainerView ->
            setView { fragment ->
                supportFragmentManager.beginTransaction()
                    .replace(
                        fragmentContainerView.id,
                        fragment,
                        fragment::class.java.toString()
                    )
                    .commit()
            }
        }
    )
}