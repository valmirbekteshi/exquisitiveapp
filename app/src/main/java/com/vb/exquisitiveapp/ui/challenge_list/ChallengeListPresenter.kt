package com.vb.exquisitiveapp.ui.challenge_list

import android.content.Context
import android.util.Log
import com.vb.exquisitiveapp.db.ChallengeDatabase
import com.vb.exquisitiveapp.model.Challenge
import com.vb.exquisitiveapp.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ChallengeListPresenter(var view: ChallengeListView, context: Context) {
    private val compositeDisposable = CompositeDisposable()
    private val challengesDao = ChallengeDatabase.getDatabase(context).challengeDao()

    private fun getChallengesFromApi() {

        compositeDisposable.add(
            ApiInterface.create().getChallanges().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {

                    if (!it.challenges.isNullOrEmpty()) {
                        val challengeList = mutableListOf<Challenge>()
                        for(item in it.challenges){
                            if(item.show){
                                challengeList.add(item)
                            }
                        }

                        saveChallenges(challengeList)
                        view.showChallenges(challengeList)
                    }

                })

    }



    private fun saveChallenges(challenges: List<Challenge>) {
        compositeDisposable.add(challengesDao.addChallenges(challenges)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("valmir", "Success saving db {${challenges.size}}")
            }, {
                Log.d("valmir", "problem saving db")
            })
        )
    }


    fun getChallenges() {
        compositeDisposable.add(challengesDao.getAllChallengesFromDb().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!it.isNullOrEmpty()) {
                    view.showChallenges(it)
                    Log.d("valmir", "from db ${it.size}")
                } else {
                    Log.d("valmir", "null or empty getting from api")
                    getChallengesFromApi()
                }

            }, {
                Log.e("valmir", "{$it.localizedMessage}")
            })
        )
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}