package com.dicoding.picodiploma.sejiwaproject.features.team.player


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dicoding.picodiploma.sejiwaproject.R

/**
 * A simple [Fragment] subclass.
 */
class GridPlayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_player, container, false)
    }


}
