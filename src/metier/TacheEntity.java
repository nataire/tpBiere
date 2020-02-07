package metier;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tache")
public class TacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer tempsprod;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedebut;
    private Timestamp datelimite;
    private Double penaliteretard;
    private Integer nmachine;

    /*@ManyToOne
    @JoinColumn(name = "id")
    private MachineEntity machine;*/

    public TacheEntity(){};

    public TacheEntity(Integer tempsprod, Date datedebut, Double penaliteretard){
        this.tempsprod = tempsprod;
        this.datedebut = datedebut;
        this.penaliteretard = penaliteretard;
        this.id = 0;

        //Mise à jour date limite
        long duree = new Timestamp(this.tempsprod*60000).getTime()+datedebut.getTime();
        this.datelimite = new Timestamp(duree);
    }

    /*public MachineEntity getMachine() {
        return machine;
    }*/

    public void setMachine(MachineEntity machine) {
        //this.machine = machine;
        this.setNmachine(machine.getId());
    }

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tempsprod")
    public Integer getTempsprod() {
        return tempsprod;
    }

    public void setTempsprod(Integer tempsprod) {
        this.tempsprod = tempsprod;
    }

    @Basic
    @Column(name = "datedebut")
    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    @Basic
    @Column(name = "datelimite")
    public Timestamp getDatelimite() {
        return datelimite;
    }

    public void setDatelimite(Timestamp datelimite) {
        this.datelimite = datelimite;
    }

    @Basic
    @Column(name = "penaliteretard")
    public Double getPenaliteretard() {
        return penaliteretard;
    }

    public void setPenaliteretard(Double penaliteretard) {
        this.penaliteretard = penaliteretard;
    }


    public Integer getNmachine() {
        return nmachine;
    }

    public void setNmachine(Integer nmachine) {
        this.nmachine = nmachine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TacheEntity tacheEntity = (TacheEntity) o;
        return id == tacheEntity.id &&
                tempsprod == tacheEntity.tempsprod &&
                Objects.equals(datedebut, tacheEntity.datedebut) &&
                Objects.equals(datelimite, tacheEntity.datelimite) &&
                Objects.equals(penaliteretard, tacheEntity.penaliteretard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tempsprod, datedebut, datelimite, penaliteretard);
    }
}
