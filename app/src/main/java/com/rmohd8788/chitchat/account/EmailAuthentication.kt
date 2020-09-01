package com.rmohd8788.chitchat.account

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rmohd8788.chitchat.MainActivity

class EmailAuthentication(private val activity: Activity, private val username : String = "", private val email : String, private val password : String) {

    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var databaseReference : DatabaseReference

    fun signInWithEmailAndPass(){
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(activity.applicationContext,"Successful",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity.applicationContext,it.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signUpWithEmailAndPass(){
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    activity.startActivity(Intent(activity.applicationContext,MainActivity::class.java))
                    activity.finish()
                    val currentUser = mAuth.currentUser
                    if (currentUser != null){
                        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.uid)
                        val userDetails = HashMap<String,String>()
                        userDetails["id"] = currentUser.uid
                        userDetails["username"] = username
                        userDetails["imageUrl"] = "default"

                        databaseReference.setValue(userDetails).addOnCompleteListener {
                            if (it.isSuccessful){
                                Toast.makeText(activity.applicationContext,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(activity.applicationContext,it.exception?.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }else{
                    Toast.makeText(activity.applicationContext,task.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }
    }

}