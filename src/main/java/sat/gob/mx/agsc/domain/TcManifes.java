package sat.gob.mx.agsc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TcManifes.
 */
@Entity
@Table(name = "tc_manifes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TcManifes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "clave", nullable = false)
    private String clave;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "moral", nullable = false)
    private Integer moral;

    @NotNull
    @Column(name = "fisica", nullable = false)
    private Integer fisica;

    @NotNull
    @Column(name = "isr", nullable = false)
    private Integer isr;

    @NotNull
    @Column(name = "iva", nullable = false)
    private Integer iva;

    @ManyToMany(mappedBy = "manifestacions")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<TdRegFront> generals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public TcManifes clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TcManifes descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMoral() {
        return moral;
    }

    public TcManifes moral(Integer moral) {
        this.moral = moral;
        return this;
    }

    public void setMoral(Integer moral) {
        this.moral = moral;
    }

    public Integer getFisica() {
        return fisica;
    }

    public TcManifes fisica(Integer fisica) {
        this.fisica = fisica;
        return this;
    }

    public void setFisica(Integer fisica) {
        this.fisica = fisica;
    }

    public Integer getIsr() {
        return isr;
    }

    public TcManifes isr(Integer isr) {
        this.isr = isr;
        return this;
    }

    public void setIsr(Integer isr) {
        this.isr = isr;
    }

    public Integer getIva() {
        return iva;
    }

    public TcManifes iva(Integer iva) {
        this.iva = iva;
        return this;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public Set<TdRegFront> getGenerals() {
        return generals;
    }

    public TcManifes generals(Set<TdRegFront> tdRegFronts) {
        this.generals = tdRegFronts;
        return this;
    }

    public TcManifes addGeneral(TdRegFront tdRegFront) {
        this.generals.add(tdRegFront);
        tdRegFront.getManifestacions().add(this);
        return this;
    }

    public TcManifes removeGeneral(TdRegFront tdRegFront) {
        this.generals.remove(tdRegFront);
        tdRegFront.getManifestacions().remove(this);
        return this;
    }

    public void setGenerals(Set<TdRegFront> tdRegFronts) {
        this.generals = tdRegFronts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TcManifes)) {
            return false;
        }
        return id != null && id.equals(((TcManifes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TcManifes{" +
            "id=" + getId() +
            ", clave='" + getClave() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", moral=" + getMoral() +
            ", fisica=" + getFisica() +
            ", isr=" + getIsr() +
            ", iva=" + getIva() +
            "}";
    }
}
