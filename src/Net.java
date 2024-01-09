import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Net implements NetC {
    private static List<Computer> computers = new ArrayList<>();
    private static List<Service> services = new ArrayList<>();

    public void Open(String Input) {
        Computer Selected = computers.stream().filter(computer -> computer.getId().equals(Input))
                .findFirst().orElse(null);
        if (Selected !=null){
            if (Selected.getStatus()){
                System.err.println("Used choice another");
            }else {
                Selected.On();
                System.out.println("Success");
            }
        }  else {
            System.err.println("Invalid computer ID. Please enter a valid ID.");
        }
        Selected.setStatus(true);
    }

    @Override
    public void Close(String Input) {
        Computer Selected = computers.stream().filter(computer -> computer.getId().equals(Input))
                .findFirst().orElse(null);
        if (Selected!=null){
            if (!Selected.getStatus()){
                System.err.println("Not Use Mistake");
            }else {
                double payment = Selected.pay();
                System.out.println(" Payment " + payment);
            }
        }  else {
            System.err.println("Invalid computer ID. Please enter a valid ID.");
        }
        Selected.setStatus(false);
    }
    @Override
    public void Display() {
        System.out.println("All Computers\n");
        System.out.println(String.format("%-10s%-10s", "ID", "Status"));

        computers.forEach(computer -> {
            String status = computer.getStatus() ? "Used" : "Available";
            System.out.println(String.format("%-10s%-10s", computer.getId(), status));
        });
    }

    @Override
    public void changePc(String Input, String input) {
        Computer From = computers.stream().filter(computer -> computer.getId().equals(Input))
                .findFirst().orElse(null);
        Computer To = computers.stream().filter(computer -> computer.getId().equals(input))
                .findFirst().orElse(null);
        if (From!=null && To!=null){
            To.setStatus(true);
            To.setStartTime(From.getStartTime());
            List<Service> servicesToTransfer = new ArrayList<>(From.getServices());
            To.setServices(servicesToTransfer);
            From.setStatus(false);
            From.setStartTime(null);
        } else {
            System.err.println("Invalid computer ID. Please enter a valid ID.");
        }
    }

    public void addServiceToComputer(String computerId, String serviceId) {
        Computer selectedComputer = computers.stream()
                .filter(computer -> computer.getId().equals(computerId))
                .findFirst().orElse(null);

        Service selectedService = services.stream()
                .filter(service -> service.getId().equals(serviceId))
                .findFirst().orElse(null);


        if (selectedComputer != null && selectedService != null) {
            selectedComputer.addService(selectedService);
            System.out.println("Service '" + selectedService.getName() + "' added to computer " + selectedComputer.getId());
        } else {
            System.err.println("Invalid computer ID or service ID");
        }
    }


    public static void main(String[] args) {
        computers.add(new Computer("PC1", 17000.0, true, new Date()));
        computers.add(new Computer("PC2", 15000.0, false, null));
        computers.add(new Computer("PC3", 15000.0, true, new Date()));
        computers.add(new Computer("PC4", 17000.0, false, null));
        computers.add(new Computer("PC5", 17000.0, false, null));
        computers.add(new Computer("PC6", 16000.0, true, new Date()));
        computers.add(new Computer("PC7", 16000.0, true, new Date()));
        computers.add(new Computer("PC8", 17000.0, false, null));
        computers.add(new Computer("PC9", 15000.0, true, new Date()));


        services.add(new Service("1","Mì Tum",15000));
        services.add(new Service("2","Sờ Tinh",10000));
        services.add(new Service("3","Bò Cụng",20000));
        services.add(new Service("4","Chúc Chích",10000));
        services.add(new Service("5","Nác Lọc",1000));
        services.add(new Service("6","Bim Bim",12000));
        Scanner sc = new Scanner(System.in);
        Net net = new Net();
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Open a computer");
            System.out.println("2. Close a computer");
            System.out.println("3. Display all computers");
            System.out.println("4. Change PC");
            System.out.println("5. Oder");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter Open ID: ");
                    net.Open(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Enter Close ID: ");
                    net.Close(sc.nextLine());
                    break;
                case 3:
                    net.Display();
                    break;
                case 4:
                    System.out.println("Enter Change PC");
                    String fromId= sc.nextLine();
                    System.out.println("Enter To PC");
                    String toID= sc.next();
                    net.changePc(fromId,toID);
                    break;
                case 5:
                    boolean check = false;
                    System.out.println("Enter the PC oder");
                    String PC = sc.nextLine();
                    while (!check) {
                        System.out.println("1. Mì Tum");
                        System.out.println("2. Sờ Tinh");
                        System.out.println("3. Bò Cụng");
                        System.out.println("4. Chúc Chích");
                        System.out.println("5. Nác Lọc");
                        System.out.println("6. Bim Bìm");
                        System.out.println("7. Exit");
                        int oder = sc.nextInt();
                        switch (oder){
                            case 1:
                                net.addServiceToComputer(PC,"1");
                                break;
                            case 2:
                                net.addServiceToComputer(PC,"2");
                                break;
                            case 3:
                                net.addServiceToComputer(PC,"3");
                                break;
                            case 4:
                                net.addServiceToComputer(PC,"4");
                                break;
                            case 5:
                                net.addServiceToComputer(PC,"5");
                                break;
                            case 6:
                                net.addServiceToComputer(PC,"6");
                                break;
                            case 7:
                                System.out.println("Exit....");
                                check = true;
                                break;
                        }
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    break;
            }
        }
    }
}