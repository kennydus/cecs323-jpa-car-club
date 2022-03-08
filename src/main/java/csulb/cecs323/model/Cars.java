package csulb.cecs323.model;

import javax.persistence.*;

/**
 * Individual, physical automobiles that someone can drive on land to transport one or more passengers
 * and a limited amount of cargo around.  Cars have four wheels and usually travel on paved roads.
 */
@Entity
public class Cars {
    @Id
    @Column(length = 17, nullable = false)
    /** The unique ID of the vehicle. Limited to 17 characters. */
    private String VIN;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
    /** The owner of the car. Limited to 70 characters. */
    private Owners owner;

    @ManyToOne
    @JoinColumn(name = "auto_body_style_name", referencedColumnName = "name")
    /** The body style of the car. */
    private auto_body_styles auto_body_style;

    /** The name of the corporation which manufactured the vehicle.  Limited to 40 characters. */
    @Column(length = 40, nullable = false)
    private String manufacturer;

    /** The popular name of the vehicle, like the Prius for Toyota.  Limited to 20 characters. */
    @Column(length = 20, nullable = false)
    private String model;

    /** The year that the vehicle was manufactured.  For now, do not worry about validating this #. */
    @Column(length = 4, nullable = false)
    private int year;


    public Cars() {
    }

    public Cars(String VIN, String manufacturer, String model, int year){
        this.VIN = VIN;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
    }


    @Override
    public String toString () {
        return "Cars - VIN: " + this.VIN + " Manufacturer: " + this.manufacturer +
                " Model: " + this.model + " year: " + this.year;
    }
}
