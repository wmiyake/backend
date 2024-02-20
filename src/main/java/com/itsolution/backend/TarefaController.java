package com.itsolution.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exemplo") // O mapeamento da raiz da API
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    // Método para obter todas as tarefas
    @GetMapping
    public ResponseEntity<List<Tarefa>> obterTodasTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return ResponseEntity.ok(tarefas);
    }

    // Método para obter uma tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterTarefaPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            return ResponseEntity.ok(tarefaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para criar uma nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    // Método para atualizar uma tarefa existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            Tarefa tarefaExistente = tarefaOptional.get();
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setConcluida(tarefaAtualizada.isConcluida());
            tarefaRepository.save(tarefaExistente);
            return ResponseEntity.ok(tarefaExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para excluir uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            tarefaRepository.delete(tarefaOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
