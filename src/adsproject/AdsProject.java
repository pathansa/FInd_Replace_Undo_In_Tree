/*
In a tree, it is necessary to update all values with some factor "x".  For example if value is 10 and x = 5, new value will be 15,
now if there 50 such nodes in tree, the program should generate an array of all such 50 nodes. The program
will start updating value and after every "Y" updates, it breaks and on user input either the update is continued or roll back.
*/

package adsproject;
import java.util.ArrayList;
import java.util.Scanner;

class Node{
    int datax;
    Node left;
    Node right;
    Node parent;
    public Node(int data1){
        this.datax = data1;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}
class Ads{
    Node root = null;
    ArrayList<Node> list=new ArrayList<Node>();
    ArrayList<Integer> clist=new ArrayList<Integer>();
    ArrayList<Integer> count=new ArrayList<Integer>();
    ArrayList<Integer> nodeloc=new ArrayList<Integer>();
    void insert(Node x){
        if(root == null){
            root = x;
            }
        else{
            Node y=null;
            Node z = root;
            while(z!=null){
                y = z;
                if(x.datax<z.datax)
                    z = z.left;   
                else
                    z = z.right;
            }
            x.parent = y;
            if(y == null)
                root = x;
            else{
                if(x.datax<y.datax)
                    y.left = x;
                else
                    y.right = x;
            }
            x.left = null;
            x.right = null; 
        }
    }
    void printLevelOrder() 
    { 
        int h = height(root); 
        int i; 
        for (i=1; i<=h; i++) 
            printGivenLevel(root, i); 
    } 
  
    int height(Node root) 
    { 
        if (root == null) 
           return 0; 
        else
        { 
            /* compute  height of each subtree */
            int lheight = height(root.left); 
            int rheight = height(root.right); 
              
            /* use the larger one */
            if (lheight > rheight) 
                return(lheight+1); 
            else return(rheight+1);  
        } 
    } 
  
    /* Print nodes at the given level */
    void printGivenLevel (Node root ,int level) 
    { 
        if (root == null){
            return; }
        if (level == 1) 
            clist.add(root.datax); 
        else if (level > 1) 
        { 
            printGivenLevel(root.left, level-1); 
            printGivenLevel(root.right, level-1); 
        }
       
    }
    void PrintArrayList(ArrayList<Integer> arr){
        for(Integer i:arr)
            System.out.print(i+" ");
        System.out.println();
    }
    
    
    void update(int x,int up){
        int flag=0;
        for(int i=0;i<clist.size();i++){
            if(up==clist.get(i)){
               if(flag==0)
                count.add(0);
                flag=1;
                clist.set(i,clist.get(i)+x);
                nodeloc.add(i);
               count.set(count.size()-1,count.get(count.size()-1)+1);
            }
        }
        PrintArrayList(clist);
    }
    void rollback(int x){
        int z=nodeloc.get(nodeloc.size()-1);
        int y=count.get(count.size()-1);
        int d=y;
        for(int i=z;y>0;y--,i--){
            clist.set(i,clist.get(i)-x);
        }
        for(int i=0;i<d;i++)
            nodeloc.remove(nodeloc.size()-1);
        count.remove(count.size()-1);
        PrintArrayList(clist);
    }
    public void print(Node n){
        if(n==null)
            System.out.println("Empty tree!!");
        else{
            if(n.parent!=null)
                System.out.println("Node==> ["+n.datax+" ]---------------> Parent=["+n.parent.datax+"]");
            else
                System.out.println("Node:[ "+n.datax+" ]<------Root element*");    
            if(n.left!=null)
                print(n.left);
            if(n.right!=null)
                print(n.right);
        }
    }
   
}

public class AdsProject{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Ads rb = new Ads();
        while(true){
        int c=0;
        int[] inp=new int[50];
        System.out.println("Enter values to be inserted in Tree(max 50)");
        for(int i=0;i<50;i++){
            int p=sc.nextInt();
            if(p==-1)
                break;
            c++;
            inp[i]=p;
        }
        Node n[]=new Node[c];
        for(int i=0;i<c;i++){
            n[i] = new Node(inp[i]);
        }
        for(int i=0;i<c;i++)
            rb.insert(n[i]);
         rb.print(rb.root);
        System.out.println("Values are inserted");
            System.out.println("Press 1 to insert more..");
            if(sc.nextInt()!=1)
                break;
        }
        rb.printLevelOrder();
        rb.PrintArrayList(rb.clist);
        System.out.println("Enter the value of X");
        int x=sc.nextInt();
        System.out.println("Enter the Node Value to  be updated");
        int up=sc.nextInt();
        rb.update(x,up);
        while(true){
        System.out.println("Press 1 to Update 0 to Rollback");
        int ui=sc.nextInt();
        if(ui==1){
            System.out.println("Enter the Value to  be updated");
            int up1=sc.nextInt();
            rb.update(x, up1);
        }
        else
           rb.rollback(x);
        }
    }
}