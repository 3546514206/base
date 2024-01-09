
}catch(FileNotFoundException e){
        e.printStackTrace();
        }catch(IOException e){
        // TODO Auto-generated catch block
        e.printStackTrace();
        }finally{
        // 4、释放资源 先打开的后关闭
        try{
        if(null!=bw){
        bw.close();
        }
        }catch(Exception e){
        e.printStackTrace();
        }
        try{
        if(null!=br){
        br.close();
        }
        }catch(Exception e){
        e.printStackTrace();
        }
        }
        }


//测试
public static void main(String[]args){
        copy("a.txt","copy1.txt");
        }
        }

