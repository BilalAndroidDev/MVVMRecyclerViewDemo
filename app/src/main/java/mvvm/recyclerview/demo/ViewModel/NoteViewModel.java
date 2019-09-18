package mvvm.recyclerview.demo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import mvvm.recyclerview.demo.Database.NoteDatabase;
import mvvm.recyclerview.demo.Repository.NoteRepository;
import mvvm.recyclerview.demo.POJOModels.Note;

public class NoteViewModel extends AndroidViewModel {
    NoteRepository mNoteRepository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        // Initializing Repository
        mNoteRepository = new NoteRepository(NoteDatabase.getInstance(application).noteDao(), application);
    }

    public void insert(Note note) {
        mNoteRepository.insert(note);
    }

    public void update(Note note) {
        mNoteRepository.update(note);
    }

    public void delete(Note note) {
        mNoteRepository.delete(note);
    }

    public void deleteAllNote() {
        mNoteRepository.deleteAll();
    }

    public LiveData<List<Note>> getAllNote() {
        return mNoteRepository.getAllNote();
    }
}
