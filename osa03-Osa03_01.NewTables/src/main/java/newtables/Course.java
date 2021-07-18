package newtables;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Henri
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends AbstractPersistable<Long> {

    private String name;

    @OneToMany
    @JoinTable(name = "Enrollment", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();

}
