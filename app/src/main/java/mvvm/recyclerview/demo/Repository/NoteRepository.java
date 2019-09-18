package mvvm.recyclerview.demo.Repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

import mvvm.recyclerview.demo.Database.NoteDao;
import mvvm.recyclerview.demo.POJOModels.Note;

public class NoteRepository implements NoteDao {

    private NoteDao mNoteDao;
    Application mApplication;

    public NoteRepository(NoteDao mNoteDao, Application mApplication) {
        this.mNoteDao = mNoteDao;
        this.mApplication = mApplication;
    }

    @Override
    public void insert(Note note) {
        mNoteDao.insert(note);
    }

    @Override
    public void update(Note note) {
        mNoteDao.update(note);
    }

    @Override
    public void delete(Note note) {
        mNoteDao.delete(note);
    }

    @Override
    public void deleteAll() {
        mNoteDao.deleteAll();
    }

    @Override
    public LiveData<List<Note>> getAllNote() {
        return mNoteDao.getAllNote();
    }
}
