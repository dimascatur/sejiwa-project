package com.dicoding.picodiploma.sejiwaproject.features.match.next


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
import com.dicoding.picodiploma.sejiwaproject.features.match.next.model.NextMatch
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.*

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(),
    NextMatchView {
    private lateinit var rvMatch: RecyclerView
    private lateinit var presenter: NextMatchPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMatch = view.findViewById(R.id.rv_next_match)
        rvMatch.setHasFixedSize(true)

        val id = arguments?.getString(ID_LEAGUE)

        val request = ApiRepository()
        val gson = Gson()
        presenter =
            NextMatchPresenter(
                this,
                request,
                gson
            )
        presenter.getNextMatch(id ?: "4328")


    }

    companion object {

        private const val ID_LEAGUE = "id"

        @JvmStatic
        fun newInstance( idLeague: String): NextMatchFragment {
            return NextMatchFragment()
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

    override fun showNextMatch(data: List<NextMatch>) {
        rvMatch.layoutManager = LinearLayoutManager(context)
        val listLeagueAdapter =
            NextMatchAdapter(data)
        rvMatch.adapter = listLeagueAdapter
    }
}


