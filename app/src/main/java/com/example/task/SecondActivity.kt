package com.example.task

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.task.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var isPasswordVisible = false
    private var isPasswordVisible2 = false
    private var selectedGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genderOptions = arrayOf("Мужчина", "Женщина", "-")

        setupClickableText()

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

        binding.textInputLayout3.setEndIconOnClickListener {
            isPasswordVisible2 = togglePasswordVisibility(
                binding.textInputLayout3,
                binding.textInputEdit3,
                isPasswordVisible2
            )
        }

        binding.textInputLayout5.setEndIconOnClickListener {
            println("Gender field clicked1")
            showGenderSelectionDialog(genderOptions)
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

    private fun showGenderSelectionDialog(options: Array<String>) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Выберите пол")
            .setItems(options) { _, which ->
                selectedGender = options[which]
                binding.textInputEdit5.setText(selectedGender)
            }
            .create()

        dialog.show()
    }


    private fun setupClickableText() {
        val text =
            "Нажимая на кнопку, вы соглашаетесь с политикой конфиденциальности и обработкой персональных данных, а также принимаете пользовательское соглашение"

        val spannableString = SpannableString(text)

        // Кликабельный текст для "политикой конфиденциальности"
        val privacyPolicyStart = text.indexOf("политикой конфиденциальности")
        val privacyPolicyEnd = privacyPolicyStart + "политикой конфиденциальности".length

        spannableString.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {

                }
            },
            privacyPolicyStart,
            privacyPolicyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            ForegroundColorSpan(Color.rgb(139,0,255)),
            privacyPolicyStart,
            privacyPolicyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        val userAgreementStart = text.indexOf("пользовательское соглашение")
        val userAgreementEnd = userAgreementStart + "пользовательское соглашение".length

        spannableString.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {

                }
            },
            userAgreementStart,
            userAgreementEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        spannableString.setSpan(
            ForegroundColorSpan(Color.rgb(139,0,255)),
            userAgreementStart,
            userAgreementEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textView1.text = spannableString
        binding.textView1.movementMethod = LinkMovementMethod.getInstance()
    }


}