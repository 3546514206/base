package edu.zjnu.base.base.cyclicDependencies;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-08-27
 **/
class Message {

    private Channel channel;
    private String title;
    private String content;

    public Message (Channel channel,String title,String content){
        this.channel = channel;
        this.title = title;
        this.content = content;
    }

    public void send(){
        if(this.channel.isConnect()){
            System.out.println("【消息发送】title="+this.title+",content="+this.content);
        }else{
            System.out.println("【ERROR】");
        }
    }

}
