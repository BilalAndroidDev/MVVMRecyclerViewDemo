package mvvm.recyclerview.demo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import mvvm.recyclerview.demo.Adapters.NoteAdapter;
import mvvm.recyclerview.demo.NoteClickListener;
import mvvm.recyclerview.demo.POJOModels.Note;
import mvvm.recyclerview.demo.R;
import mvvm.recyclerview.demo.ViewModel.NoteViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NoteClickListener {

    private static final String TAG = NoteViewModel.class.getSimpleName();

    NoteViewModel mNoteViewModel;
    RecyclerView noteRecyclerView;
    NoteAdapter mNoteAdapter;
    FloatingActionButton fabNoteMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteAdapter = new NoteAdapter(this);

        initViewModel();
        initUI();
        initToolbar();
    }
    
    void initViewModel() {

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        mNoteViewModel.getAllNote().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                mNoteAdapter.setNoteList(notes);
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void initUI() {
        noteRecyclerView = findViewById(R.id.noteRecyclerView);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteRecyclerView.setHasFixedSize(true);

        fabNoteMain = findViewById(R.id.fabNoteMain);
        fabNoteMain.setOnClickListener(this);

        noteRecyclerView.setAdapter(mNoteAdapter);
    }

    void initToolbar() {
        Toolbar toolbarAddNote = findViewById(R.id.toolbarMainNote);
        toolbarAddNote.setTitle("Notes");
        toolbarAddNote.inflateMenu(R.menu.main_menu);

        toolbarAddNote.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemDeleteAll:
                        mNoteViewModel.deleteAllNote();
                    default:
                        return true;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabNoteMain:
                Intent addNoteIntent = new Intent(this, AddNoteActivity.class);
                startActivity(addNoteIntent);
        }
    }

    @Override
    public void noteClickListener(Integer position) {
        Toast.makeText(this, mNoteAdapter.deleteNote(position).getTitle() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noteDeleteClicked(Integer position) {
        mNoteViewModel.delete(mNoteAdapter.deleteNote(position));
    }
}
