package com.example.climate.ui.theme.screens.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class ProfileViewModel : ViewModel() {
    // Firebase Realtime Database reference
    private val databaseRef: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("users")
    }
    // Firebase Storage reference
    private val storageRef = FirebaseStorage.getInstance().reference

    // Function to fetch user profile from Firebase Realtime Database
    fun getUserProfile(uid: String, onDataLoaded: @Composable (UserProfile) -> Unit) {
        // Add a listener to read the data at a specific path
        databaseRef.child(uid).addListenerForSingleValueEvent(/* listener = */ object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(UserProfile::class.java)
                userProfile?.let { onDataLoaded
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                val errorMessage = when (error.code) {
                    DatabaseError.DISCONNECTED -> "Disconnected from the database"
                    DatabaseError.NETWORK_ERROR -> "Network error occurred"
                    DatabaseError.PERMISSION_DENIED -> "Permission denied"
                    DatabaseError.UNKNOWN_ERROR -> "Unknown error occurred"
                    else -> "An error occurred"
                }
                // Log the error for debugging purposes
                Log.e(TAG, "Database error: ${error.message}")
                // You can also report the error to a crash reporting tool (e.g., Firebase Crashlytics)
                // Crashlytics.logException(error.toException())
            }

        })
    }

    // Function to upload profile image to Firebase Storage
    fun uploadProfileImage(uid: String, imageBytes: ByteArray, onSuccess: (String) -> Unit) {
        // Reference to the location of the image in Firebase Storage
        val profileImageRef = storageRef.child("profile_images/${uid}.jpg")
        // Upload image bytes to Firebase Storage
        profileImageRef.putBytes(imageBytes)
            .addOnSuccessListener {
                // Once the upload is successful, get the download URL of the image
                profileImageRef.downloadUrl.addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
            }
    }
}