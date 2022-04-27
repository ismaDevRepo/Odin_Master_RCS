package fr.igr.odin.service.interpretation.repository;

import fr.igr.odin.common.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 01/04/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface RunRepository extends JpaRepository<Run, Long> {
}
