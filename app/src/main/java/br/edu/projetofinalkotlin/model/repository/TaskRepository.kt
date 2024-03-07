package br.edu.projetofinalkotlin.model.repository

import androidx.annotation.WorkerThread
import br.edu.projetofinalkotlin.model.Task
import br.edu.projetofinalkotlin.model.room.TaskDao
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao, private val firebaseDB: DatabaseReference) {

    @WorkerThread
    suspend fun insertTaskRepository(task: Task) {
        taskDao.insertTaskDao(task)
        val insertedId = taskDao.getLastInsertedTaskId()

        if (insertedId != null) {
            task.id = insertedId
            firebaseDB.child("task").child(insertedId.toString()).setValue(task)
        }
    }


    @WorkerThread
    suspend fun deleteTaskRepository(task: Task) {
        taskDao.deleteTaskDao(task.id)
        firebaseDB.child("task").child(task.id.toString()).removeValue()
    }

    val allTask: Flow<List<Task>> = taskDao.getAllTasksDao()
}
