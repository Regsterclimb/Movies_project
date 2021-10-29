package com.example.moviesproject.hardcodedatalist

import com.example.moviesproject.data.NetworkModule.NetworkModuleGetData

interface NetworkModuleProvider {
    fun provideNetworkModule() : NetworkModuleGetData
}