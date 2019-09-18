package mvvm.recyclerview.demo.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mvvm.recyclerview.demo.NoteClickListener;
import mvvm.recyclerview.demo.POJOModels.Note;
import mvvm.recyclerview.demo.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> noteList = new ArrayList<>();
    private NoteClickListener mNoteClickListener;


    public NoteAdapter(NoteClickListener mNoteClickListener) {
        this.mNoteClickListener = mNoteClickListener;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemNoteView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_adapter_view, parent, false);
        return new NoteHolder(itemNoteView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note ObjNote = noteList.get(position);

        holder.txtTitle.setText(ObjNote.getTitle());
        holder.txtDescription.setText(ObjNote.getDescription());
        holder.txtPriority.setText(ObjNote.getPriority() + "");
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public Note deleteNote(Integer position){
        return noteList.get(position);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtDescription, txtPriority;
        private CardView cardViewNoteAdapter;
        private ImageView imgDeleteNote;

        NoteHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtPriority = itemView.findViewById(R.id.txtPriority);
            cardViewNoteAdapter = itemView.findViewById(R.id.cardViewNoteAdapter);
            imgDeleteNote = itemView.findViewById(R.id.imgDeleteNote);

            cardViewNoteAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNoteClickListener.noteClickListener(getAdapterPosition());
                }
            });

            imgDeleteNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNoteClickListener.noteDeleteClicked(getAdapterPosition());
                }
            });

        }
    }
}
