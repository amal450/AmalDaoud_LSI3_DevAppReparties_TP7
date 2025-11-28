import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class JMSConsumer {
    public static void main(String[] args) {
        try {
            // Configuration de la ConnectionFactory - CORRIGÉ
            ConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");  // "tcp" pas "top", port 61616

            // Création de la connexion et de la session
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Création de la destination (Topic)
            Topic destination = session.createTopic("TP7.Topic");  // "TP7" pas "IP7"

            // Création du MessageConsumer
            MessageConsumer consumer = session.createConsumer(destination);

            // Configuration du Listener
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage textMessage = (TextMessage) message;
                            System.out.println("Message reçu: " + textMessage.getText());
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            System.out.println("Consumer en attente de messages...");

            // Maintenir le programme actif
            Thread.sleep(30000);

            // Fermeture
            consumer.close();
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}