import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {
    private val _movieName = MutableLiveData<String>()
    val movieName: LiveData<String> = _movieName

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _resultImage = MutableLiveData<Int>()
    val resultImage: LiveData<Int> = _resultImage


    fun setMovieName(name: String) {
        _movieName.value = name
    }

    fun setDate(date : String){
        _date.value = date
    }
    fun setResultImage(imageResId: Int) {
        _resultImage.value = imageResId
    }
}
