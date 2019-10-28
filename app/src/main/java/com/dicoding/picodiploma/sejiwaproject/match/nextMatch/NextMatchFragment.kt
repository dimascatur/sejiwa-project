package com.dicoding.picodiploma.sejiwaproject.match.nextMatch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.main.PageViewModel
import com.dicoding.picodiploma.sejiwaproject.model.nextMatch.NextMatch
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(),
    NextMatchView {
    private lateinit var rvMatch: RecyclerView
    private lateinit var pageViewModel: PageViewModel
    private lateinit var presenter: NextMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_next_match, container, false)
        pageViewModel.text.observe(this, Observer<String> {})
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMatch = view.findViewById(R.id.rv_next_match)
        rvMatch.setHasFixedSize(true)

        val id = arguments?.getString(ID_LEAGUE)

        val request = ApiRepository()
        val gson = Gson()
        presenter = NextMatchPresenter(
            this,
            request,
            gson
        )
        presenter.getNextMatch(id ?: "4328")


    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ID_LEAGUE = "id"

        @JvmStatic
        fun newInstance( idLeague: String): NextMatchFragment {
            return NextMatchFragment().apply {
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

    override fun showNextMatch(data: List<NextMatch>) {
        rvMatch.layoutManager = LinearLayoutManager(context)
        val listLeagueAdapter =
            NextMatchAdapter(data)
        rvMatch.adapter = listLeagueAdapter
    }
}


