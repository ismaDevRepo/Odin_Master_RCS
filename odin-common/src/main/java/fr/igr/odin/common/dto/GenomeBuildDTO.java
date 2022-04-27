package fr.igr.odin.common.dto;

/**
 * Created on 06/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class GenomeBuildDTO {
    private Long id;
    private String libelle;
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
        return osiris;
    }

    public void setOsiris(String osiris) {
        this.osiris = osiris;
    }
}
