package com.bignerdranch.android.vysotskiymi_01_02

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlin.math.PI

class GeometryActivity : Fragment() {
    private lateinit var spinnerShape: Spinner
    private lateinit var ivFormula: ImageView
    private lateinit var etVar1: EditText
    private lateinit var etVar2: EditText
    private lateinit var etVar3: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerShape = view.findViewById(R.id.spinnerShape)
        ivFormula = view.findViewById(R.id.ivFormula)
        etVar1 = view.findViewById(R.id.etVar1)
        etVar2 = view.findViewById(R.id.etVar2)
        etVar3 = view.findViewById(R.id.etVar3)
        val btnCalculate = view.findViewById<Button>(R.id.btnCalculate)

        val shapes = listOf("Треугольник", "Круг")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            shapes
        )
        spinnerShape.adapter = adapter

        spinnerShape.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                etVar1.text.clear()
                etVar2.text.clear()
                etVar3.text.clear()

                if (position == 0)
                {
                    ivFormula.setImageResource(R.drawable.pick2)
                    etVar1.hint = "Введите сторону a"
                    etVar2.visibility = View.VISIBLE
                    etVar3.visibility = View.VISIBLE
                }
                else
                {
                    ivFormula.setImageResource(R.drawable.pick1)
                    etVar1.hint = "Введите длину окружности (L)"
                    etVar2.visibility = View.GONE
                    etVar3.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnCalculate.setOnClickListener {
            calculateResult()
        }
    }

    private fun calculateResult() {
        val selectedShape = spinnerShape.selectedItemPosition

        try
        {
            if (selectedShape == 0)
            {
                val a = etVar1.text.toString().toDouble()
                val b = etVar2.text.toString().toDouble()
                val c = etVar3.text.toString().toDouble()

                val perimeter = a + b + c
                showAlert("Результат", "Периметр треугольника равен: $perimeter")
            }
            else
            {
                val l = etVar1.text.toString().toDouble()
                val radius = l / (2 * PI)

                showAlert("Результат", String.format("Радиус круга равен: %.2f", radius))
            }
        }
        catch (e: NumberFormatException) {
            showAlert("Ошибка", "Заполните всё числами")
        }
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("ОК", null)
            .show()
    }
}

