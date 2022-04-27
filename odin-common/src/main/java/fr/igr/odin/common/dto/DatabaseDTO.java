package fr.igr.odin.common.dto;

/**
 * Created on 19/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class DatabaseDTO {
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
