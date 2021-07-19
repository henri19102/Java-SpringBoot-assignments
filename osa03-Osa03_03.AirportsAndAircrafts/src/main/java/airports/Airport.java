package airports;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport extends AbstractPersistable<Long> {

    @OneToMany(mappedBy = "airport")
    private List<Aircraft> aircrafts = new ArrayList<>();

    private String identifier;
    private String name;
}
