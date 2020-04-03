package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val DATE = "date"

class TimePickerFragment: DialogFragment() {

    interface Callbacks {
        fun onTimeSelected(hour: Int, minute: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val timeListener = TimePickerDialog.OnTimeSetListener {
            _: TimePicker, Hour: Int, Minute: Int ->

            targetFragment?.let { fragment ->
                (fragment as Callbacks).onTimeSelected(Hour, Minute)
            }
        }

        val date = arguments?.get(DATE) as Date
        val hour = date.hours
        val minute = date.minutes

        return TimePickerDialog(
            requireContext(),
            timeListener,
            hour,
            minute,
            true
        )
    }

    companion object {
        fun newInstance(date: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(DATE, date)
            }

            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }

}