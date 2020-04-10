package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.io.File
import java.util.*

private const val CRIME_LIST_FRAGMENT_TAG = "crime_list_fragment"

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = CrimeListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, CRIME_LIST_FRAGMENT_TAG)
                .commit()
        }
    }

    override fun onCrimeSelected(crimeId: UUID) {
        Log.d("Main Activity", "MainActivity.onCrimeSelected: $crimeId")
        val fragment = CrimeFragment.newInstance(crimeId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onPhotoSelected(photoFile: File) {
        Log.d("Main Activity", "MainActivity.onPhotoSelected")
        val fragment = CrimePhotoFragment.newInstance(photoFile)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
