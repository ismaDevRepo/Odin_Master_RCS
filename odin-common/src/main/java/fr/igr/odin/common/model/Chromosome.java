package fr.igr.odin.common.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Created on 06/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "CHROMOSOME")
public class Chromosome {
    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GENOME_BUILD_ID")
    private GenomeBuild genomeBuild;

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

    public GenomeBuild getGenomeBuild() {
        return genomeBuild;
    }

    public void setGenomeBuild(GenomeBuild genomeBuild) {
        this.genomeBuild = genomeBuild;
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
