package haonan.tech.test.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import haonan.tech.test.R
import haonan.tech.test.viewModel.StudentVIewModel

class StudentFragment : Fragment() {

    companion object {
        fun newInstance() = StudentFragment()
    }

    private lateinit var viewModel: StudentVIewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.student_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentVIewModel::class.java)
        // TODO: Use the ViewModel
    }

}