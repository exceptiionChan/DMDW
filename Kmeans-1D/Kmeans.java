/* Kmeans with single dimensional data */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class KMeans {

	//no of clusters
	public static int k;
	
	//Arraylist that will store input points
	public static ArrayList<Integer> input;
	
	//array for centroids
	public static int centroid[];
	
	//array that stores an arraylist each arraylist has one cluster
	public static ArrayList<Integer>  cluster[];
	
	public static Scanner sc=new Scanner(System.in);
	
	//accept the inputs
	public void accept()
	{
		//ask the size of input
		System.out.println("Enter the size of input : ");
		int size = sc.nextInt();
		
		//ask for the inputs and add it to the input list
		System.out.println("Enter the input array : ");
		input=new ArrayList<Integer>();
		for (int i=0;i<size;i++)		
			input.add(sc.nextInt());		
		
		//ask for the no of clusters required and store it in k
		System.out.println("Enter the no of clusters : ");
		k=sc.nextInt();
		
	    //ask for inital centroids and store it in an array
		centroid=new int [k];
		System.out.println("Enter the inital centroids : ");
		 for (int i=0;i<k;i++)		 
			 centroid[i]=sc.nextInt();		 
		 
		 //initalize the cluster
		 cluster=new ArrayList[k];
		 for(int i=0;i<k;i++)		 
			 cluster[i]=new ArrayList();		 
		 
	}
	
	public void display()
	{
		System.out.println("Input array :");
		for(int i=0;i<input.size();i++)		
			System.out.print(input.get(i)+" ");		
		
		System.out.println("\n\nNo of clusters : "+ k);
		for(int i=0;i<k;i++)
		{
			System.out.print("cluster "+(i+1)+" = ");
			System.out.println(cluster[i]);
		}
		System.out.println();
	}
	
	public int distance(int cent ,int point)
	{
         if (cent>point)
        	 return cent-point;
         else
        	 return point-cent;
	}
	
	public void kmeansAlgo()
	{ 
	  int flag=1;
	  do
	  {
		    int dist=0,min,index=0;
		    flag=0;
			int sum[]=new int [k];
			for(int i=0;i<k;i++)
			{
				cluster[i].clear();
			}
			for(int i=0;i<input.size();i++)
			{
				min=1000;
				for(int j=0;j<centroid.length;j++)
				{
					dist=distance(centroid[j],input.get(i));
					if(dist<min)
					{
						min=dist;
						index=j;
					}
				}
				cluster[index].add(input.get(i));
				sum[index]=input.get(i)+sum[index];
			}
			
		   for(int i=0;i<k;i++)
		   {
			   sum[i]=sum[i]/cluster[i].size();
			   if(sum[i]==centroid[i])
				   flag++;
			   centroid[i]=sum[i];
		   }	   
	  }while(flag!=k);
		
	}
	public static void main(String[] args) {
		
            KMeans km=new KMeans();
            String ans;
            do {
            
			System.out.println("************INPUT*************");
            km.accept();
         
            System.out.println("\nCalculating the k-mean... \n");
            km.kmeansAlgo();
            
			System.out.println("Clusters are as follows : ");
            km.display();
			
            System.out.println("Do you want to continue?[y/n]");
            ans=sc.next();
			
            }while(ans.equals("y"));
	}

}


/*


************INPUT*************
Enter the size of input : 
12
Enter the input array : 
10
20
15
18
91
48
61
17
24
39
65
43
Enter the no of clusters : 
3
Enter the inital centroids : 
91
10
48

Calculating the k-mean... 

Clusters are as follows : 
Input array :
10 20 15 18 91 48 61 17 24 39 65 43 

No of clusters : 3
Cluster 1 = [91]
Cluster 2 = [10, 20, 15, 18, 17, 24]
Cluster 3 = [48, 61, 39, 65, 43]

Do you want to continue?[y/n]
  
*/
