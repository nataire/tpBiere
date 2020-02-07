package metier;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "machine")
public class MachineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedispo;
    private double penalitetotale;
    private Integer natelier;

    //@OneToMany(mappedBy = "machine")
    @OneToMany(targetEntity=TacheEntity.class, mappedBy="machine", fetch=FetchType.EAGER)
    private List<TacheEntity> taches;

    /*@ManyToOne
    @JoinColumn(name = "id")
    private AtelierEntity atelier;*/

    public MachineEntity(){
        this.id = 0;
        this.taches = new ArrayList<TacheEntity>();
        this.datedispo = new Date(System.currentTimeMillis());
    }

    public boolean addTache(TacheEntity t){
        try {
            this.taches.add(t);
            t.setMachine(this);
            t.setDatedebut(this.datedispo);

            //Mise à jour date dispo
            long duree = new Timestamp(t.getTempsprod()).getTime()*60000+this.datedispo.getTime();
            this.datedispo = new Date(duree);

            //Ajouter pénalité de retard
            if(this.datedispo.getTime() > t.getDatelimite().getTime()){
                this.penalitetotale = this.datedispo.getTime()-t.getDatelimite().getTime()/60000.0*t.getPenaliteretard();
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "datedispo")
    public Date getDatedispo() {
        return datedispo;
    }

    public void setDatedispo(Date datedispo) {
        this.datedispo = datedispo;
    }

    @Basic
    @Column(name = "penalitetotale")
    public double getPenalitetotale() {
        return penalitetotale;
    }

    public void setPenalitetotale(double penalitetotale) {
        this.penalitetotale = penalitetotale;
    }

    /*public AtelierEntity getAtelier() {
        return atelier;
    }

    public void setAtelier(AtelierEntity atelier) {
        this.atelier = atelier;
        this.natelier = atelier.getId();
    }*/

    public int getNatelier() {
        return natelier;
    }

    public void setNatelier(int natelier) {
        this.natelier = natelier;
    }

    /*public List<TacheEntity> getTaches() {
        return taches;
    }

    public void setTaches(List<TacheEntity> taches) {
        this.taches = taches;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MachineEntity machineEntity = (MachineEntity) o;
        return id == machineEntity.id &&
                Double.compare(machineEntity.penalitetotale, penalitetotale) == 0 &&
                Objects.equals(datedispo, machineEntity.datedispo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datedispo, penalitetotale);
    }
}
