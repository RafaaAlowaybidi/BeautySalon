/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beautysalon;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author sara
 */
@Entity
@Table(name = "customer1")
public class customer1 implements Serializable {
 @Column(name = "CustomerName")
    private String CustomerName;
    @Id
    @Column(name = "CustomerPhoneNumber")
    private long CustomerPhoneNumber;
    @Column(name = "Service")
    private String Service;
    @Column(name = "Stylist")
    private String Stylist;
    @Column(name = "DateTime")
    private String DateTime;

    public customer1() {
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public long getCustomerPhoneNumber() {
        return CustomerPhoneNumber;
    }

    public void setCustomerPhoneNumber(long CustomerPhoneNumber) {
        this.CustomerPhoneNumber = CustomerPhoneNumber;
    }

    public String getService() {
        return Service;
    }

    public void setService(String Service) {
        this.Service = Service;
    }

    public String getStylist() {
        return Stylist;
    }

    public void setStylist(String Stylist) {
        this.Stylist = Stylist;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }
    
}
