package com.bignerdranch.android.criminalintent

import androidx.recyclerview.widget.DiffUtil

class CrimeDiffCallBack: DiffUtil.ItemCallback<Crime>() {

    override fun areContentsTheSame(oldItem: Crime, newItem: Crime): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areItemsTheSame(oldItem: Crime, newItem: Crime): Boolean {
        return (oldItem == newItem)
    }


}