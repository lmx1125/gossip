import java.util.Random;
import java.util.Scanner;

public class main implements Runnable {
    private static String winner;//胜利者

    public void run() {
        for(int steps=1;steps<=100;steps++){
            System.out.println(Thread.currentThread().getName()+"-->"+steps);
            //模拟休息
            if(Thread.currentThread().getName().equals("rabbit")&&steps%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //比赛是否结束
            boolean flag = gameOver(steps);
            if(flag){
                break;
            }
        }
    }

    private boolean gameOver(int steps){
        if(winner!=null){//存在胜利者
            return true;
        }else{
            if(steps == 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner==>"+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        main racer = new  main();
        new Thread(racer,"tortoise").start();
        new Thread(racer,"rabbit").start();
    }

}
