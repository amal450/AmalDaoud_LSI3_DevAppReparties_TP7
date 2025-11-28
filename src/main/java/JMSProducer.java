import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class JMSProducer {
    public static void main(String[] args) {
        try {
            // Configuration de la ConnectionFactory
            ConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Création de la connexion et de la session
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            // Création de la destination (Topic)
            Topic destination = session.createTopic("TP7.Topic");

            // Création du MessageProducer
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Création et envoi du message
            String messageText = "Hello World! From: AMAL DAOUD";
            TextMessage message = session.createTextMessage(messageText);
            producer.send(message);

            // Commit de la session transactionnelle
            session.commit();

            System.out.println("Message envoyé: " + messageText);

            // Fermeture des ressources
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}