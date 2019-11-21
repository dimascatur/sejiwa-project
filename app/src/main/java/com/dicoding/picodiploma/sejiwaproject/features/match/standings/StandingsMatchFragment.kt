package com.dicoding.picodiploma.sejiwaproject.features.match.standings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.features.match.standings.model.StandingsMatch
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_standings_match.*

/**
 * A simple [Fragment] subclass.
 */
class StandingsMatchFragment : Fragment(), StandingsMatchView {
    private lateinit var rvMatch: RecyclerView
    private lateinit var presenter: StandingsMatchPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listStandingMatchAdapter = StandingsMatchAdapter(mutableListOf())

        rvMatch = view.findViewById(R.id.rv_standings_match)
        rvMatch.setHasFixedSize(true)
        rvMatch.layoutManager = LinearLayoutManager(context)
        rvMatch.adapter = listStandingMatchAdapter

        val id = arguments?.getString(ID_LEAGUE)

        val request = ApiRepository()
        val gson = Gson()
        presenter =
            StandingsMatchPresenter(
                this,
                request,
                gson
            )
        presenter.getStandingsLeague(id ?: "4328")

    }

    companion object {

        private const val ID_LEAGUE = "id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(idLeague: String): StandingsMatchFragment {
            return StandingsMatchFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ID_LEAGUE, idLeague)
                    }
                }
        }
    }

    override fun showLoading() {
        progress_standings.visible()
    }

    override fun hideLoading() {
        progress_standings.invisible()
    }

    override fun showStandingsList(data: StandingsMatch) {
        progress_standings.invisible()
        (rvMatch.adapter as StandingsMatchAdapter).addStandingsMatch(data)

    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
    }
}
