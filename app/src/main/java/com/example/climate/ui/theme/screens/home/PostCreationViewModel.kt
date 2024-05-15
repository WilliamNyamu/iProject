package com.example.climate.ui.theme.screens.home

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import android.content.ContentValues.TAG
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.*
import java.util.UUID

class PostCreationViewModel : ViewModel() {

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private var _imageUri: Uri? = null
    val imageUri: Uri?
        get() = _imageUri

    fun updateImageUri(uri: Uri) {
        _imageUri = uri
    }

    fun createPost(postText: String, imageUri: Uri?, onPostCreated: (Post) -> Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let { uid ->
            val postId = firebaseDatabase.reference.child("posts").push().key ?: ""

            if (imageUri != null) {
                val imageRef = firebaseStorage.reference.child("images").child("$postId.jpg")
                imageRef.putFile(imageUri)
                    .addOnSuccessListener { taskSnapshot ->
                        imageRef.downloadUrl.addOnSuccessListener { uri ->
                            // Image uploaded successfully, create post object
                            val post = Post(
                                postId = postId,
                                userId = uid,
                                userName = "User Name", // replace with actual username
                                userProfession = "User Profession",
                                text = postText,
                                imageUri = uri.toString()
                            )

                            // Save post to Firebase Realtime Database
                            firebaseDatabase.reference.child("posts").child(postId).setValue(post)
                                .addOnSuccessListener {
                                    onPostCreated(post)
                                }
                                .addOnFailureListener {
                                    Log.d("PostCreationViewModel", "Failed to save post: ${it.message}")
                                }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("PostCreationViewModel", "Failed to upload image: ${exception.message}")
                    }
            } else {
                // Create post without image
                val post = Post(
                    postId = postId,
                    userId = uid,
                    userName = "User Name", // replace with actual username
                    userProfession = "User Profession",
                    text = postText,
                    imageUri = null
                )

                // Save post to Firebase Realtime Database
                firebaseDatabase.reference.child("posts").child(postId).setValue(post)
                    .addOnSuccessListener {
                        onPostCreated(post)
                    }
                    .addOnFailureListener {
                        Log.d("PostCreationViewModel", "Failed to save post: ${it.message}")
                    }
            }
        }
    }
}
//class PostCreationViewModel(
//    private val storage: FirebaseStorage,
//    private val database: FirebaseDatabase
//) : ViewModel() {
//
//    // ...
//
//    fun createPost(imageUri: Uri, description: String) {
//        // Upload image to Firebase Storage
//        val storageRef = storage.reference.child("posts/${UUID.randomUUID()}")
//        val uploadTask = storageRef.putFile(imageUri)
//
//        uploadTask.addOnSuccessListener { taskSnapshot ->
//            // Get the download URL of the uploaded image
//            storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
//                // Create a new post object
//                val post = Post(
//                    id = UUID.randomUUID().toString(),
//                    description = description,
//                    imageUrl = downloadUri.toString(),
//                    authorId = FirebaseAuth.getInstance().currentUser!!.uid,
//                    timestamp = System.currentTimeMillis()
//                )
//
//                // Write the post to the Firebase Realtime Database
//                database.reference.child("posts").child(post.id).setValue(post)
//                    .addOnSuccessListener {
//                        // Post created successfully
//                    }
//                    .addOnFailureListener { exception ->
//                        // Handle database write error
//                    }
//            }
//        }
//            .addOnFailureListener { exception ->
//                // Handle image upload error
//            }
//    }
//