package com.example.testtask.sign_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.testtask.R
import com.example.testtask.content_screen.ContentActivity

class SignInFragment : Fragment() {

    private var firstNameEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var signButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        firstNameEditText = view.findViewById(R.id.first_name_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)
        signButton = view.findViewById(R.id.sign_button)

        return view
    }

    override fun onStart() {
        super.onStart()

        signButton?.setOnClickListener {
            val intent = ContentActivity.newIntent(requireContext())
            startActivity(intent)
            requireActivity().finish()
        }
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}