package com.rmohd8788.chitchat.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.rmohd8788.chitchat.R
import com.rmohd8788.chitchat.RegisterActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*

class SignInFragment : Fragment() {

    private lateinit var emailAuthentication: EmailAuthentication
    private lateinit var mEmail: String
    private lateinit var mPassword: String
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_signInEmail.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validEmail()
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        et_signInPass.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validPassword()
            }

            override fun afterTextChanged(s: Editable?) {}

        })


        tv_forgotPassword.setOnClickListener {
            RegisterActivity.isForgotClicked = true
            setFragment(ForgotPasswordFragment())
        }

        tv_dont_have_account.setOnClickListener {
            setFragment(SignUpFragment())
        }


        btn_sign_in.setOnClickListener {
            emailAuthentication = EmailAuthentication(
                activity = requireActivity(),
                email = mEmail,
                password = mPassword
            )
            emailAuthentication.signInWithEmailAndPass()
        }
    }

    /*
private fun EditText.onChange(cb : (Boolean) -> Unit){
this.addTextChangedListener(object : TextWatcher{
override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
}

override fun afterTextChanged(s: Editable?) {}

})
}
*/

    private fun setFragment(fragment: Fragment) {
        activity!!.supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_frm_right, R.anim.slideout_frm_left)
            .replace(requireActivity().fragmentContainer.id, fragment)
            .commit()
    }

    private fun validEmail(): Boolean {
        mEmail = et_signInEmail.editText?.text.toString()
        return if (mEmail.trim().isEmpty()) {
            et_signInEmail.error = "Field cannot be empty"
            btn_sign_in.isEnabled = false
            false
        } else if (!mEmail.matches(emailPattern.toRegex())) {
            et_signInEmail.error = "Invalid Email"
            btn_sign_in.isEnabled = false
            false
        } else {
            et_signInEmail.isErrorEnabled = false
            true
        }
    }

    private fun validPassword(): Boolean {
        mPassword = et_signInPass.editText?.text.toString()
        return if (mPassword.trim().isEmpty()) {
            et_signInPass.error = "Field can't be empty"
            btn_sign_in.isEnabled = false
            btn_sign_in.textColor(resources.getColor(R.color.white70))
            false
        } else {
            et_signInPass.isErrorEnabled = false
            btn_sign_in.isEnabled = true
            btn_sign_in.textColor(resources.getColor(R.color.white))
            true
        }
    }

    private fun Button.textColor(color: Int) {
        btn_sign_in.setTextColor(color)
    }
}
