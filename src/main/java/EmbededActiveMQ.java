import org.apache.activemq.broker.BrokerService;

public class EmbededActiveMQ {
    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("TP7-Broker");
        broker.setUseJmx(false);
        broker.setPersistent(false);
        broker.addConnector("tcp://localhost:61616");
        broker.start();

        System.out.println("Broker ActiveMQ démarré avec succès sur tcp://localhost:61616");
        System.out.println("En attente de connexions...");
        Thread.sleep(Long.MAX_VALUE);
    }
}