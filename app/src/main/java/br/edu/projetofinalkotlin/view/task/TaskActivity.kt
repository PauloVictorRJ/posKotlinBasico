package br.edu.projetofinalkotlin.view.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.projetofinalkotlin.databinding.ActivityTaskBinding
import br.edu.projetofinalkotlin.view.login.LoginActivity
import br.edu.projetofinalkotlin.view.task.task_rv.TaskRvAdapter
import br.edu.projetofinalkotlin.viewmodel.task.TaskViewModel

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private lateinit var adapter: TaskRvAdapter
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureBackBtn()
        configureAddTaskBtn()
        configureObservers()
        configureRv()
    }

    private fun configureBackBtn() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun configureAddTaskBtn() {
        binding.addTaskBtn.setOnClickListener {
            startActivity(Intent(this, EditTaskActivity::class.java))
        }
    }

    private fun configureObservers() {
        viewModel.allTask.observe(this) {
            adapter.updateList(it)
        }
    }

    private fun configureRv() {
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = TaskRvAdapter(action = {
            viewModel.deleteTaskViewModel(it)
        })
        binding.rvTasks.adapter = adapter
    }
}