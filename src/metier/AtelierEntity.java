package metier;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "atelier")
public class AtelierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedispo;

    //@OneToMany(mappedBy = "atelier")
    @OneToMany(targetEntity=MachineEntity.class, mappedBy="atelier", fetch=FetchType.EAGER)
    private List<MachineEntity> machines;



    public AtelierEntity() {
        this.id = 0;
        this.machines = new ArrayList<MachineEntity>();
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
    @Column(name = "datedispo")
    public Date getDatedispo() {
        return datedispo;
    }

    public void setDatedispo(Date datedispo) {
        this.datedispo = datedispo;
    }

    public boolean addMachine(MachineEntity m){
        try {
            this.machines.add(m);
            Date now =new java.sql.Date(Calendar.getInstance().getTime().getTime());
            this.datedispo = now;

            //m.setAtelier(this);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtelierEntity atelierEntity = (AtelierEntity) o;
        return id == atelierEntity.id &&
                Objects.equals(datedispo, atelierEntity.datedispo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datedispo);
    }
}
