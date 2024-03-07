package br.edu.projetofinalkotlin.view.task.task_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.model.Task

class TaskRvAdapter(private val action: (Task) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffUtil = AsyncListDiffer(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskRvViewHolder(
            inflater.inflate(R.layout.task_rv_item, parent, false), action
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TaskRvViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount() = diffUtil.currentList.size

    fun updateList(items: List<Task>) {
        diffUtil.submitList(items)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}