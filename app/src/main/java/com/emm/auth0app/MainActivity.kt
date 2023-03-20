package com.emm.auth0app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.emm.auth0app.presentation.Root
import com.emm.auth0app.presentation.fcmservice.NotificationUtils
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Root()
            }
        }
    }

    private fun getToken() = lifecycleScope.launch {
        val token: String = FirebaseMessaging.getInstance().token.await()
        Log.e("aea", token)
        delay(1000L)
        NotificationUtils(applicationContext).showNotification("random", "rrrr")
    }
}