package com.dicoding.picodiploma.sejiwaproject.features.match.previous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.model.PreviousMatch
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_previous_match.*

/**
 * A placeholder fragment containing a simple view.
 */
class PreviousMatchFragment : Fragment(),
    PreviousMatchView {
    private lateinit var rvMatch: RecyclerView
    private lateinit var presenter: PreviousMatchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_previous_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listPreviousAdapter = PreviousMatchAdapter(mutableListOf())

        rvMatch = view.findViewById(R.id.rv_previous_match)
        rvMatch.setHasFixedSize(true)
        rvMatch.layoutManager = LinearLayoutManager(context)
        rvMatch.adapter = listPreviousAdapter

        val id = arguments?.getString(ID_LEAGUE)

        val request = ApiRepository()
        val gson = Gson()
        presenter =
            PreviousMatchPresenter(
                this,
                request,
                gson
            )
        presenter.getPreviousMatch(id ?: "4328")

    }

    companion object {

        private const val ID_LEAGUE = "id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(idLeague: String): PreviousMatchFragment {
            return PreviousMatchFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ID_LEAGUE, idLeague)
                    }
                }
        }
    }

    override fun showLoading() {
        progress_previous.visible()
    }

    override fun hideLoading() {
        progress_previous.invisible()
    }

    override fun matchReady(previousMatch: PreviousMatch) {
        progress_previous.invisible()
        (rvMatch.adapter as PreviousMatchAdapter).addPreviousMatch(previousMatch)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
    }
}