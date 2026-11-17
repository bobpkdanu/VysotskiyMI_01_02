package com.bignerdranch.android.vysotskiymi_01_02

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class StartActivity : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStart = view.findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GeometryActivity())
                .commit()
        }
    }
}