package com.example.albalog.data.repository

import com.example.albalog.data.dao.AlbaRegiDao
import com.example.albalog.data.entity.AlbaRegiEntity

class AlbaRepository(private val dao: AlbaRegiDao) {

    suspend fun insert(alba: AlbaRegiEntity) = dao.insertAlba(alba)

    fun getAllAlbas() = dao.getAllAlbas()
}
