package com.example.testtask.sign_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testtask.R
import com.example.testtask.content_screen.ContentActivity

class SignUpFragment : Fragment() {

    interface Callbacks {
        fun onLogInPressed()
    }

    private var firstNameEditText: EditText? = null
    private var lastNameEditText: EditText? = null
    private var emailEditText: EditText? = null
    private var signButton: Button? = null
    private var logInTextView: TextView? = null

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        firstNameEditText = view.findViewById(R.id.first_name_edit_text)
        lastNameEditText = view.findViewById(R.id.last_name_edit_text)
        emailEditText = view.findViewById(R.id.email_edit_text)
        signButton = view.findViewById(R.id.sign_button)
        logInTextView = view.findViewById(R.id.log_in_text_view)

        return view
    }

    override fun onStart() {
        super.onStart()

        logInTextView?.setOnClickListener {
            callbacks?.onLogInPressed()
        }

        signButton?.setOnClickListener {
            val intent = ContentActivity.newIntent(requireContext())
            startActivity(intent)
            requireActivity().finish()
        }
    }

    companion object {
        fun newInstance() = SignUpFragment()
    }
}