package br.edu.projetofinalkotlin.view.task.task_rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.projetofinalkotlin.R
import br.edu.projetofinalkotlin.model.Task
import com.google.android.material.button.MaterialButton

class TaskRvViewHolder(view: View, private val action: (Task) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.task_title)
    private val description: TextView = view.findViewById(R.id.task_description_txt)
    private val deleteAction: MaterialButton = view.findViewById(R.id.del_btn)

    fun bind(item: Task) {
        title.text = item.title
        description.text = item.description

        deleteAction.setOnClickListener {
            action.invoke(item)
        }
    }
}

