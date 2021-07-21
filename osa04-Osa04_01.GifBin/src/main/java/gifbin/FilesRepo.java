
package gifbin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepo extends JpaRepository<FileObject, Long> {
    
}
