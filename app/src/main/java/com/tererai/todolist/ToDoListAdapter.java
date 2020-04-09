package com.tererai.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {

    private final LayoutInflater mInflator;
    private List<ToDo> mToDos; //Cached copy of ToDos

    ToDoListAdapter(Context context){
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.recyclerview_item, parent, false);
        return new ToDoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        if(mToDos!=null){
            ToDo current = mToDos.get(position);
            holder.toDoItemView.setText(current.getTodo());
            holder.textViewDetail.setText(current.getTodoDetail());
            holder.textViewDate.setText(current.getDate());
        }else{
            holder.toDoItemView.setText("No ToDo");
        }
    }

    void setToDos(List<ToDo> toDos){
        mToDos = toDos;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).


    @Override
    public int getItemCount() {
        if(mToDos!=null){
            return mToDos.size();
        }else {
            return 0;
        }
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder{

        @BindView (R.id.textView) TextView toDoItemView;
        @BindView(R.id.textViewDetail) TextView textViewDetail;
        @BindView(R.id.textViewDate) TextView textViewDate;

        private ToDoViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
