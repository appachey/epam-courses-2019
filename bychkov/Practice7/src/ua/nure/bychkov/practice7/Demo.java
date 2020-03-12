package ua.nure.bychkov.practice7;

import ua.nure.bychkov.practice7.entity.*;
import ua.nure.bychkov.practice7.entity.Package;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Medicine med = new Medicine();
        Version ver = new Version();
        Manufacturer man = new Manufacturer();
        Certificate cer = new Certificate();
        Package pack = new Package();
        pack.setCount(1);
        pack.setCurrency("UAH");
        pack.setType("box");
        pack.setPrice(1.59);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        cer.setExpDate(dateFormat.format(new Date()));
        cer.setNumber(123456);
        cer.setOrganization("NDEKC");
        man.setCertificate(cer);
        man.setPack(pack);
        man.setDosage(2);
        man.setDosgeUnit("ml");
        ver.setType("pills");
        ver.getManufacturers().add(man);
        med.setName("Analgin");
        med.setGroup("Analgetics");
        med.setPharm("Oh-Bolit");
        med.getAnalogs().add("Koleso");
        med.getVersions().add(ver);
        Medicines medicines = new Medicines();
        medicines.getMedicines().add(med);
        System.out.println(medicines);
    }
}
