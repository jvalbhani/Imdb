package com.test.imdb.home.presenter

import com.test.imdb.home.repository.HomeRepository
import com.test.imdb.common.datamodel.Home
import com.test.imdb.home.HomeContract
import com.test.imdb.home.repository.DataSource
import org.koin.core.KoinComponent

class HomePresenter(
    private val repository: HomeRepository
) : HomeContract.Presenter, KoinComponent {

    private var view: HomeContract.View? = null

    override fun init() {

    }

    override fun loadHomePage() {
        view?.showLoadingDialog()
        repository.getData(
            hashMapOf(
                Pair("api_key", "38a73d59546aa378980a88b645f487fc"),
                Pair("language", "en-US"),
                Pair("city", "1")
            ),
            object : DataSource.Callback<Home> {
                override fun onSuccess(responseCode: Int, data: Home?, message: String?) {

                }

                override fun onFailure(errorCode: Int, message: String?) {
                    view?.hideLoadingDialog()
                    view?.showErrorMessage()
                }
            },
            forceRemoteFetch = true
        )
    }

    override fun setView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

}