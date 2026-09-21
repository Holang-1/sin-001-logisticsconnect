package co.wethinkcode.logisticsconnect.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class DelayStagePublisher {

    public void publish(String hubId, int delayStage) {

        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory(MqConfig.BROKER_URL);

        try (Connection connection = factory.createConnection()) {

            Session session = connection.createSession(
                    false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(MqConfig.TOPIC);
            MessageProducer producer = session.createProducer(topic);

            String message = """
                    {
                        "hubId": "%s",
                        "stage": %d
                    }
                    """.formatted(hubId, delayStage);

            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);

            System.out.println("Published delay stage: " + message);

            producer.close();
            session.close();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to publish delay stage", e);
        }
    }
}