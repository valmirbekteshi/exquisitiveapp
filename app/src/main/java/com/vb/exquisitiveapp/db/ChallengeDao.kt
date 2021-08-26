package com.vb.exquisitiveapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vb.exquisitiveapp.model.Challenge
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ChallengeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addChallenges(list: List<Challenge>): Completable

    @Query("SELECT * FROM challenge_table WHERE show ")
     fun getAllChallengesFromDb() : Single<List<Challenge>>

     @Query("SELECT * FROM challenge_table WHERE id LIKE :id")
     fun getChallengeById(id: Int): Flowable<Challenge>

}