package com.dicoding.picodiploma.sejiwaproject.features.player


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.features.player.model.Player
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_grid_player.*

/**
 * A simple [Fragment] subclass.
 */
class GridPlayerFragment : Fragment(), PlayerView {
    private lateinit var rvPlayer: RecyclerView
    private lateinit var presenter: PlayerPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_grid_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridPlayerAdapter = PlayerAdapter(mutableListOf())

        rvPlayer = view.findViewById(R.id.rv_grid_player)
        rvPlayer.setHasFixedSize(true)
        rvPlayer.layoutManager = GridLayoutManager(context, 4)
        rvPlayer.adapter = gridPlayerAdapter

        val id = arguments?.getString(ID_TEAM)

        val request = ApiRepository()
        val gson = Gson()
        presenter =
            PlayerPresenter(
                this,
                request,
                gson
            )
        presenter.getPlayerLogo(id ?: "133690")
    }

    companion object {

        private const val ID_TEAM = "id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(idTeam: String): GridPlayerFragment {
            return GridPlayerFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ID_TEAM, idTeam)
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

    override fun showPlayerDetail(data: List<Player>) {
        progress_bar.invisible()
        rvPlayer.adapter = PlayerAdapter(data)
    }

}
