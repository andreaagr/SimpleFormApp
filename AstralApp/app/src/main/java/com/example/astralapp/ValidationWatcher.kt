package com.example.astralapp

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

class ValidationWatcher(
    private val textInputLayout: TextInputLayout
) : TextWatcher {

    private val context = textInputLayout.context

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        textInputLayout.error = null
    }

    override fun afterTextChanged(p0: Editable?) {
        when(textInputLayout.id) {
            R.id.postalCodeInputLayout -> validatePostalCode(textInputLayout.editText?.text.toString())
        }
    }

    private fun validatePostalCode(postalCode: String) {
        if (postalCode.length > 4) {
            textInputLayout.error = context.getString(R.string.postal_code_error)
        }
    }
}