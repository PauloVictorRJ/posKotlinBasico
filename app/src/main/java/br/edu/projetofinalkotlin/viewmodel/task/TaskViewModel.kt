package br.edu.projetofinalkotlin.viewmodel.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import br.edu.projetofinalkotlin.model.Task
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import br.edu.projetofinalkotlin.model.repository.TaskRepository
import br.edu.projetofinalkotlin.model.room.TaskDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository

    init {
        val dao = TaskDatabase.getDatabase(application).getTaskDao()
        val firebaseDB = Firebase.database.reference
        repository = TaskRepository(dao, firebaseDB)
    }

    val allTask: LiveData<List<Task>> = repository.allTask.asLiveData()

    fun deleteTaskViewModel(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTaskRepository(task)
    }
}