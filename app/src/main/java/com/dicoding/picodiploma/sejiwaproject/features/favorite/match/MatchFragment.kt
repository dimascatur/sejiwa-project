package com.dicoding.picodiploma.sejiwaproject.features.favorite.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.db.FavoriteMatch
import com.dicoding.picodiploma.sejiwaproject.db.database
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.DetailMatchActivity
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.DetailMatchActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.fragment_favorite_next.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity


/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment() {
    private var favorites: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteNextAdapter
    private lateinit var rvMatch: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter =
            FavoriteNextAdapter(
                favorites
            ) {
                context?.startActivity<DetailMatchActivity>(EXTRA_ID to it.matchId)
            }

        rvMatch.setHasFixedSize(true)
        rvMatch.layoutManager = LinearLayoutManager(context)
        rvMatch.adapter = adapter
        showFavorite()
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (favorite.isEmpty()){
                text_favorite.visible()
                rvMatch.invisible()
            } else {
                text_favorite.invisible()
                rvMatch.visible()
                favorites.addAll(favorite)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_next, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMatch = view.findViewById(R.id.rv_next_match)

    }

    companion object {

        private const val ID_LEAGUE = "id"

        @JvmStatic
        fun newInstance( matchId: String): MatchFragment {
            return MatchFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ID_LEAGUE, matchId)
                    }
                }
        }
    }
}
