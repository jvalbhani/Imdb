package com.test.imdb.common.room.dao

import androidx.room.*
import com.test.imdb.common.room.entity.RoomEvent
import com.test.imdb.common.room.entity.RoomEventList
import com.test.imdb.common.room.entity.RoomHome
import com.test.imdb.common.room.entity.RoomHomeWithEvents

@Dao
interface HomeDao {

    @Transaction
    @Query("SELECT * FROM home")
    suspend fun loadAll(): List<RoomHomeWithEvents>

    @Transaction
    suspend fun deleteAll() {
        deleteHome()
        deleteEventList()
        deleteEvent()
    }

    @Transaction
    @Query("Select * FROM event WHERE slug in (:slugs)")
    suspend fun loadEvents(slugs: List<String>): List<RoomEvent>

    @Transaction
    suspend fun saveAll(home: RoomHomeWithEvents) {
        insert(home.roomHome)
        home.roomEventListWithEvent?.roomEventList?.let { insert(it) }
        home.roomEventListWithEvent?.masterList?.forEach { insert(it) }
    }

    @Query("DELETE FROM home")
    suspend fun deleteHome()

    @Query("DELETE FROM eventlist")
    suspend fun deleteEventList()

    @Query("DELETE FROM event")
    suspend fun deleteEvent()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(home: RoomHome)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventList: RoomEventList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: RoomEvent)
}