package examsandquestions;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exam extends AbstractPersistable<Long> {

    @OneToMany(mappedBy = "exam")
    private List<Question> questions;

    private String subject;

    private LocalDate examDate;


}
