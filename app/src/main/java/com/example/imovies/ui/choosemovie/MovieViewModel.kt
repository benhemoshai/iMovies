import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {
    private val _movieName = MutableLiveData<String>()
    val movieName: LiveData<String> = _movieName

    private val _resultImage = MutableLiveData<Int>()
    val resultImage: LiveData<Int> = _resultImage


    fun setMovieName(name: String) {
        _movieName.value = name
    }

    fun setResultImage(imageResId: Int) {
        _resultImage.value = imageResId
    }
}
