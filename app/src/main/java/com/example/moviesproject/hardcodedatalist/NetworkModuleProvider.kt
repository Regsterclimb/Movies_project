package com.example.moviesproject.hardcodedatalist

import com.example.moviesproject.data.remote.NetworkModule.NetworkModuleResponses

interface NetworkModuleProvider {

    fun provideNetworkModule() : NetworkModuleResponses
}