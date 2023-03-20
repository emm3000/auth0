package com.emm.auth0app.presentation.fcmservice

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {
            it.title?.let { it1 ->
                it.body?.let { it2 ->
                    NotificationUtils(applicationContext).showNotification(
                        it1,
                        it2
                    )
                }
            }

        }
    }
}