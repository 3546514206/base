package edu.zjnu.base.base.cyclicDependencies;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-08-27
 **/

class Channel {

    private Message message;

    public Channel(String title,String content){
        this.message = new Message(this,title,content);
        this.message.send();
    }

    public boolean isConnect(){
        return true;
    }
}