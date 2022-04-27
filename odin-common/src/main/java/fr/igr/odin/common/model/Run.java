package fr.igr.odin.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 25/03/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "RUN")
public class Run {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OSIRIS_ANALYSIS_DATE")
    private java.sql.Date osirisAnalysisDate;

    @Column(name = "OSIRIS_TECHNOLOGY_PLATFORM_NAME")
    private String osirisTechnologyPlatformName;

    @Column(name = "OSIRIS_TECHNOLOGY_PLATFORM_ACCESSION")
    private String osirisTechnologyPlatformAccession;

    @Column(name = "SGL_ID")
    private Long sglId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public java.sql.Date getOsirisAnalysisDate() {
        return osirisAnalysisDate;
    }

    public void setOsirisAnalysisDate(java.sql.Date osirisAnalysisDate) {
        this.osirisAnalysisDate = osirisAnalysisDate;
    }


    public String getOsirisTechnologyPlatformName() {
        return osirisTechnologyPlatformName;
    }

    public void setOsirisTechnologyPlatformName(String osirisTechnologyPlatformName) {
        this.osirisTechnologyPlatformName = osirisTechnologyPlatformName;
    }


    public String getOsirisTechnologyPlatformAccession() {
        return osirisTechnologyPlatformAccession;
    }

    public void setOsirisTechnologyPlatformAccession(String osirisTechnologyPlatformAccession) {
        this.osirisTechnologyPlatformAccession = osirisTechnologyPlatformAccession;
    }


    public Long getSglId() {
        return sglId;
    }

    public void setSglId(Long sglId) {
        this.sglId = sglId;
    }

}
