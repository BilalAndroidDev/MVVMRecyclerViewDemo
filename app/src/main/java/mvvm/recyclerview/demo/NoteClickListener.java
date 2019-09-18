package mvvm.recyclerview.demo;

public interface NoteClickListener {
    void noteClickListener(Integer position);
    void noteDeleteClicked(Integer position);
}
