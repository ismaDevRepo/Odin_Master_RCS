package fr.igr.odin.common.dto.resolver;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;

import java.util.HashMap;

/**
 * Created on 28/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @see <a href="https://gist.github.com/maludwig/12c168ad0b610696b2ca89124547f404">Jackson deduping resolver</a>
 * @since 1.0.0
 */
public class DedupingObjectIdResolver extends SimpleObjectIdResolver {
    @Override
    public void bindItem(ObjectIdGenerator.IdKey id, Object ob) {
        if (_items == null) {
            _items = new HashMap<>();
        }
        _items.put(id, ob);
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return new DedupingObjectIdResolver();
    }
}
