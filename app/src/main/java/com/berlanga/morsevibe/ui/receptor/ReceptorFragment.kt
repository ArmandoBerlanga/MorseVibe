package com.berlanga.morsevibe.ui.receptor

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.berlanga.morsevibe.MainActivity
import com.berlanga.morsevibe.databinding.FragmentReceptorBinding

class ReceptorFragment : Fragment() {

    private lateinit var vibrator: Vibrator
    private var _binding: FragmentReceptorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReceptorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mainActivity = activity as? MainActivity
        val sharedText = mainActivity?.sharedBundle?.getString("sharedText")

        val understand = binding.understand
        val notUnderstand = binding.notUnderstand

        if (sharedText.isNullOrEmpty())
            return root

        vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        translateAndVibrate("Mensaje pendiente: el mensaje dice: " + sharedText)

        var understandCount = 0
        understand.setOnClickListener {
            if (understandCount == 0) {
                translateAndVibrate("Al dar click una vez mas significa que entendiste")
                understandCount++
            } else {
                translateAndVibrate("Entendido")
                mainActivity?.sharedBundle?.putString("understand", "Entendido")
                understandCount = 0
            }
        }

        var notUnderstandCount = 0
        notUnderstand.setOnClickListener {
            if (notUnderstandCount == 0) {
                translateAndVibrate("Al dar click una vez mas significa que NO entendiste")
                notUnderstandCount++
            } else {
                translateAndVibrate("Entendido")
                mainActivity?.sharedBundle?.putString("understand", "Entendido")
                notUnderstandCount = 0
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun translateAndVibrate(message: String) {
        val morseCode = translateToMorseCode(message)
        vibrateMorseCode(morseCode)
    }

    private fun translateToMorseCode(message: String): String {
        val morseCodeBuilder = StringBuilder()
        for (c in message.toUpperCase().toCharArray()) {
            when (c) {
                'A' -> morseCodeBuilder.append(".- ")
                'B' -> morseCodeBuilder.append("-... ")
                'C' -> morseCodeBuilder.append("-.-. ")
                'D' -> morseCodeBuilder.append("-.. ")
                'E' -> morseCodeBuilder.append(". ")
                'F' -> morseCodeBuilder.append("..-. ")
                'G' -> morseCodeBuilder.append("--. ")
                'H' -> morseCodeBuilder.append(".... ")
                'I' -> morseCodeBuilder.append(".. ")
                'J' -> morseCodeBuilder.append(".--- ")
                'K' -> morseCodeBuilder.append("-.- ")
                'L' -> morseCodeBuilder.append(".-.. ")
                'M' -> morseCodeBuilder.append("-- ")
                'N' -> morseCodeBuilder.append("-. ")
                'O' -> morseCodeBuilder.append("--- ")
                'P' -> morseCodeBuilder.append(".--. ")
                'Q' -> morseCodeBuilder.append("--.- ")
                'R' -> morseCodeBuilder.append(".-. ")
                'S' -> morseCodeBuilder.append("... ")
                'T' -> morseCodeBuilder.append("- ")
                'U' -> morseCodeBuilder.append("..- ")
                'V' -> morseCodeBuilder.append("...- ")
                'W' -> morseCodeBuilder.append(".-- ")
                'X' -> morseCodeBuilder.append("-..- ")
                'Y' -> morseCodeBuilder.append("-.-- ")
                'Z' -> morseCodeBuilder.append("--.. ")
                ' ' -> morseCodeBuilder.append(" ")
            }
        }
        return morseCodeBuilder.toString()
    }

    private fun vibrateMorseCode(morseCode: String) {
        // Vibrate according to Morse code
        for (c in morseCode.toCharArray()) {
            when (c) {
                '.' -> shortVibrate() // Short vibration for dot
                '-' -> longVibrate()  // Long vibration for dash
                ' ' -> pauseVibration() // Pause for space between letters
            }
        }
    }

    private fun shortVibrate() {
        vibrator.vibrate(200) // Vibrate for 200 milliseconds for dot
    }

    private fun longVibrate() {
        vibrator.vibrate(500) // Vibrate for 500 milliseconds for dash
    }

    private fun pauseVibration() {
        try {
            Thread.sleep(500) // Pause for 500 milliseconds for space between letters
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}