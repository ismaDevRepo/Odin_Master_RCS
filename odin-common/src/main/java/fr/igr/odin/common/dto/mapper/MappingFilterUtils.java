package fr.igr.odin.common.dto.mapper;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 08/08/2019
 *
 * @author JDI
 * @version 1.0.0
 * @see <a href="https://stackoverflow.com/a/55348748/10004661">Ignore fields from Java object dynamically while sending as JSON from Spring MVC</a>
 * @since 1.0.0
 */
public class MappingFilterUtils {
    private static Logger logger = LoggerFactory.getLogger(MappingFilterUtils.class);

    public enum JsonFilterMode {
        INCLUDE_FIELD_MODE, EXCLUDE_FIELD_MODE
    }

    public static MappingJacksonValue applyFilter(Object object, final String filterName, String fieldsIncluded, String fieldsExcluded) {
        MappingJacksonValue mapping = new MappingJacksonValue(object);
        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);
        MappingFilterUtils.JsonFilterMode mode = MappingFilterUtils.JsonFilterMode.EXCLUDE_FIELD_MODE;

        String inlineFields = null;
        if (null != fieldsIncluded && !fieldsIncluded.isEmpty()) {
            mode = MappingFilterUtils.JsonFilterMode.INCLUDE_FIELD_MODE;
            inlineFields = fieldsIncluded;
        } else if (null != fieldsExcluded && !fieldsExcluded.isEmpty()) {
            inlineFields = fieldsExcluded;
        }

        if (null == inlineFields) {
            mapping.setFilters(simpleFilterProvider);
        } else {
            // On enlève tous les espaces
            inlineFields = inlineFields.replaceAll("\\s+", "");

            // String to be scanned to find the pattern.
            String pattern = "^(.*?)[\\(\\)]";

            // Create a Pattern object
            Pattern r = Pattern.compile(pattern);
            Map<String, Set<String>> filters = new HashMap<>();
            String previousFilter = filterName;

            List<String> papa = new ArrayList<>();

            while (inlineFields.length() > 0) {
                if (inlineFields.startsWith(",")) {
                    inlineFields = inlineFields.substring(1);
                }
                Matcher m = r.matcher(inlineFields);

                if (m.find()) {
                    papa.clear();
                    Collections.addAll(papa, m.group(1).split(","));
                    if (papa.size() == 1 && m.group(0).endsWith("(")) {
                        previousFilter = papa.get(papa.size() - 1);
                        filters.put(previousFilter, new HashSet<>());
                    } else {
                        filters.put(previousFilter, new HashSet<>());
                        if (JsonFilterMode.EXCLUDE_FIELD_MODE.equals(mode) && m.group(0).endsWith("(")) {
                            filters.get(previousFilter).addAll(papa.subList(0, papa.size() - 1));
                        } else {
                            filters.get(previousFilter).addAll(papa);
                        }
                        if (m.group(0).endsWith("(")) {
                            previousFilter = papa.get(papa.size() - 1);
                            filters.put(previousFilter, new HashSet<>());
                        }
                    }
                    inlineFields = inlineFields.substring(m.group(0).length());
                    if (inlineFields.startsWith(")")) {
                        inlineFields = inlineFields.substring(1);
                    }
                } else {
                    papa.clear();
                    Collections.addAll(papa, inlineFields.split(","));
                    filters.put(previousFilter, new HashSet<>());
                    for (String tete : papa) {
                        filters.get(previousFilter).add(tete);
                    }
                    inlineFields = "";
                }
            }

            for (Map.Entry<String, Set<String>> fil : filters.entrySet()) {
                SimpleBeanPropertyFilter filter = null;
                switch (mode) {
                    case EXCLUDE_FIELD_MODE:
                        filter = SimpleBeanPropertyFilter.serializeAllExcept(fil.getValue());
                        break;
                    case INCLUDE_FIELD_MODE:
                        filter = SimpleBeanPropertyFilter.filterOutAllExcept(fil.getValue());
                        break;
                }
                simpleFilterProvider.addFilter(String.format("%sFilter", fil.getKey()), filter);
            }
            mapping.setFilters(simpleFilterProvider);
        }
        return mapping;
    }
}
