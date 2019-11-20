package com.dicoding.picodiploma.sejiwaproject.features.team.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), TeamView {
    private lateinit var presenter: TeamPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString(TEAM_ID)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(
            this,
            request,
            gson
        )
        presenter.getDetailTeam(id ?: "133608")
    }

    companion object {

        private const val TEAM_ID = "id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(teamId: String): TeamFragment {
            return TeamFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(TEAM_ID, teamId)
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

    override fun showDetailTeam(data: Team) {
        progress_bar.invisible()
        Glide.with(this)
            .load(data.teamBadge)
            .into(team_badge)

        Glide.with(this)
            .load(data.teamJersey)
            .into(team_jersey)

        team_formed.text = data.teamFormed
        team_description.text = data.teamDescription
    }
}
