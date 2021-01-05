package haonan.tech.experment4.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import haonan.tech.experment4.entity.Student
import haonan.tech.experment4.repository.StudentRepository

class StudentVIewModel(application: Application) : AndroidViewModel(application) {
    private var allStudentsLive:LiveData<List<Student>>
    private var studentRepository: StudentRepository = StudentRepository(context = application)

    fun getAllStudentsLive():LiveData<List<Student>>{
        return allStudentsLive
    }
    init {
        allStudentsLive = studentRepository.getAllStudentsLive()
    }
    fun insertStudents(vararg param: Student){
        studentRepository.insertStudents(*param)
    }

    fun updateStudents(vararg param: Student){
        studentRepository.updateStudents(*param)
    }

    fun deleteAllStudents(){
        studentRepository.deleteAllStudents()
    }

    fun deleteStudentBySno(sno: Int){
        studentRepository.deleteStudentBySno(sno)
    }
}