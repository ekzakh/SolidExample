package com.ekzak.solidexample.services

import android.util.Log

interface DataBase {
    fun save(data: String)
}

class MySQLBase : DataBase {
    override fun save(data: String) {
        Log.d("checkData", "MySQLBase save $data")
    }
}
