package haonan.tech.experment4.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import haonan.tech.experment4.entity.Sc
import haonan.tech.experment4.repository.ScRepository

class ScVIewModel(application: Application) : AndroidViewModel(application) {
    private var scRepository: ScRepository = ScRepository(context = application)

    fun getAllScsLive(sno: Int):LiveData<List<Sc>>{
        return scRepository.getAllScsLive(sno)
    }

    fun insertScs(vararg param: Sc){
        scRepository.insertScs(*param)
    }

    fun updateScs(vararg param: Sc){
        scRepository.updateScs(*param)
    }

    fun deleteAllScs(){
        scRepository.deleteAllScs()
    }

    fun deleteScBySnoAndCno(sc:Sc){
        scRepository.deleteScBySnoAndCno(sc)
    }


}