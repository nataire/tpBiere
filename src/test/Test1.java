package test;

import metier.AtelierEntity;
import metier.MachineEntity;
import metier.TacheEntity;

import java.sql.Timestamp;
import java.sql.Date;

public class Test1 {

    public static void main(String[] args) {
        AtelierEntity a = new AtelierEntity();
        MachineEntity m1 = new MachineEntity();
        MachineEntity m2 = new MachineEntity();
        long timeMillis = System.currentTimeMillis();
        TacheEntity t1 = new TacheEntity(45, new Date(new Date(timeMillis+60000*120).getTime()), 5.0);
        TacheEntity t2 = new TacheEntity(120, new Date(new Date(timeMillis+60000*150).getTime()), 10.0);
        TacheEntity t3 = new TacheEntity(70, new Date(new Date(timeMillis+60000*180).getTime()), 4.0);
        TacheEntity t4 = new TacheEntity(60, new Date(new Date(timeMillis+60000*300).getTime()), 12.0);
        a.addMachine(m1);
        a.addMachine(m2);
        m1.addTache(t1);
        m1.addTache(t2);
        m2.addTache(t3);
        m2.addTache(t4);


    }

}
