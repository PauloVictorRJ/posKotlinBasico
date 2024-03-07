package br.edu.projetofinalkotlin.view.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.databinding.ActivityEditTaskBinding
import br.edu.projetofinalkotlin.model.Task
import br.edu.projetofinalkotlin.viewmodel.task.EditTaskViewModel

class EditTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTaskBinding
    private val viewModel: EditTaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureBackBtn()
        configureSaveBtn()
    }

    private fun registerNewTask() {
        val title = binding.taskTitleTiet.text.toString()
        val description = binding.taskDescriptionTiet.text.toString()

        if (title.isNotEmpty() && description.isNotEmpty()) {
            viewModel.insertTaskViewModel(Task(null, title, description))
            sendToast(true)
            startActivity(Intent(this, TaskActivity::class.java))
        } else {
            sendToast(false)
        }
    }

    private fun sendToast(success: Boolean) {
        if (success) {
            showMessage(getString(R.string.edit_task_toast_success_txt))
        } else {
            showMessage(getString(R.string.edit_task_toast_error_txt))
        }
    }

    private fun configureBackBtn() {
        binding.backBtn.setOnClickListener {
            this.finish()
        }
    }

    private fun configureSaveBtn() {
        binding.saveBtn.setOnClickListener {
            registerNewTask()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this@EditTaskActivity, message, Toast.LENGTH_SHORT).show()
    }
}