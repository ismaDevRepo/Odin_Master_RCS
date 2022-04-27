package fr.igr.odin.common.dto.mapper;

import fr.igr.odin.common.dto.MappingValueDTO;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJacksonValue;

import static org.junit.Assert.*;

/**
 * Created on 08/08/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class MappingFilterUtilsTest {
    @Test
    public void testFieldsIncluded() {
        String fieldsIncluded = "draftComment(attachments($type,author(fullName,id,ringId),comment(id),created,id,imageDimension(height,width),issue(id,project(id,ringId)),issue(id,project(id,ringId)),mimeType,name,removed,size,thumbnailURL,url,visibility($type,implicitPermittedUsers($type,avatarUrl,email,fullName,id,isLocked,issueRelatedGroup(icon),login,name,online,ringId),permittedGroups($type,allUsersGroup,icon,id,name,ringId),permittedUsers($type,avatarUrl,email,fullName,id,isLocked,issueRelatedGroup(icon),login,name,online,ringId))),author($type,avatarUrl,email,fullName,id,isLocked,issueRelatedGroup(icon),login,name,online,ringId),created,id,text,textPreview,usesMarkdown,visibility($type,implicitPermittedUsers($type,avatarUrl,email,fullName,id,isLocked,issueRelatedGroup(icon),login,name,online,ringId),permittedGroups($type,allUsersGroup,icon,id,name,ringId),permittedUsers($type,avatarUrl,email,fullName,id,isLocked,issueRelatedGroup(icon),login,name,online,ringId)))";
        MappingJacksonValue map = MappingFilterUtils.applyFilter(null, "", fieldsIncluded, null);

        assertNotNull(map.getFilters().findPropertyFilter("authorFilter", null));
        assertNull(map.getFilters().findPropertyFilter("test", null));
    }

    @Test
    public void testFieldsIncluded_2() {
        MappingValueDTO mappingValueDTO = new MappingValueDTO();
        mappingValueDTO.setId(1L);
        mappingValueDTO.setKey("test");
        MappingJacksonValue toto = MappingFilterUtils.applyFilter(mappingValueDTO, "mappingValue", "id, key, value", null);

        assertEquals("test", ((MappingValueDTO) toto.getValue()).getKey());
    }

    @Test
    public void testFieldsExcluded() {
        String fieldsExcluded = "thesaurusValue,mapping(mappingValues, mappingValuesToMap)";
        MappingJacksonValue map = MappingFilterUtils.applyFilter(null, "mappingValue", null, fieldsExcluded);

        assertNotNull(map.getFilters().findPropertyFilter("mappingFilter", null));
        assertNull(map.getFilters().findPropertyFilter("test", null));
    }
}
