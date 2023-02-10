package br.com.notes.repository;

import br.com.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesRepository {

    private List<Note> notes = new ArrayList<>();

    public Note inserirNaLista(Note note) {
        notes.add(note);

        return note;
    }

    public Note buscarNote(Long id) throws Exception {
        return notes.stream().filter(note -> note.getId().equals(id)).findFirst().orElseThrow(() -> new Exception("Não encontrado"));
    }

    public List<Note> buscarTodas() {
        return notes;
    }

    public void deletarNote(Long id) throws Exception {
        notes.remove(this.buscarNote(id));
    }
}
