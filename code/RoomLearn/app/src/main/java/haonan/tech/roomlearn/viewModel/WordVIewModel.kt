package haonan.tech.roomlearn.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import haonan.tech.roomlearn.entity.Word
import haonan.tech.roomlearn.repository.WordRepository

class WordVIewModel(application: Application) : AndroidViewModel(application) {
    private var allWordsLive:LiveData<List<Word>>
    private var wordRepository: WordRepository = WordRepository(context = application)

    fun getAllWordsLive():LiveData<List<Word>>{
        return allWordsLive
    }
    init {
        allWordsLive = wordRepository.getAllWordsLive()
    }
    fun insertWords(vararg param: Word){
        wordRepository.insertWords(*param)
    }

    fun updateWords(vararg param: Word){
        wordRepository.updateWords(*param)
    }
    fun deleteWords(vararg param: Word){
        wordRepository.deleteWords(*param)
    }
    fun deleteAllWords(){
        wordRepository.deleteAllWords()
    }

}