package com.wq.activemq;import com.wq.activemq.entity.Activemq;import org.apache.activemq.command.ActiveMQTextMessage;import javax.jms.*;public class ActivemqMessage implements ActivemqFactory {    private Activemq activemq = new Activemq();    @Override    public void createMessageProducer(String id,int type, String name) throws JMSException{        Destination destination = createDestination(type,name);        //5、创建消息生产者        MessageProducer messageProducer = activemq.getSession().createProducer(destination);        activemq.setMessageProducer(id,messageProducer);    }    @Override    public void sendMessage(String id, String message) throws JMSException{        MessageProducer messageProducer = activemq.getMessageProducer(id);        //6、创建消息 - 文本消息        ActiveMQTextMessage activeMQTextMessage = new ActiveMQTextMessage();        activeMQTextMessage.setText(message);        //7、发送文本消息        messageProducer.send(activeMQTextMessage);//        //8、关闭消息发送者、Session、Connection//        messageProducer.close();//        session.close();//        connection.close();    }    @Override    public void CreateMessageConsumer(String id,int type,String name) throws JMSException{        Destination destination = createDestination(type,name);        //6、创建消费者        MessageConsumer consumer = activemq.getSession().createConsumer(destination);        activemq.setMessageConsumer(id,consumer);//        //7、获取消息//        Message message = consumer.receive(100);////        if (message instanceof TextMessage) {//                TextMessage textMessage = (TextMessage) message;//                System.out.println("----------------------------------");//                System.out.println("消费消息：" + textMessage.getText());//        }//        //8、关闭消息发送者、Session、Connection//        consumer.close();//        session.close();//        connection.close();    }    @Override    public String   acceptMessage(String id) throws JMSException {        MessageConsumer consumer = activemq.getMessageConsumer(id);        Message message = consumer.receive(100);        if (message instanceof TextMessage) {            TextMessage textMessage = (TextMessage) message;            System.out.println("----------------------------------");            System.out.println("消费消息：" + textMessage.getText());            return textMessage.getText();        }        return null;    }    Destination createDestination(int type,String name) throws JMSException{        /**         * 5、创建消息目的:         * 第一种 Queue：点对点消息         * 第二种 Topic：广播式消息         */        Destination destination;        switch (type){            case 0:                destination = activemq.getSession().createQueue(name);                break;            case 1:                destination = activemq.getSession().createTopic(name);                break;            default:                destination = activemq.getSession().createQueue(name);        }        return  destination;    }}