package co.edu.uniandes.csw.primid.logic.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ViajeroEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ManyToMany(mappedBy = "viajeros")
    private List<ItinerarioEntity> itinerarios = new ArrayList<>();

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<ItinerarioEntity> getBooks() {
        return itinerarios;
    }

    public void setBooks(List<ItinerarioEntity> itinerarios) {
        this.itinerarios = itinerarios;
    }
}