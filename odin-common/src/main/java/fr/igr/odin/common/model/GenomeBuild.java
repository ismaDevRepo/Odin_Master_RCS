package fr.igr.odin.common.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 06/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "GENOME_BUILD")
public class GenomeBuild {
    @Id
    @Column(name = "ID")
    private Long id;

    @NaturalId
    @Column(name = "LIBELLE")
    private String libelle;

    @Column(name = "OSIRIS")
    private String osiris;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getOsiris() {
        return this.osiris;
    }

    public void setOsiris(String osiris) {
        this.osiris = osiris;
    }
}
