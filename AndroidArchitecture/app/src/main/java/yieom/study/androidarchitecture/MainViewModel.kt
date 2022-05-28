package yieom.study.androidarchitecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val message = MutableLiveData<String>()
    fun sendMessage(text:String) {
        message.value = text
    }
}