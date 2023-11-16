package com.justparokq.sample.animations_demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.justparokq.core.design_system.AppTheme
import com.justparokq.sample.animations_demo.xml.XmlAnimationsActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(onClick = { navigateToXmlAnimationsDemoScreen() }) {
                        Text(text = "XML")
                    }
                    Button(onClick = { }) {
                        Text(text = "Jetpack Compose")
                    }
                    Button(onClick = { }) {
                        Text(text = "Lottie animation")
                    }
                }
            }
        }

        //compose animations

        //lottie animations

    }
}

private fun Activity.navigateToXmlAnimationsDemoScreen() {
    val intent = Intent(this, XmlAnimationsActivity::class.java)
    startActivity(intent)
}