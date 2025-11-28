import org.apache.activemq.broker.BrokerService;

public class EmbededActiveMQ {
    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();

        // Configuration du broker
        broker.setBrokerName("TP7-Broker");
        broker.setUseJmx(false); // Désactiver JMX pour simplifier
        broker.setPersistent(false); // Mode non persistant pour le TP

        // Ajouter le connecteur TCP
        broker.addConnector("tcp://localhost:61616");

        // Démarrer le broker
        broker.start();

        System.out.println("✅ Broker ActiveMQ démarré avec succès sur tcp://localhost:61616");
        System.out.println("📡 En attente de connexions...");

        // Garder le programme actif
        Thread.sleep(Long.MAX_VALUE);
    }
}