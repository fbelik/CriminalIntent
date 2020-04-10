package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import getScaledBitmap
import java.io.File

private const val PHOTO_FILE = "photo_file"

class CrimePhotoFragment: Fragment() {

    private lateinit var photoView: ImageView
    private lateinit var photoContainer: androidx.constraintlayout.widget.ConstraintLayout

    private lateinit var photoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoFile = arguments?.getSerializable(PHOTO_FILE) as File
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_photo, container, false)

        photoView = view.findViewById(R.id.crime_photo_zoomed) as ImageView
        photoContainer = view.findViewById(R.id.zoomed_photo_container) as androidx.constraintlayout.widget.ConstraintLayout

        return view
    }

    override fun onStart() {
        super.onStart()

        photoView.setOnClickListener {
            getActivity()?.supportFragmentManager?.popBackStack()
        }

        photoContainer.setOnClickListener {
            getActivity()?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updatePhotoView()
    }

    private fun updatePhotoView() {
        if (photoFile.exists()) {
            val bitmap = getScaledBitmap(photoFile.path, requireActivity())
            photoView.setImageBitmap(bitmap)
        }
        else {
            photoView.setImageDrawable(null)
        }
    }

    companion object {
        fun newInstance(photoFile: File): CrimePhotoFragment {
            val args = Bundle().apply {
                putSerializable(PHOTO_FILE, photoFile)
            }
            return CrimePhotoFragment().apply {
                arguments = args
            }
        }
    }

}