package fr.igr.odin.common.dto;

/**
 * Created on 06/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class ChromosomeDTO {
    private Long id;
    private GenomeBuildDTO genomeBuild;
    private String libelle;
    private String osiris;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GenomeBuildDTO getGenomeBuild() {
        return genomeBuild;
    }

    public void setGenomeBuild(GenomeBuildDTO genomeBuild) {
        this.genomeBuild = genomeBuild;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getOsiris() {
        return osiris;
    }

    public void setOsiris(String osiris) {
        this.osiris = osiris;
    }
}
