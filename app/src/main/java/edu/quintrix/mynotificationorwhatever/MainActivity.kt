package edu.quintrix.mynotificationorwhatever

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private var notificationManager : NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //edu.quintrix.mynotificationorwhatever
        createNotificationChannel(getString(R.string.package_name), "My Notification News", "Example Channel")
    }

    fun sendNotification(view : View) {
        val notificationID = 101
        val channelId = getString(R.string.package_name)
        val resultIntent = Intent(this, ResultActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val icon:Icon = Icon.createWithResource(this,android.R.drawable.ic_dialog_info)

        val action : Notification.Action = Notification.Action.Builder(icon, "Open",
            pendingIntent).build()

        val notification = Notification.Builder(this@MainActivity, channelId)
            .setContentTitle("Example Notifcation")
            .setContentText("This is an example notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setChannelId(channelId)
            .setActions(action)
            .setNumber(420)
            .build()
        notificationManager?.notify(notificationID, notification)
    }

    private fun createNotificationChannel(id: String, name: String, description: String) {
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id,name,importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.vibrationPattern = longArrayOf(100,200,300,100)
        notificationManager?.createNotificationChannel(channel)

    }

}