package co.wethinkcode.logisticsconnect.mq;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MqSubscriber {

    private final ActiveMQConnectionFactory connectionFactory;
    private final ObjectMapper mapper;

    private final String hubId;
    private int stage;

    public MqSubscriber(String hubId) {
        this.hubId = hubId;
        this.stage = 0;

        connectionFactory = new ActiveMQConnectionFactory(MqConfig.BROKER_URL);
        mapper = new ObjectMapper();
    }

    public void start() throws JMSException, javax.jms.JMSException {

        Connection connection = (Connection) connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(MqConfig.TOPIC);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(this::receiveMessage);

        connection.start();

        System.out.println("Transit service subscribed to " +
                MqConfig.TOPIC + " for hub " + hubId);
    }

    private void receiveMessage(Message message) {

        try {
            if (message instanceof TextMessage textMessage) {

                String body = textMessage.getText();
                System.out.println("Transit received: " + body);

                JsonNode json = mapper.readTree(body);

                String messageHubId = json.get("hubId").asText();
                int messageStage = json.get("stage").asInt();

                // Only accept messages for this subscriber's hub
                if (messageHubId.equals(hubId)) {

                    this.stage = messageStage;
                    System.out.println("Updated delay stage for " +
                            hubId + ": " + stage);
                }
            }

        } catch (Exception e) {
            System.err.println(
                    "Error processing MQ message: " + e.getMessage());
        }
    }

    public int getStage() {
        return stage;
    }
}