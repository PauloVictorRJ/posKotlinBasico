package br.edu.projetofinalkotlin.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.edu.projetofinalkotlin.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskDao(task: Task)

    @Query("SELECT * from task order by id ASC")
    fun getAllTasksDao(): Flow<List<Task>>

    @Query("UPDATE task set title = :title, description = :description where id = :id")
    suspend fun updateTaskDao(id: Int?, title: String?, description: String?)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTaskDao(id: Int?)

    @Query("SELECT id FROM task ORDER BY id DESC LIMIT 1")
    suspend fun getLastInsertedTaskId(): Int?
}