package sat.gob.mx.agsc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TdRegFront.
 */
@Entity
@Table(name = "td_reg_front")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdRegFront implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "region", nullable = false)
    private String region;

    @NotNull
    @Column(name = "domicilio_region", nullable = false)
    private String domicilioRegion;

    @NotNull
    @Column(name = "sucursal_region", nullable = false)
    private String sucursalRegion;

    @NotNull
    @Column(name = "tipo_impuestod", nullable = false)
    private String tipoImpuestod;

    @NotNull
    @Column(name = "tipo_solicitudd", nullable = false)
    private String tipoSolicitudd;

    @NotNull
    @Column(name = "ejerciciod", nullable = false)
    private Integer ejerciciod;

    @ManyToOne
    @JsonIgnoreProperties(value = "tdRegFronts", allowSetters = true)
    private TcTipoSol tipoSolicitud;

    @ManyToOne
    @JsonIgnoreProperties(value = "tdRegFronts", allowSetters = true)
    private TcTipoImp tipoImpuesto;

    @ManyToOne
    @JsonIgnoreProperties(value = "tdRegFronts", allowSetters = true)
    private TcEjerc ejercicio;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "td_reg_front_manifestacion",
               joinColumns = @JoinColumn(name = "td_reg_front_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "manifestacion_id", referencedColumnName = "id"))
    private Set<TcManifes> manifestacions = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "td_reg_front_validacion",
               joinColumns = @JoinColumn(name = "td_reg_front_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "validacion_id", referencedColumnName = "id"))
    private Set<TcValida> validacions = new HashSet<>();

    @OneToOne(mappedBy = "tipoSolicitud")
    @JsonIgnore
    private TdGeneral general;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public TdRegFront region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDomicilioRegion() {
        return domicilioRegion;
    }

    public TdRegFront domicilioRegion(String domicilioRegion) {
        this.domicilioRegion = domicilioRegion;
        return this;
    }

    public void setDomicilioRegion(String domicilioRegion) {
        this.domicilioRegion = domicilioRegion;
    }

    public String getSucursalRegion() {
        return sucursalRegion;
    }

    public TdRegFront sucursalRegion(String sucursalRegion) {
        this.sucursalRegion = sucursalRegion;
        return this;
    }

    public void setSucursalRegion(String sucursalRegion) {
        this.sucursalRegion = sucursalRegion;
    }

    public String getTipoImpuestod() {
        return tipoImpuestod;
    }

    public TdRegFront tipoImpuestod(String tipoImpuestod) {
        this.tipoImpuestod = tipoImpuestod;
        return this;
    }

    public void setTipoImpuestod(String tipoImpuestod) {
        this.tipoImpuestod = tipoImpuestod;
    }

    public String getTipoSolicitudd() {
        return tipoSolicitudd;
    }

    public TdRegFront tipoSolicitudd(String tipoSolicitudd) {
        this.tipoSolicitudd = tipoSolicitudd;
        return this;
    }

    public void setTipoSolicitudd(String tipoSolicitudd) {
        this.tipoSolicitudd = tipoSolicitudd;
    }

    public Integer getEjerciciod() {
        return ejerciciod;
    }

    public TdRegFront ejerciciod(Integer ejerciciod) {
        this.ejerciciod = ejerciciod;
        return this;
    }

    public void setEjerciciod(Integer ejerciciod) {
        this.ejerciciod = ejerciciod;
    }

    public TcTipoSol getTipoSolicitud() {
        return tipoSolicitud;
    }

    public TdRegFront tipoSolicitud(TcTipoSol tcTipoSol) {
        this.tipoSolicitud = tcTipoSol;
        return this;
    }

    public void setTipoSolicitud(TcTipoSol tcTipoSol) {
        this.tipoSolicitud = tcTipoSol;
    }

    public TcTipoImp getTipoImpuesto() {
        return tipoImpuesto;
    }

    public TdRegFront tipoImpuesto(TcTipoImp tcTipoImp) {
        this.tipoImpuesto = tcTipoImp;
        return this;
    }

    public void setTipoImpuesto(TcTipoImp tcTipoImp) {
        this.tipoImpuesto = tcTipoImp;
    }

    public TcEjerc getEjercicio() {
        return ejercicio;
    }

    public TdRegFront ejercicio(TcEjerc tcEjerc) {
        this.ejercicio = tcEjerc;
        return this;
    }

    public void setEjercicio(TcEjerc tcEjerc) {
        this.ejercicio = tcEjerc;
    }

    public Set<TcManifes> getManifestacions() {
        return manifestacions;
    }

    public TdRegFront manifestacions(Set<TcManifes> tcManifes) {
        this.manifestacions = tcManifes;
        return this;
    }

    public TdRegFront addManifestacion(TcManifes tcManifes) {
        this.manifestacions.add(tcManifes);
        tcManifes.getGenerals().add(this);
        return this;
    }

    public TdRegFront removeManifestacion(TcManifes tcManifes) {
        this.manifestacions.remove(tcManifes);
        tcManifes.getGenerals().remove(this);
        return this;
    }

    public void setManifestacions(Set<TcManifes> tcManifes) {
        this.manifestacions = tcManifes;
    }

    public Set<TcValida> getValidacions() {
        return validacions;
    }

    public TdRegFront validacions(Set<TcValida> tcValidas) {
        this.validacions = tcValidas;
        return this;
    }

    public TdRegFront addValidacion(TcValida tcValida) {
        this.validacions.add(tcValida);
        tcValida.getGenerals().add(this);
        return this;
    }

    public TdRegFront removeValidacion(TcValida tcValida) {
        this.validacions.remove(tcValida);
        tcValida.getGenerals().remove(this);
        return this;
    }

    public void setValidacions(Set<TcValida> tcValidas) {
        this.validacions = tcValidas;
    }

    public TdGeneral getGeneral() {
        return general;
    }

    public TdRegFront general(TdGeneral tdGeneral) {
        this.general = tdGeneral;
        return this;
    }

    public void setGeneral(TdGeneral tdGeneral) {
        this.general = tdGeneral;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TdRegFront)) {
            return false;
        }
        return id != null && id.equals(((TdRegFront) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TdRegFront{" +
            "id=" + getId() +
            ", region='" + getRegion() + "'" +
            ", domicilioRegion='" + getDomicilioRegion() + "'" +
            ", sucursalRegion='" + getSucursalRegion() + "'" +
            ", tipoImpuestod='" + getTipoImpuestod() + "'" +
            ", tipoSolicitudd='" + getTipoSolicitudd() + "'" +
            ", ejerciciod=" + getEjerciciod() +
            "}";
    }
}
