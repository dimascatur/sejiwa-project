package com.dicoding.picodiploma.sejiwaproject.features.match.previous

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.page.PageViewModel
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.model.Matchs
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.*

/**
 * A placeholder fragment containing a simple view.
 */
class PreviousMatchFragment : Fragment(),
    PreviousMatchView {
    private lateinit var rvMatch: RecyclerView
    private lateinit var pageViewModel: PageViewModel
    private lateinit var presenter: PreviousMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_previous_match, container, false)
        pageViewModel.text.observe(this, Observer<String> {})
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMatch = view.findViewById(R.id.rv_previous_match)
        rvMatch.setHasFixedSize(true)

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


        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ID_LEAGUE = "id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance( idLeague: String): PreviousMatchFragment {
            return PreviousMatchFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ID_LEAGUE, idLeague)
                }
            }
        }
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun showPreviousMatch(data: List<Matchs>) {
        Log.d("size","" + data.size )
        rvMatch.layoutManager = LinearLayoutManager(context)
        val listLeagueAdapter =
            PreviousMatchAdapter(
                data
            )
        rvMatch.adapter = listLeagueAdapter
    }
}