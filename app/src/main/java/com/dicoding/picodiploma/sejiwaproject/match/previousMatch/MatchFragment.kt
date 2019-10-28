package com.dicoding.picodiploma.sejiwaproject.match.previousMatch

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
import com.dicoding.picodiploma.sejiwaproject.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.main.PageViewModel
import com.dicoding.picodiploma.sejiwaproject.model.previousMatch.Matchs
import com.google.gson.Gson

/**
 * A placeholder fragment containing a simple view.
 */
class MatchFragment : Fragment(),
    MatchView {
    private lateinit var rvMatch: RecyclerView
    private lateinit var pageViewModel: PageViewModel
    private lateinit var presenter: MatchPresenter

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
        presenter = MatchPresenter(
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
        fun newInstance( idLeague: String): MatchFragment {
            return MatchFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_LEAGUE, idLeague)
                }
            }
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showPreviousMatch(data: List<Matchs>) {
        Log.d("size","" + data.size )
        rvMatch.layoutManager = LinearLayoutManager(context)
        val listLeagueAdapter =
            MatchLeagueAdapter(data)
        rvMatch.adapter = listLeagueAdapter
    }
}