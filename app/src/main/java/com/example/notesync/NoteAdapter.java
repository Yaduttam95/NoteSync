package com.example.notesync;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private OnNoteClickListener onNoteClickListener;

    public NoteAdapter(List<Note> noteList, OnNoteClickListener onNoteClickListener) {
        this.noteList = noteList;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        int noteCount = position + 1;
        holder.textNoteCount.setText(String.valueOf(noteCount));

        Note note = noteList.get(position);
        holder.textTitle.setText(note.getTitle());

        String noteText = note.getNote();
        if (noteText.length() > 40) {
            noteText = noteText.substring(0, 40) + "...";
        }
        holder.textNote.setText(noteText);
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public Note getItem(int position) {
        return noteList.get(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textTitle;
        TextView textNote;

        TextView textNoteCount;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textNote = itemView.findViewById(R.id.textNote);
            textNoteCount = itemView.findViewById(R.id.textNoteCount);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (onNoteClickListener != null) {
                onNoteClickListener.onNoteClick(position);
            }
        }


    }

    public interface OnNoteClickListener {
        void onNoteClick(int position);
    }
}
