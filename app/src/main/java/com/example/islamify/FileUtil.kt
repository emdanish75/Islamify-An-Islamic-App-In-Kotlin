package com.example.islamify

import android.content.Context
import java.io.File

object FileUtil {
    private const val FILE_NAME = "users.txt"

    fun saveUser(context: Context, name: String, username: String, dob: String, email: String, password: String) {
        val file = File(context.filesDir, FILE_NAME)
        file.appendText("$name,$username,$dob,$email,$password\n")
    }

    fun isUserExist(context: Context, username: String, email: String): Boolean {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return false

        val users = file.readLines()
        for (user in users) {
            val userDetails = user.split(",")
            if (userDetails[1] == username || userDetails[3] == email) {
                return true
            }
        }
        return false
    }
    fun getUserPassword(context: Context, enteredUsername: String): String? {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return null

        val users = file.readLines()
        for (user in users) {
            val userDetails = user.split(",")
            if (userDetails[1] == enteredUsername) {
                return userDetails[4]  // Return the password if username matches
            }
        }
        return null  // Return null if no matching username is found
    }


    fun isUserExist(context: Context, username: String): Boolean {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return false

        val users = file.readLines()
        for (user in users) {
            val userDetails = user.split(",")
            if (userDetails[1] == username) {
                return true
            }
        }
        return false
    }

    fun getUserPasswordByEmail(context: Context, enteredEmail: String): String? {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return null

        val users = file.readLines()
        for (user in users) {
            val userDetails = user.split(",")
            if (userDetails[3] == enteredEmail) {
                return userDetails[4]
            }
        }
        return null
    }
    fun getUserDetails(context: Context, username: String): List<String>? {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return null

        val users = file.readLines()
        for (user in users) {
            val userDetails = user.split(",")
            if (userDetails[1] == username) {
                return userDetails
            }
        }
        return null
    }

    fun updateUser(context: Context, name: String, username: String, dob: String, email: String, password: String) {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return

        val users = file.readLines().toMutableList()
        for (i in users.indices) {
            val userDetails = users[i].split(",")
            if (userDetails[1] == username) {
                users[i] = "$name,$username,$dob,$email,$password"
                break
            }
        }
        file.writeText(users.joinToString("\n"))
    }
}
