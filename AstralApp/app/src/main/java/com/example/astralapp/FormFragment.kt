package com.example.astralapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.astralapp.databinding.FragmentFormBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private lateinit var simpleDateFormat: SimpleDateFormat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFormBinding
            .inflate(inflater, container, false)
            .apply {
                binding = this
            }.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        simpleDateFormat = SimpleDateFormat(
            getString(R.string.calendar_date_format),
            Locale.getDefault()
        )
        populateHobbies()
        setButtonListener()
        setDateListener()
        setTextWatcher()
    }

    private fun populateHobbies() {
        val items = listOf(
            getString(R.string.movies_hobby),
            getString(R.string.cooking_hobby),
            getString(R.string.sports_hobby),
            getString(R.string.photo_hobby),
            getString(R.string.board_games_hobby),
            getString(R.string.modeling_hobby),
            getString(R.string.music_hobby),
            getString(R.string.theater_hobby),
            getString(R.string.videogames_hobby),
            getString(R.string.yoga_hobby)
        )
        val adapter = ArrayAdapter(this.requireContext(), R.layout.list_item, items)
        (binding.favoriteHobbyInputLayout.editText as AutoCompleteTextView).setAdapter(adapter)
    }

    private fun validateEmptyFields(textInputLayouts: List<TextInputLayout>): Boolean {
        var emptyFields = false
        textInputLayouts.forEach { textInputLayout ->
            if (textInputLayout.editText?.text.isNullOrEmpty()) {
                textInputLayout.error = getString(R.string.empty_field_error)
                emptyFields = true
            }
        }
        return emptyFields
    }

    private fun setButtonListener() {
        with(binding) {
            continueButton.setOnClickListener {
                if (!validateEmptyFields(
                        listOf(
                            fullNameInputLayout,
                            birthDateInputLayout,
                            postalCodeInputLayout,
                            favoriteHobbyInputLayout
                        )
                    )
                ) {
                    FormFragmentDirections
                        .actionFormFragmentToDetailsFragment(generateUser())
                        .let {
                            findNavController().navigate(it)
                        }
                }
            }
        }
    }

    private fun generateUser(): User {
        with(binding) {
            return User(
                fullNameEditText.text.toString(),
                stringToDate(birthDateEditText.text.toString()),
                postalCodeEditText.text.toString(),
                favoriteHobbyEditText.text.toString()
            )
        }
    }

    private fun setDateListener() {
        binding.birthDateEditText.setOnClickListener {
            showDateDialogPicker()
        }
    }

    private fun showDateDialogPicker() {
        val datePicker =
            MaterialDatePicker
                .Builder
                .datePicker()
                .setCalendarConstraints(createCalendarConstraints())
                .setTitleText(getString(R.string.calendar_entry_message))
                .build()

        datePicker.show(childFragmentManager, getString(R.string.calendar_fragment_tag))
        datePicker.addOnPositiveButtonClickListener {
            binding.birthDateEditText.setText(dateToString(it))
        }
    }

    private fun setTextWatcher() {
        with(binding) {
            postalCodeEditText.addTextChangedListener(ValidationWatcher(postalCodeInputLayout))
            fullNameEditText.addTextChangedListener(ValidationWatcher(fullNameInputLayout))
            birthDateEditText.addTextChangedListener(ValidationWatcher(birthDateInputLayout))
            favoriteHobbyEditText.addTextChangedListener(ValidationWatcher(favoriteHobbyInputLayout))
        }
    }

    private fun createCalendarConstraints(): CalendarConstraints {
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val calendar = Calendar.getInstance(TimeZone.getTimeZone(getString(R.string.utc_timezone)))
        calendar.timeInMillis = today
        calendar[Calendar.YEAR] = 1950
        val startDate = calendar.timeInMillis

        calendar.timeInMillis = today
        calendar[Calendar.YEAR] = 2022
        val endDate = calendar.timeInMillis

        return CalendarConstraints.Builder()
            .setOpenAt(endDate)
            .setStart(startDate)
            .setEnd(endDate)
            .build()
    }

    private fun stringToDate(string: String) = simpleDateFormat.parse(string)

    private fun dateToString(date: Long) = simpleDateFormat.format(date)
}