package euroshopper;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {
    
    private Map<Item, Long> items = new HashMap<>();
    
    public Map<Item, Long> getItems(){
        return this.items;
    }
    
    public void addToCart(Item item){
        if (items.containsKey(item)){
            items.put(item, items.get(item)+1);
        } else {
            items.put(item, 1L);
        }
        
    }
    
    public void clearAll(){
        items.clear();
    }
    
}
