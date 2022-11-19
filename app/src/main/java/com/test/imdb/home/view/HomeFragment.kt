package com.test.imdb.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.imdb.R
import com.test.imdb.common.adapter.event.MovieAdapter
import com.test.imdb.common.datamodel.Movie
import com.test.imdb.common.dialog.ErrorDialog
import com.test.imdb.common.dialog.LoadingDialog
import com.test.imdb.home.HomeContract
import com.test.imdb.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), HomeContract.View {

    private val presenter: HomePresenter by inject()
    private val movies = mutableListOf<Movie>()
    private var loadingDialog: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
//        presenter.init()
    }

    private fun initViews() {
//        presenter.setView(this)
        rvMovies.let {
            val adapter = MovieAdapter(movies)
            adapter.onItemClickListener = object : MovieAdapter.OnItemClickListener {
                override fun onClick(event: Movie) {
                    //Todo: Implement Detail View
                }
            }
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
    }

    override fun setMovies(movieList: List<Movie>) {
        GlobalScope.launch(Dispatchers.Main) {
            movies.clear()
            movies.addAll(movieList)
            rvMovies.adapter?.notifyDataSetChanged()
        }
    }


    override fun showErrorMessage() {
        GlobalScope.launch(Dispatchers.Main) {
            val errorDialog = ErrorDialog()
            errorDialog.show(parentFragmentManager, "")
        }
    }

    override fun showLoadingDialog() {
        GlobalScope.launch(Dispatchers.Main) {
            if (loadingDialog == null) {
                loadingDialog = LoadingDialog()
            }
            if (!loadingDialog!!.isVisible) {
                loadingDialog!!.show(requireActivity().supportFragmentManager, "")
            }
        }
    }

    override fun hideLoadingDialog() {
        GlobalScope.launch(Dispatchers.Main) {
            loadingDialog?.dismissAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
//        presenter.detachView()
        super.onDestroyView()
    }
}