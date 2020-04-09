package com.tererai.todolist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.List;

public class SummaryWidget extends LinearLayout {
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.txtProgress) TextView mProgressPercentage;
    public SummaryWidget(Context context) {
        super(context);
        initViews(context);
    }

    public SummaryWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public SummaryWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public void initViews(Context context){
        final LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.summary_view, this);
        setOrientation(VERTICAL);
        ButterKnife.bind(this);
    }

    public void display(Context context, List<ToDo> toDos){
        int totalPercentage = 100;
        int progressPercentage = 1;
        if (!toDos.isEmpty()) {
            int totalTodos = toDos.size();
            long progress = toDos.stream().filter(ToDo::getDoneStatus).count();
            progressPercentage = (int) progress * totalPercentage / totalTodos;
        }
        mProgressBar.setProgress(progressPercentage, true);
        mProgressPercentage.setText(context.getString(R.string.concat_percentage, progressPercentage));
    }
}
