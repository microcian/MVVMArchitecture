package com.nextbridge.roomdb.database

import androidx.room.*
import com.nextbridge.roomdb.entities.PhotoEntityDB

@Dao
interface PhotoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPhotosList(listPhotos: List<PhotoEntityDB>)

  @Update
  fun updatePhotos(photoModel: PhotoEntityDB)

//  @Query("SELECT * FROM PhotoModel WHERE id = :id_")
//  fun getPhotoById(id_: Int): Movie

  @Query("SELECT * FROM PhotoEntityDB")
  fun getAllPhotos(): List<PhotoEntityDB>
}
