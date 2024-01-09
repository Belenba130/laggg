import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Computer {
    private String id;
    private Double Price;
    private Boolean status;
    private Date startTime;

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    private List<Service> services;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Computer(String id, Double price, Boolean status, Date startTime) {
        this.id = id;
        this.Price = price;
        this.status = status;
        this.startTime = startTime;
        this.services = new ArrayList<>();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public double pay (){
        this.status=false;
        long usageTime = new Date().getTime()-this.startTime.getTime();
        double payment = usageTime/(360000)+1;
        double servicePayment = this.services.stream()
                .mapToDouble(Service::getPrice)
                .sum();
        return payment*this.Price + servicePayment;

    }
    public void On() {
        this.status = true;
        this.startTime = new Date();
    }

    public void addService(Service service){
        this.services.add(service);
    }
}

