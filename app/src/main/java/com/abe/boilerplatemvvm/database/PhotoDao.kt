package com.abe.boilerplatemvvm.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abe.boilerplatemvvm.model.photos.PhotoModel

@Dao
interface PhotoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPhotosList(listPhotos: List<PhotoModel>)

  @Update
  fun updatePhotos(photoModel: PhotoModel)

//  @Query("SELECT * FROM PhotoModel WHERE id = :id_")
//  fun getPhotoById(id_: Int): Movie

  @Query("SELECT * FROM PhotoModel")
  fun getAllPhotos(): LiveData<List<PhotoModel>>
}
