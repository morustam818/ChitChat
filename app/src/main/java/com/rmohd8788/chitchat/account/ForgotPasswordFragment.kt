package com.rmohd8788.chitchat.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rmohd8788.chitchat.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class ForgotPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goBack.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_frm_left,R.anim.slideout_frm_right)
                .replace(requireActivity().fragmentContainer.id,SignInFragment())
                .commit()
        }
    }
}