import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class avgNum implements Runnable{
    static List<Node> nodes = new ArrayList<>();
    static double k= 10;
    static Scanner sc = new Scanner(System.in);
    static int total=sc.nextInt();
    static Random rand = new Random();
    private Node node;

    public static void main(String[] args){
       for(int i=0;i<total;i++){
           Node node=new Node(i);
           nodes.add(node);
           System.out.println("Node " + i + "初值：" + node.val);
       }

        for (Node node : nodes) {
            Thread t = new Thread(new avgNum(node));
            t.start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       int totalValue=0;
        for (Node node : nodes) {
            totalValue += node.val;
        }
        double average = totalValue / total;
        System.out.println("平均值: " + average);
    }

    public avgNum(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        while (!node.removed) {
            int target;
            do {
                target = rand.nextInt(total);
            } while (target == node.id);

            Node targetNode = nodes.get(target);

            if (!targetNode.removed && !targetNode.infected) {
                double newValue = (node.val + targetNode.val) / 2.0;

                node.val=newValue;
                targetNode.val=newValue;

                node.infected= true;
                targetNode.infected= true;

              }else if(!targetNode.removed&&targetNode.infected){
                if(rand.nextDouble()<1/k)
                    node.removed = true;
            }
        }

    }

    static class Node {
        int id;
        boolean infected = false;
        boolean removed=false;
        double val;

        public Node(int id) {
            this.id = id;
            this.val = rand.nextDouble()*100;
        }
    }
}
