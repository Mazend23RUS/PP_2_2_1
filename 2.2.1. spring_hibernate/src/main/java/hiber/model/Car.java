package hiber.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table
public class Car {

    @OneToOne (mappedBy = "car",cascade = CascadeType.ALL)
    private User user;


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String model;
    private int series;

    public Car() {
    }



    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public long getId() {
        return id;
    }

    public void setId (){
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return series == car.series && model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, series);
    }

    @Override
    public String toString() {
        return "Car{" +
                "user=" + user +
                ", id=" + id +
                ", model='" + model + '\'' +
                ", series=" + series +
                '}';
    }


}


