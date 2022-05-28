package yieom.study.androidarchitecture

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class FirstViewModel : ViewModel() {
    private val count: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun getCount(): LiveData<Int> {
        return count
    }

    fun setCount(value: Int) {
        if (count.value == null) {
            count.value = 0
        } else {
            count.value = count.value?.plus(value)
        }
    }

    fun onClickCountDown() {
        setCount(-1)
    }

    var btnCountDown: String

    init {
        btnCountDown = "Count Down"
//       binding
    }
}