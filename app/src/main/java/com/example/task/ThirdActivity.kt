package com.example.task

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task.databinding.ActivitySecondBinding
import com.example.task.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.textInputLayout2.setEndIconOnClickListener {
            isPasswordVisible = togglePasswordVisibility(
                binding.textInputLayout2,
                binding.textInputEdit2,
                isPasswordVisible
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
                getDrawable(R.drawable.ic_app_password_eye)

        } else {
            textInputEdit.inputType = InputType.TYPE_CLASS_TEXT
            textInputEdit.transformationMethod = null
            textInputLayout.endIconDrawable =
                getDrawable(R.drawable.ic_app_password_eye_with_strikethrough)
        }

        return !isPasswordVisible
    }
}