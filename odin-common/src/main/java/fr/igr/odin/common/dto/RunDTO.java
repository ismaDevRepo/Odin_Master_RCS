package fr.igr.odin.common.dto;

/**
 * Created on 25/03/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class RunDTO {
    private Long id;

    private Long userId;

    private String name;

    private java.sql.Date osirisAnalysisDate;

    private String osirisTechnologyPlatformName;

    private String osirisTechnologyPlatformAccession;

    private Long sglId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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


    public long getSglId() {
        return sglId;
    }

    public void setSglId(long sglId) {
        this.sglId = sglId;
    }

}
