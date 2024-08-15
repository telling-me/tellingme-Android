package com.tellingus.tellingme.presentation.ui.feature.myspace

import com.tellingus.tellingme.domain.repository.DataStoreRepository
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MySpaceViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
): BaseViewModel<MySpaceContract.State, MySpaceContract.Event, MySpaceContract.Effect>(
    initialState = MySpaceContract.State()
) {

    override fun reduceState(event: MySpaceContract.Event) {

    }


}