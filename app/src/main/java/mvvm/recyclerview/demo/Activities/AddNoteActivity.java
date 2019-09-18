package mvvm.recyclerview.demo.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import mvvm.recyclerview.demo.POJOModels.Note;
import mvvm.recyclerview.demo.R;
import mvvm.recyclerview.demo.ViewModel.NoteViewModel;

public class AddNoteActivity extends AppCompatActivity{

    private static final String TAG = AddNoteActivity.class.getSimpleName();

    TextInputEditText edtTitle, edtDescription;
    TextInputLayout textInputLayoutTitle, textInputLayoutDescription;
    NumberPicker numberPickerPriority;
    NoteViewModel mNoteViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initUI();
        initToolbar();

        //Initialize ViewModel
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
    }

    void initUI() {
        edtTitle = findViewById(R.id.edtTitle);
        edtDescription = findViewById(R.id.edtDescription);

        numberPickerPriority = findViewById(R.id.numberPickerPriority);
        numberPickerPriority.setMinValue(0);
        numberPickerPriority.setMaxValue(10);
        numberPickerPriority.setWrapSelectorWheel(true);

        textInputLayoutTitle = findViewById(R.id.textInputLayoutTitle);
        textInputLayoutDescription= findViewById(R.id.textInputLayoutDescription);
    }

    void initToolbar() {
        Toolbar toolbarAddNote = findViewById(R.id.toolbarAddNote);
        toolbarAddNote.setTitle("Add Note");
        toolbarAddNote.setNavigationIcon(R.drawable.ic_left_arrow);
        toolbarAddNote.inflateMenu(R.menu.add_note_menu);

        toolbarAddNote.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbarAddNote.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemSaveNote:
                        saveNote();

                    default:
                        return true;
                }
            }
        });
    }

    void saveNote() {

        String title = edtTitle.getText().toString();
        String description = edtDescription.getText().toString();
        Integer priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            textInputLayoutTitle.setError("Enter Title");
            textInputLayoutDescription.setError("Enter Description");
            return;
        }

        //Adding all data to Database through ViewModel >> Repository >> Database
        mNoteViewModel.insert(new Note(title, description, priority));

        finish();
    }
}
