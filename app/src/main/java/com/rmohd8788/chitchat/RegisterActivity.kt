package com.rmohd8788.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.rmohd8788.chitchat.account.SignInFragment
import com.rmohd8788.chitchat.account.SignUpFragment
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_sign_in.*

class RegisterActivity : AppCompatActivity() {
    //by using companion object we easily access outside this class
    companion object{
        var isForgotClicked = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //By Default we set SignInFragment
        setFragment(SignInFragment(),R.anim.slide_frm_right,R.anim.slideout_frm_left)
    }

    private fun setFragment(fragment: Fragment,enterAnimation: Int,endAnimation: Int){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(enterAnimation,endAnimation)
            .replace(fragmentContainer.id,fragment)
            .commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (isForgotClicked){
                isForgotClicked = false
                setFragment(SignInFragment(),R.anim.slide_frm_left,R.anim.slideout_frm_right)
                return false
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

}