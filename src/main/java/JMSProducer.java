import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class JMSProducer {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Topic destination = session.createTopic("TP7.Topic");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            String messageText = "Hello World! From: AMAL DAOUD";
            TextMessage message = session.createTextMessage(messageText);
            producer.send(message);
            session.commit();

            System.out.println("Message envoyé: " + messageText);
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}