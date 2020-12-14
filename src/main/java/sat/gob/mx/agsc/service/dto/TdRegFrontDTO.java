package sat.gob.mx.agsc.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link sat.gob.mx.agsc.domain.TdRegFront} entity.
 */
public class TdRegFrontDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String region;

    @NotNull
    private String domicilioRegion;

    @NotNull
    private String sucursalRegion;

    @NotNull
    private String tipoImpuestod;

    @NotNull
    private String tipoSolicitudd;

    @NotNull
    private Integer ejerciciod;


    private Long tipoSolicitudId;

    private String tipoSolicitudDescripcion;

    private Long tipoImpuestoId;

    private String tipoImpuestoDescripcion;

    private Long ejercicioId;

    private String ejercicioValor;
    private Set<TcManifesDTO> manifestacions = new HashSet<>();
    private Set<TcValidaDTO> validacions = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDomicilioRegion() {
        return domicilioRegion;
    }

    public void setDomicilioRegion(String domicilioRegion) {
        this.domicilioRegion = domicilioRegion;
    }

    public String getSucursalRegion() {
        return sucursalRegion;
    }

    public void setSucursalRegion(String sucursalRegion) {
        this.sucursalRegion = sucursalRegion;
    }

    public String getTipoImpuestod() {
        return tipoImpuestod;
    }

    public void setTipoImpuestod(String tipoImpuestod) {
        this.tipoImpuestod = tipoImpuestod;
    }

    public String getTipoSolicitudd() {
        return tipoSolicitudd;
    }

    public void setTipoSolicitudd(String tipoSolicitudd) {
        this.tipoSolicitudd = tipoSolicitudd;
    }

    public Integer getEjerciciod() {
        return ejerciciod;
    }

    public void setEjerciciod(Integer ejerciciod) {
        this.ejerciciod = ejerciciod;
    }

    public Long getTipoSolicitudId() {
        return tipoSolicitudId;
    }

    public void setTipoSolicitudId(Long tcTipoSolId) {
        this.tipoSolicitudId = tcTipoSolId;
    }

    public String getTipoSolicitudDescripcion() {
        return tipoSolicitudDescripcion;
    }

    public void setTipoSolicitudDescripcion(String tcTipoSolDescripcion) {
        this.tipoSolicitudDescripcion = tcTipoSolDescripcion;
    }

    public Long getTipoImpuestoId() {
        return tipoImpuestoId;
    }

    public void setTipoImpuestoId(Long tcTipoImpId) {
        this.tipoImpuestoId = tcTipoImpId;
    }

    public String getTipoImpuestoDescripcion() {
        return tipoImpuestoDescripcion;
    }

    public void setTipoImpuestoDescripcion(String tcTipoImpDescripcion) {
        this.tipoImpuestoDescripcion = tcTipoImpDescripcion;
    }

    public Long getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Long tcEjercId) {
        this.ejercicioId = tcEjercId;
    }

    public String getEjercicioValor() {
        return ejercicioValor;
    }

    public void setEjercicioValor(String tcEjercValor) {
        this.ejercicioValor = tcEjercValor;
    }

    public Set<TcManifesDTO> getManifestacions() {
        return manifestacions;
    }

    public void setManifestacions(Set<TcManifesDTO> tcManifes) {
        this.manifestacions = tcManifes;
    }

    public Set<TcValidaDTO> getValidacions() {
        return validacions;
    }

    public void setValidacions(Set<TcValidaDTO> tcValidas) {
        this.validacions = tcValidas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TdRegFrontDTO)) {
            return false;
        }

        return id != null && id.equals(((TdRegFrontDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TdRegFrontDTO{" +
            "id=" + getId() +
            ", region='" + getRegion() + "'" +
            ", domicilioRegion='" + getDomicilioRegion() + "'" +
            ", sucursalRegion='" + getSucursalRegion() + "'" +
            ", tipoImpuestod='" + getTipoImpuestod() + "'" +
            ", tipoSolicitudd='" + getTipoSolicitudd() + "'" +
            ", ejerciciod=" + getEjerciciod() +
            ", tipoSolicitudId=" + getTipoSolicitudId() +
            ", tipoSolicitudDescripcion='" + getTipoSolicitudDescripcion() + "'" +
            ", tipoImpuestoId=" + getTipoImpuestoId() +
            ", tipoImpuestoDescripcion='" + getTipoImpuestoDescripcion() + "'" +
            ", ejercicioId=" + getEjercicioId() +
            ", ejercicioValor='" + getEjercicioValor() + "'" +
            ", manifestacions='" + getManifestacions() + "'" +
            ", validacions='" + getValidacions() + "'" +
            "}";
    }
}
