package com.test.imdb.home


import com.test.imdb.common.datamodel.Movie

class HomeContract {
    interface View {
        fun setMovies(eventList: List<Movie>)
        fun showErrorMessage()
        fun showLoadingDialog()
        fun hideLoadingDialog()
    }

    interface Presenter {
        fun init()
        fun loadHomePage()
        fun detachView()
        fun setView(view: View)
    }
}