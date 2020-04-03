package com.example.reminder

data class Reminder(
    var reminderText: String
)
/*
* A model has to be made representing the reminder String. In Kotlin classes can be prefixed with
* “data”. This means that the main purpose is to hold data. The compiler will automatically create
* getters, setters, a toString and many more so we don’t need to define them. For more information
* refer to: https://kotlinlang.org/docs/reference/data-classes.html
*
* In Kotlin we also don’t need to define a separate constructor if we define the variables in the
* class constructor. Kotlin automatically creates a constructor which initializes these variables
* for us.
* */