package com.example.reminder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_reminder.view.*

class ReminderAdapter(private val reminders: List<Reminder>) : RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

           fun bind(reminder: Reminder) {
            itemView.tvReminder.text = reminder.reminderText
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return reminders.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reminders[position])
    }
}
/*
* For the recyclerview to know how and which items to display, an adapter needs to be created.
* Create a kotlin class named ReminderAdapter.
*
* The RecyclerView recycles a set of ViewHolders. The views in the list are represented by
* ViewHolder objects. An inner class, called ViewHolder, which extends RecyclerView.
* ViewHolder is the view holder for this Recyclerview. In the ViewHolder a reference to the TextView
* is made and a bind method is created which is used to populate the widgets with data from the
* Reminder object. In our case, it sets the text from the TextView to the text from the Reminder String.
*
* In the class constructor, a variable called reminders is added. This is a List of Reminder objects
* which represents the reminders that should be displayed in the RecyclerView.
*
* The ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder>. Here the ViewHolder
* that was created has been given as the ViewHolder type. Android Studio will prompt us to implement
* methods. The following methods are implemented:
*   onCreateViewHolder: Called when RecyclerView needs a new RecyclerView.ViewHolder.
*                       Here a new ReminderAdapter.ViewHolder object is created using a
*                       LayoutInflater which inflates the layout resource file item_reminder.
*   getItemCount: Returns the total number of items in the data set held by the adapter.
*   onBindViewHolder: Called by RecyclerView to bind the data with the specified position.
*                     The bind method made in the previous step is used.
* */



