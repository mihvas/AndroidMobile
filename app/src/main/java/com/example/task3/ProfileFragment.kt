package com.example.task3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task3.databinding.FragmentChangePasswordBinding
import com.example.task3.databinding.FragmentProfileBinding
import com.google.android.material.button.MaterialButton

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)

        view.findViewById<MaterialButton>(R.id.btnChangePassword).setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ChangePasswordFragment(),
                    "CHANGE_PASSWORD_FRAGMENT"
                )
                .commit()


        }
    }
}