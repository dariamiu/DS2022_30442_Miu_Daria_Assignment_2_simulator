package ds.smartmeteringdevicesimulator;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.*;
import java.util.*;

@SpringBootApplication
public class SmartMeteringDeviceSimulatorApplication {
    @Autowired
    MessageController messageController;

    static String string;

    @PostConstruct
    public void init() throws InterruptedException, FileNotFoundException {
        FileInputStream file = new FileInputStream(string);
        Scanner inputFile = new Scanner(file);
        String nr = inputFile.nextLine();
        Integer deviceId = Integer.parseInt(nr);

        List<Float> inputList = new ArrayList<Float>();
        try{

            File inputF = new File("sensor.csv");
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            inputList = br.lines().map((line) -> {
                String[] p = line.split(",");
                float item = Float.parseFloat(p[0]);
                return item;
            }).toList();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputList.forEach(System.out::println);

        for (int i = 0; i < inputList.size() - 1; i++) {

            CustomMessage message = new CustomMessage();
            message.setDevice(deviceId);
            float consumption = inputList.get(i+1) - inputList.get(i);
            System.out.println(consumption);
            message.setConsumption(inputList.get(i+1) - inputList.get(i));
            messageController.publishMessage(message);

            Thread.sleep(10000);
        }
    }


    public static void main(String[] args) {
        System.out.println(args[0]);
        string = args[0];
        SpringApplication.run(SmartMeteringDeviceSimulatorApplication.class, args);

    }

}
