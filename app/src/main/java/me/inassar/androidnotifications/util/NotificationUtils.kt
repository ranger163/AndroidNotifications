/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.inassar.androidnotifications.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import me.inassar.androidnotifications.MainActivity
import me.inassar.androidnotifications.R
import me.inassar.androidnotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

// Step 1.1 extension function to send messages (GIVEN)
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Create the content intent for the notification, which launches
    // this activity
    // Step 1.11 create intent
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // Step 1.12 create PendingIntent
    val pendingIntent = PendingIntent.getActivity(
        applicationContext, NOTIFICATION_ID, contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    // Step 2.0 add style
    val eggImg = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.cooked_egg
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImg)
        .bigLargeIcon(null)

    // TODO: Step 2.2 add snooze action
    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent = PendingIntent.getBroadcast(
        applicationContext, NOTIFICATION_ID, snoozeIntent,
        FLAGS
    )

    // Step 1.2 get an instance of NotificationCompat.Builder
    val builder = NotificationCompat.Builder(
        applicationContext,
        // Step 1.8 use the new 'breakfast' notification channel
        applicationContext.getString(R.string.egg_notification_channel_id)
    )

        // Step 1.3 set title, text and icon to builder
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)

        // Step 1.13 set content intent
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

        // Step 2.1 add style to builder
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImg)

    // TODO: Step 2.3 add snooze action

    // TODO: Step 2.5 set priority

    // Step 1.4 call notify
    notify(NOTIFICATION_ID, builder.build())

}

// Step 1.14 Cancel all notifications
fun NotificationManager.cancelNotifications() {
    cancelAll()
}