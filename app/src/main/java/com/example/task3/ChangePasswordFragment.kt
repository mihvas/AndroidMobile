package com.example.task3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

import com.example.task3.databinding.FragmentChangePasswordBinding
import com.example.task3.databinding.FragmentProfileBinding
import com.google.android.material.button.MaterialButton


class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding
    private var isPasswordVisible = false
    private var isPasswordVisible2 = false
    private var isPasswordVisible3 = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.backButton).setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ProfileFragment(),
                    "PROFILE_FRAGMENT"
                )
                .commit()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentChangePasswordBinding.inflate(layoutInflater)
        binding.textInputLayout.setEndIconOnClickListener {
            isPasswordVisible = togglePasswordVisibility(
                binding.textInputLayout,
                binding.textInputEdit,
                isPasswordVisible
            )
        }

        binding.textInputLayout2.setEndIconOnClickListener {
            isPasswordVisible = togglePasswordVisibility(
                binding.textInputLayout2,
                binding.textInputEdit2,
                isPasswordVisible2
            )
        }

        binding.textInputLayout3.setEndIconOnClickListener {
            isPasswordVisible2 = togglePasswordVisibility(
                binding.textInputLayout3,
                binding.textInputEdit3,
                isPasswordVisible3
            )
        }
    }


    private fun togglePasswordVisibility(
        textInputLayout: com.google.android.material.textfield.TextInputLayout,
        textInputEdit: com.google.android.material.textfield.TextInputEditText,
        isPasswordVisible: Boolean
    ): Boolean {
        if (isPasswordVisible) {
            textInputEdit.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            textInputEdit.transformationMethod =
                android.text.method.PasswordTransformationMethod.getInstance()
            textInputLayout.endIconDrawable =
                ResourcesCompat.getDrawable(getResources(), R.drawable.ic_app_password_eye, null)

        } else {
            textInputEdit.inputType = InputType.TYPE_CLASS_TEXT
            textInputEdit.transformationMethod = null
            textInputLayout.endIconDrawable =
                ResourcesCompat.getDrawable(
                    getResources(),
                    R.drawable.ic_app_password_eye_with_strikethrough,
                    null
                )
        }

        return !isPasswordVisible
    }

}