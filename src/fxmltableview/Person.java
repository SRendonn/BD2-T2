package fxmltableview;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty cedulaVendedor = new SimpleStringProperty("");
    private final SimpleStringProperty totalGanancias = new SimpleStringProperty("");

    public Person() {
        this("", "");
    }

    public Person(String cedulaVendedor, String totalGanancias) {
        setCedulaVendedor(cedulaVendedor);
        setTotalGanancias(totalGanancias);
    }

    public String getCedulaVendedor() {
        return cedulaVendedor.get();
    }

    public void setCedulaVendedor(String fName) {
        cedulaVendedor.set(fName);
    }

    public String getTotalGanancias() {
        return totalGanancias.get();
    }

    public void setTotalGanancias(String fName) {
        totalGanancias.set(fName);
    }

}