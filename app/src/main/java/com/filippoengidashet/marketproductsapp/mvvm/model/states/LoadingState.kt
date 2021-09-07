package com.filippoengidashet.marketproductsapp.mvvm.model.states

/**
 * @author Filippo 19/08/2021
 */
sealed class LoadingState {

    object LOADING : LoadingState()
    object FAILED : LoadingState()
    object DONE : LoadingState()
}
