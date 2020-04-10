package com.tererai.todolist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tererai.todolist.R;
import com.tererai.todolist.data.model.ToDo;
import com.tererai.todolist.data.viewmodel.ToDoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {

    private final LayoutInflater mInflator;
    private List<ToDo> mToDos; //Cached copy of ToDos
    private Context mContext;
    private ToDoViewModel mToDoViewModel;

    public ToDoListAdapter(Context context, ToDoViewModel toDoViewModel) {
        mInflator = LayoutInflater.from(context);
        this.mContext = context;
        this.mToDoViewModel = toDoViewModel;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.recyclerview_item, parent, false);
        return new ToDoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {

        if (mToDos != null) {
            ToDo current = mToDos.get(position);
            holder.toDoItemView.setText(current.getTodo());
            holder.textViewDetail.setText(current.getTodoDetail());
            holder.textViewDate.setText(current.getDate());
            //delete action
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mToDoViewModel.deleteTaskById(current);
                }
            });
            //populate checkboxes

            holder.checkCompleted.setChecked(current.getDoneStatus());
            //toggle between between done and not done
            holder.checkCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    current.setDoneStatus(isChecked);
                    mToDoViewModel.updateStatus(current);
                }
            });

        } else {
            holder.toDoItemView.setText("No ToDo");
        }
    }

    public void setToDos(List<ToDo> toDos) {
        mToDos = toDos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mToDos != null) {
            return mToDos.size();
        } else {
            return 0;
        }
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView toDoItemView;
        @BindView(R.id.textViewDetail)
        TextView textViewDetail;
        @BindView(R.id.textViewDate)
        TextView textViewDate;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.completed)
        CheckBox checkCompleted;

        private ToDoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
