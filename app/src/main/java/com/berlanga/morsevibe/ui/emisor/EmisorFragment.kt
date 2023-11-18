package com.berlanga.morsevibe.ui.emisor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.berlanga.morsevibe.MainActivity
import com.berlanga.morsevibe.databinding.FragmentEmisorBinding

class EmisorFragment : Fragment() {

    private var _binding: FragmentEmisorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmisorBinding.inflate(inflater, container, false)
        
        val root: View = binding.root

        val mainActivity = activity as? MainActivity

        val btnEmisorEnviar: Button = binding.btnEmisorEnviar
        val btnEmisorLimpiar: Button = binding.btnEmisorLimpiar
        val messageFromEmisor: EditText = binding.messageFromEmisor

        val understand = mainActivity?.sharedBundle?.getString("understand")
        if (understand == "1") {
            Toast.makeText(context, "Mensaje entendido", Toast.LENGTH_SHORT).show()
            mainActivity.sharedBundle.putString("understand", "")
        }

        btnEmisorEnviar.setOnClickListener {
            val message = messageFromEmisor.text.toString()
            mainActivity?.sharedBundle?.putString("sharedText", message)
            messageFromEmisor.setText("")
            Toast.makeText(context, "Mensaje enviado", Toast.LENGTH_SHORT).show()
        }

        btnEmisorLimpiar.setOnClickListener { messageFromEmisor.setText("") }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
