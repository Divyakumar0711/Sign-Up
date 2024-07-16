package com.example.signup

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import com.example.signup.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val buttonSignup = findViewById<Button>(R.id.buttonSignup)

        // Set up the spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGender.adapter = adapter
        }

        // Handle signup button click
        buttonSignup.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val gender = spinnerGender.selectedItem.toString()

            // Perform validation (you can add more validations here)
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Mask the password
                val maskedPassword = "*".repeat(password.length)

                // Show details in a popup dialog
                val details = "Name: $name\nEmail: $email\nPassword: $maskedPassword\nGender: $gender"
                AlertDialog.Builder(this)
                    .setTitle("User Details")
                    .setMessage(details)
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        // Clear all text input fields
                        editTextName.text.clear()
                        editTextEmail.text.clear()
                        editTextPassword.text.clear()
                        // Reset the spinner to the first item
                        spinnerGender.setSelection(0)
                    }
                    .create()
                    .show()
            }
        }
    }
}
