package com.bloc.blocnotes;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.bloc.blocnotes.models.Note;

/**
 * Created by matthewarnold on 08/01/15.
 *
 */
public class ReminderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // get the notification manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // get the noteId and instantiate the note
        Long noteId = intent.getLongExtra("noteId", 0);
        Note note = new Note(noteId);
        note.load();

        Log.d("intent:", intent.getAction());

        // check whether the intent is our notify intent
        if (intent.getAction().equals("com.bloc.notify")) {


            // build a notification
            Notification notification = buildNotification(context, note);

            // let the notification manager know about out notification
            notificationManager.notify(123,notification);

        }
        else if (intent.getAction().equals("com.bloc.snooze")) {

            // first get rid of the original notification
            notificationManager.cancel(123);

            // then close the notification centre
            Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            context.sendBroadcast(it);

            // create a new intent, set its action and add the noteId
            Intent reminderIntent = new Intent(context, ReminderReceiver.class);
            reminderIntent.setAction("com.bloc.notify");
            reminderIntent.putExtra("noteId", noteId);

            // create a pending intent based on our intent
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, reminderIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // set an alarm for the intent
            // convert the remindIn time into milliseconds
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + 10 * 60 * 1000,       // 10 minutes
                    pendingIntent
            );

        }
        else if (intent.getAction().equals("com.bloc.delete")) {

            // clear notification
            notificationManager.cancel(123);

            // close notification centre
            Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            context.sendBroadcast(it);

            // delete note
            note.delete();

        }

    }

    private Notification buildNotification(Context context, Note note) {

        // make the pending intent for snooze
        Intent snoozeIntent = new Intent(context, ReminderReceiver.class);
        snoozeIntent.setAction("com.bloc.snooze");
        snoozeIntent.putExtra("noteId", note.getId());
        PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // make the pending intent for delete
        Intent deleteIntent = new Intent(context, ReminderReceiver.class);
        deleteIntent.setAction("com.bloc.delete");
        deleteIntent.putExtra("noteId", note.getId());
        PendingIntent deletePendingIntent = PendingIntent.getBroadcast(context, 0, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // make the pending intent for edit
        Intent editIntent = new Intent(context, ReminderReceiver.class);
        editIntent.setAction("com.bloc.edit");
        editIntent.putExtra("noteId", note.getId());
        PendingIntent editPendingIntent = PendingIntent.getBroadcast(context, 0, editIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // make and return a notification
        return new NotificationCompat.Builder(context)
                .setContentTitle("Note Reminder")
                .setContentText(note.getText())
                .setSmallIcon(android.R.drawable.ic_menu_edit)
                .setContentIntent(editPendingIntent) // for the edit
                .addAction(
                        // for the snooze
                        android.R.drawable.ic_menu_rotate,
                        context.getString(R.string.notification_snooze),
                        snoozePendingIntent
                )
                .addAction(
                        // for the delete
                        android.R.drawable.ic_menu_delete,
                        context.getString(R.string.notification_delete),
                        deletePendingIntent
                )
                .setAutoCancel(true)
                .build();

    }
}
