package fr.igr.odin.common.dto.mapper;

import fr.igr.odin.common.dto.ResultDTO;
import fr.igr.odin.common.dto.VariantDTO;
import fr.igr.odin.common.dto.mapper.context.CycleAvoidingMappingContext;
import fr.igr.odin.common.dto.mapper.converter.StringJSONConverter;
import fr.igr.odin.common.model.Result;
import fr.igr.odin.common.model.Variant;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created on 05/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Mapper(uses = StringJSONConverter.class)
public interface EntityToDTOMapper {
    EntityToDTOMapper INSTANCE = Mappers.getMapper(EntityToDTOMapper.class);

    List<ResultDTO> resultsToResultDTOs(List<Result> results, @Context CycleAvoidingMappingContext context);

    //List<TemplateDTO> templatesToTemplateDTOs(List<Template> templates, @Context CycleAvoidingMappingContext context);
    List<VariantDTO> variantsToVariantDTOs(List<Variant> variants, @Context CycleAvoidingMappingContext context);

}
