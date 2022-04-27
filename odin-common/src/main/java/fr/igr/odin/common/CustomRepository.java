package fr.igr.odin.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created on 18/07/2019
 *
 * @author JDI
 * @version 1.0.0
 * @see <a href="https://www.javabullets.com/add-entitymanager-refresh-spring-data-repositories/">Add EntityManager.refresh to all Spring Data Repositories</a>
 * @since 1.0.0
 */
@NoRepositoryBean // @NoRepositoryBean : This prevents an instance of a Repository being created
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    void refresh(T t);
}
