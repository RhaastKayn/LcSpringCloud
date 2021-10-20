package com.lc.shoppingmall.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author 刘晨
 * @description rabbitmq中的消息的生产者
 * @create 2021/10/20 0020
 * @since 1.0.0
 */
public class Producer {
    public static void main(String[] args) {
        //1.创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        //虚拟访问节点
//        connectionFactory.setVirtualHost("/");
        //2.创建连接
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection("生产者");
            //3.通过连接获取通道channel 为什么rabbitmq是通过channel来处理消息，而不是通过链接来处理？ 因为短链接要经过三次握手，四次挥手，会很慢，而且连接开关也会损耗性能。而高并发下长链接里面有多个channel，可以进行快速处理
            channel = connection.createChannel();
            //4.通过通道，创建减缓及，声明队列，绑定关系，路由key，发送消息，接收消息
            String queueName = "queue1";
            /**
             * @param1 队列名
             * @param2 是否要持久化 true持久化 false不持久化  如果不持久化，服务器重启或故障，队列就会消失。非持久化，也会将消息存盘，但会随着服务器重启而消失
             * @param3 是否具有排他性
             * @param4 是否自动删除，当最后一个消费者消费完消息后，队列是否自动删除
             * @param5 附加参数
             */
            //queueDeclare(队列名，是否要持久化，是否具有排他性，是否自动删除，携带的其他参数)
            //创建队列
            channel.queueDeclare(queueName, false, false, false, null);
            //5.准备消息内容
            String message = "test message";
            //6.发送消息给队列
            /**
             * @param1 交换机 虽然当前没有指定交换机，但一定会有一个默认的交换机，所以说不可能存在没有交换机的队列
             * @param2 队列名、路由key
             * @param3 消息的状态控制
             * @param4 消息的主体
             */
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println("消息发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //7.关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //8.关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
