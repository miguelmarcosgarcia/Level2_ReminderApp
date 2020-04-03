package com.example.reminder

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    /*
    * Using the Kotlin method arrayListOf<Reminder> an ArrayList of type Reminder is initialized.
    * This list is given as a parameter when initializing the ReminderAdapter.
    *
    * A method called initViews is made which is going to be responsible for initializing the views
    * on startup. This method is called in the onCreate
    * */
    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        reminders.add(Reminder("Do Homework"))
        reminders.add(Reminder("Answer Questions"))

        initViews()
        fab.setOnClickListener {

            // Code to add to the floating button onClickListener:
            val reminder = etReminder.text.toString()
            addReminder(reminder)

        }
    }

    // addReminder method
    private fun addReminder(reminder: String) {
        if (reminder.isNotBlank()) {
            reminders.add(Reminder(reminder))
            reminderAdapter.notifyDataSetChanged()
            etReminder.text?.clear()
        } else {
            Snackbar.make(etReminder, "You must fill in the input field!", Snackbar.LENGTH_SHORT).show()
        }
    }
    /*
    * Using the Kotlin method isNotBlank it is verified that the String is not null or empty. A
    * SnackBar message is displayed if it’s empty to notify the user that you can’t add empty
    * reminders.
    *
    * If the reminder is valid a Reminder object is created using the String and it is added to the
    * reminders ArrayList. Because the list has been updated the adapter needs to be notified that
    * the dataset has changed so it can refresh itself. This is done using
    * reminderAdapter.notifyDataSetChanged(). Using etReminder.text?.clear() the inputfield has
    * been cleared.
*/

    /*
    * In the initViews method a layoutManager is added to the Recyclerview of type
    * LinearLayoutManager which defines that our RecyclerView will be Linear (e.g. if you want a
    * grid layout a GridLayoutManager is used). The reminderAdapter is also added. An itemDecoration
    * is also added, the dividerItemDecoration adds a line under each item in the RecyclerView to
    * separate them, giving a better user experience.
    * */

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvReminders.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvReminders.adapter = reminderAdapter
        rvReminders.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvReminders)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                reminders.removeAt(position)
                reminderAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

}
