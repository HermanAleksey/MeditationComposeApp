//package com.example.meditationcomposeapp
//
//class MainViewModel constructor(
//    private val mainRepository: MainRepository
//    ) : ViewModel() {
//
//    val errorMessage = MutableLiveData<String>()
//    val movieList = MutableLiveData<List<Movie>>()
//    var job: Job? = null
//    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        onError("Exception handled: ${throwable.localizedMessage}")
//    }
//    val loading = MutableLiveData<Boolean>()
//
//    fun getAllMovies() {
//
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
//            loading.postValue(true)
//            val response = mainRepository.getAllMovies()
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    movieList.postValue(response.body())
//                    loading.value = false
//                } else {
//                    onError("Error : ${response.message()} ")
//                }
//            }
//        }
//
//    }
//
//    private fun onError(message: String) {
//        errorMessage.value = message
//        loading.value = false
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }
//
//}