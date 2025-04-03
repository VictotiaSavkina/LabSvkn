
// Блок питания
class PowerSupply {
    public void powerOn() {
        System.out.println("Блок питания: подача питания");
    }

    public void powerOff() {
        System.out.println("Блок питания: отключение питания");
    }
}

// BIOS
class BIOS {
    public void start() {
        System.out.println("BIOS: запуск самотестирования (POST)");
    }

    public void loadOS() {
        System.out.println("BIOS: загрузка операционной системы");
    }
}

// Операционная система
class OperatingSystem {
    public void boot() {
        System.out.println("ОС: запуск ядра системы");
    }

    public void shutdown() {
        System.out.println("ОС: завершение работы");
    }

    public void executeProgram(String programName) {
        System.out.println("ОС: запуск программы " + programName);
    }
}

// Жесткий диск
class HardDrive {
    public void readData() {
        System.out.println("Жесткий диск: чтение данных");
    }

    public void writeData() {
        System.out.println("Жесткий диск: запись данных");
    }
}

// Фасад
class ComputerFacade {
    private PowerSupply powerSupply;
    private BIOS bios;
    private OperatingSystem os;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.powerSupply = new PowerSupply();
        this.bios = new BIOS();
        this.os = new OperatingSystem();
        this.hardDrive = new HardDrive();
    }

    public void turnOn() {
        System.out.println("    Включение компьютера");
        powerSupply.powerOn();
        bios.start();
        hardDrive.readData();
        bios.loadOS();
        os.boot();
        System.out.println("Компьютер готов к работе\n");
    }

    public void turnOff() {
        System.out.println("    Выключение компьютера");
        os.shutdown();
        powerSupply.powerOff();
        System.out.println("Компьютер выключен\n");
    }

    public void runProgram(String programName) {
        System.out.println("    Запуск программы");
        hardDrive.readData();
        os.executeProgram(programName);
        System.out.println("Программа " + programName + " запущена\n");
    }
}

// Главный
public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.turnOn();
        computer.runProgram("Word");
        computer.turnOff();
    }
}