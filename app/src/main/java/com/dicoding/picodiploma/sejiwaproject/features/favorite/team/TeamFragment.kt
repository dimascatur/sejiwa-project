package com.dicoding.picodiploma.sejiwaproject.features.favorite.team


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.db.FavoriteTeam
import com.dicoding.picodiploma.sejiwaproject.db.database
import com.dicoding.picodiploma.sejiwaproject.features.team.TeamActivity
import com.dicoding.picodiploma.sejiwaproject.features.team.TeamActivity.Companion.EXTRA_TEAM
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {
    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var rvMatch: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter =
            TeamAdapter(
                favorites
            ) {
                context?.startActivity<TeamActivity>(EXTRA_TEAM to it.idTeam)
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
            val result = select(FavoriteTeam.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isEmpty()){
                txt_favorite.visible()
                rvMatch.invisible()
            } else {
                txt_favorite.invisible()
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
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMatch = view.findViewById(R.id.rv_favorite_team)

    }

    companion object {

        private const val ID_TEAM = "id"

        @JvmStatic
        fun newInstance( teamId: String): TeamFragment {
            return TeamFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ID_TEAM, teamId)
                    }
                }
        }
    }

}
