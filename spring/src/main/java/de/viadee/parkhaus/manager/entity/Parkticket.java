package de.viadee.parkhaus.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Parkticket {

    @Id
    private String id;

    private LocalDateTime entered;

    private LocalDateTime payment;

    public Parkticket() {}

    public Parkticket(LocalDateTime entered) {
        this.entered = entered;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getEntered() {
        return entered;
    }

    public void setEntered(LocalDateTime validFrom) {
        this.entered = validFrom;
    }

    public LocalDateTime getPayment() {
        return payment;
    }

    public void setPayment(LocalDateTime payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "parkhaus{" +
                "id='" + id + '\'' +
                ", entered=" + entered +
                ", payment=" + payment +
                '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Parkticket))
            return false;
        Parkticket that = (Parkticket) o;
        return getId().equals(that.getId()) && getEntered().equals(that.getEntered())
              && getPayment().equals(that.getPayment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entered);
    }
}
