package br.edu.projetofinalkotlin.viewmodel.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.projetofinalkotlin.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import br.edu.projetofinalkotlin.model.repository.TaskRepository
import br.edu.projetofinalkotlin.model.room.TaskDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EditTaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository

    init {
        val dao = TaskDatabase.getDatabase(application).getTaskDao()
        val firebaseDB = Firebase.database.reference
        repository = TaskRepository(dao, firebaseDB)
    }

    fun insertTaskViewModel(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTaskRepository(task)
    }
}