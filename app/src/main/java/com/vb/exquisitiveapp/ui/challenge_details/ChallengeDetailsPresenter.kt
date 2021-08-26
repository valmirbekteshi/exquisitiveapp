package com.vb.exquisitiveapp.ui.challenge_details

import android.content.Context
import android.util.Log
import com.vb.exquisitiveapp.db.ChallengeDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ChallengeDetailsPresenter(var view: ChallengeDetailsView, context: Context) {

    private val compositeDisposable = CompositeDisposable()
    private val challengesDao = ChallengeDatabase.getDatabase(context).challengeDao()

    fun getChallengeById(id: Int) {
        compositeDisposable.add(
            challengesDao.getChallengeById(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showDetails(it)
                    Log.d("valmir", "Success geting challenge from db {${it.title}}")
                }, {
                    Log.d("valmir", "problem geting from db this challenge")
                })
        )
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

}