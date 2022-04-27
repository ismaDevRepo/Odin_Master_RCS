package fr.igr.odin.common.model;

import javax.persistence.*;

/**
 * Created on 13/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "MAPPING_VALUE")
public class MappingValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MAPPING_ID")
    private Mapping mapping;

    @Column(name = "`KEY`")
    private String key;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne
    @JoinColumn(name = "THESAURUS_VALUE_ID")
    private ThesaurusValue thesaurusValue;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mapping getMapping() {
        return this.mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ThesaurusValue getThesaurusValue() {
        return this.thesaurusValue;
    }

    public void setThesaurusValue(ThesaurusValue thesaurusValue) {
        this.thesaurusValue = thesaurusValue;
    }
}
