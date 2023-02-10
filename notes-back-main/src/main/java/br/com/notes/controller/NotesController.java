package br.com.notes.controller;

import br.com.notes.models.Note;
import br.com.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@ResponseBody
@CrossOrigin("*")
@EnableCaching
public class NotesController {

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping
    public List<Note> getNotes() {
        System.out.println("Buscando Notas");
        return notesRepository.buscarTodas();
    }

    @PostMapping
    public Note saveNote(Note note) {
        return notesRepository.inserirNaLista(note);
    }

    @PutMapping("/{id}")
    public Note putNote(@PathVariable(name = "id") Long id, @RequestBody Note note) throws Exception {
        Note noteBanco = notesRepository
                .buscarNote(id);

        noteBanco.setText(note.getText());
        noteBanco.setUrgent(note.getUrgent());

        return notesRepository.inserirNaLista(noteBanco);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable(name = "id") Long id) throws Exception {
        notesRepository.deletarNote(id);
    }

    @GetMapping("/{id}")
    public Note findById(@PathVariable(name = "id") Long id) throws Exception {
        return this.notesRepository.buscarNote(id);
    }

}