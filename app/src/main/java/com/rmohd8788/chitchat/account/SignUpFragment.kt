package com.rmohd8788.chitchat.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rmohd8788.chitchat.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"
    private val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    private lateinit var emailAuthentication: EmailAuthentication
    private lateinit var mEmail : String
    private lateinit var mPassword : String
    private lateinit var mConfirmPass : String
    private lateinit var mUsername : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_already_have_account.setOnClickListener {
            setFragment(SignInFragment())
        }

        et_sign_up_email.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validEmail()
            }

            override fun afterTextChanged(s: Editable?) {}

        })
        et_sign_up_username.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validUsername()
            }

            override fun afterTextChanged(s: Editable?) {}

        })
        et_sign_up_pass.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validPassword()
            }

            override fun afterTextChanged(s: Editable?) {}

        })
        et_confirm_pass.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validConfirmPass()
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        btn_sign_up.setOnClickListener {
            emailAuthentication = EmailAuthentication(
                activity = requireActivity(),
                username = mUsername,
                email = mEmail,
                password = mPassword
            )

            emailAuthentication.signUpWithEmailAndPass()
        }
    }

    private fun setFragment(fragment: Fragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_frm_left, R.anim.slideout_frm_right)
            .replace(requireActivity().fragmentContainer.id, fragment)
            .commit()
    }

    private fun validEmail(): Boolean {
        mEmail = et_sign_up_email.editText?.text.toString()
        return if (mEmail.trim().isEmpty()) {
            et_sign_up_email.error = "Field cannot be empty"
            btn_sign_up.isEnabled = false
            false
        } else if (!mEmail.matches(emailPattern.toRegex())) {
            et_sign_up_email.error = "Invalid Email"
            btn_sign_up.isEnabled = false
            false
        } else {
            et_sign_up_email.isErrorEnabled = false
            true
        }
    }

    private fun validUsername() : Boolean{
        mUsername = et_sign_up_username.editText?.text.toString()
        return if (mUsername.isEmpty()){
            et_sign_up_username.error = "Field can't be empty"
            btn_sign_up.isEnabled = false
            false
        }else{
            et_sign_up_username.isErrorEnabled = false
            true
        }
    }

    private fun validPassword() : Boolean{
        mPassword = et_sign_up_pass.editText?.text.toString()
        return if (mPassword.isEmpty()){
            et_sign_up_pass.error = "Field can't be empty"
            btn_sign_up.isEnabled = false
            false
        }else if (mPassword.length < 7 || !mPassword.matches(passwordPattern.toRegex())){
            et_sign_up_pass.error =
                "Password must have at least 8 characters and contains at least 1 : " +
                        "uppercase letters, lowercase letters, symbols and numbers."
            btn_sign_up.isEnabled = false
            false
        }else{
            et_sign_up_pass.isErrorEnabled = false
            btn_sign_up.isEnabled = true
            true
        }
    }

    private fun validConfirmPass() : Boolean{
        mConfirmPass = et_confirm_pass.editText?.text.toString()
        return if (mConfirmPass.isEmpty()){
            et_confirm_pass.error = "Field  can't be empty"
            btn_sign_up.isEnabled = false
            false
        }else if (mConfirmPass.length>7 && mConfirmPass != mPassword){
            et_confirm_pass.error = "Password doesn't match"
            btn_sign_up.isEnabled = false
            textColor(resources.getColor(R.color.white70))
            false
        }else{
            et_confirm_pass.isErrorEnabled = false
            btn_sign_up.isEnabled = true
            textColor(resources.getColor(R.color.white))
            true
        }
    }

    private fun textColor(color: Int) {
        btn_sign_up.setTextColor(color)
    }

}