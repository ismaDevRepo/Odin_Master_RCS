package fr.igr.odin.common.dto.mapper.context;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created on 28/05/2019
 *
 * @author JDI
 * @version 1.0.0
 * @see <a href="https://github.com/mapstruct/mapstruct-examples/tree/master/mapstruct-mapping-with-cycles">mapstruct-mapping-with-cycles</a>
 * @since 1.0.0
 */
public class CycleAvoidingMappingContext {
    private Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        return (T) knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put(source, target);
    }
}
