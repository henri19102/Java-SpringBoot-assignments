package todoapplication;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item extends AbstractPersistable<Long> {
    private String name;
    private int checked;
    
    public Item(String name){
        this.name = name;
        this.checked = 0;
    }
    
    public void add(){
        this.checked++;
    }
}
