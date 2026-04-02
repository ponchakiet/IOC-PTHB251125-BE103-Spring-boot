package poncha.kiet.ss4.service;

import org.springframework.stereotype.Service;
import poncha.kiet.ss4.entity.Instructor;
import poncha.kiet.ss4.entity.InstructorCreateRequest;
import poncha.kiet.ss4.repository.IInstructorRepository;
import poncha.kiet.ss4.repository.IStudentRepository;

import java.util.List;

@Service
public class InstructorService {
    private final IInstructorRepository  iInstructorRepository;

    public InstructorService(IInstructorRepository iInstructorRepository) {
        this.iInstructorRepository = iInstructorRepository;
    }


    public Instructor findInstructorById(Long id)
    {
        return iInstructorRepository.findById(id).orElse(null);
    }

    public List<Instructor> findAllInstructors()
    {
        return iInstructorRepository.findAll();
    }

    public Instructor createInstructor(InstructorCreateRequest req)
    {
        Instructor instructor = new Instructor(null, req.getName(), req.getEmail(), null);
        return iInstructorRepository.save(instructor);
    }
}
