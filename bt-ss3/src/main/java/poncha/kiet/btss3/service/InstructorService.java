package poncha.kiet.btss3.service;

import org.springframework.stereotype.Service;
import poncha.kiet.btss3.model.Instructor;
import poncha.kiet.btss3.repository.InstructorRepository;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getAll() { return instructorRepository.findAll(); }
    public Instructor getById(int id) { return instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Instructor not found: " + id)); }
    public Instructor create(Instructor i) { return instructorRepository.create(i); }
    public Instructor update(int id, Instructor i) {
        Instructor existing = getById(id);
        existing.setName(i.getName());
        existing.setEmail(i.getEmail());
        return existing;
    }
    public Instructor delete(int id) {
        Instructor existing = getById(id);
        instructorRepository.delete(existing);
        return existing;
    }
}
