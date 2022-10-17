/* Kmeans with 2D data in .csv file */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

class Point
{
	int x;
	int y;
	Point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}

}

class Kmeans
{
	Scanner sc=new Scanner(System.in);
	Point arr[];
	Point centroids[];
	int k;
	int count=0;
	int num;
	void accept1()
	{
		String csvFile ="/home/ccoew/Documents/3475/K-Means2D/Data.csv";		

		try
		{
			sc = new Scanner(new File(csvFile));
			while(sc.hasNext())
			{
				String s=sc.next();
				String h[]=s.split(",");
				count++;
			}
			int j=0;
			arr=new Point[count];
			
			Scanner sc2 = new Scanner(new File(csvFile));
			System.out.println("The points are: ");
			while(sc2.hasNext())
			{
				String s=sc2.next();
				String h[]=s.split(",");

				arr[j]=new Point(Integer.parseInt(h[0]),Integer.parseInt(h[1]));
				System.out.println(arr[j].x + ","+arr[j].y);
				j++;

			}
			
			System.out.println("Enter the size of k: ");
			k=sc.nextInt();

			System.out.println("Enter the centroids: ");
			centroids=new Point[k];

			for(int i=0;i<k;i++)
			{
				System.out.println("Enter the x co-ordinate of point "+(i+1)+": ");
				int x=sc.nextInt();
				System.out.println("Enter the y co-ordinate of point "+(i+1)+": ");
				int y=sc.nextInt();

				centroids[i]=new Point(x,y);
			}

			sc.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

}


void operation()
{
	ArrayList<Point> arr1[];

	Point mean[]=new Point[k];
	int flag=0;
	do
	{
		flag=0;
		arr1= new ArrayList[k];
		for(int i=0;i<k;i++)
		{
			ArrayList <Point> c1 = new ArrayList<Point>();
			arr1[i]=c1;
		}
		for(int j=0;j<count;j++)
		{
			double min=Double.MAX_VALUE;
			int index=0;
			for(int i=0;i<k;i++)
			{
				double dist = Math.sqrt(Math.pow((centroids[i].x-arr[j].x), 2) + Math.pow((centroids[i].y-arr[j].y),2));
				if(min>dist)
				{
					min=dist;
					index=i;
				}
			}

			arr1[index].add(arr[j]);
		}

		int sum_x=0;
		int sum_y=0;
		int mean_x;
		int mean_y;
		for(int i=0;i<k;i++)
		{
			mean_x=0;
			mean_y=0;
			sum_x=0;
			sum_y=0;
			if(arr1[i].size()!=0)
			{
				for(int j=0;j<arr1[i].size();j++)
				{
					sum_x=sum_x+arr1[i].get(j).x;
					sum_y=sum_y+arr1[i].get(j).y;
				}
				mean_x=(sum_x/arr1[i].size());
				mean_y=(sum_y/arr1[i].size());

				mean[i] =new Point(mean_x,mean_y);
			}
			else
			{
				mean_x=0;
				mean_y=0;
				mean[i] =new Point(mean_x,mean_y);
			}

		}

		for(int i=0;i<k;i++)
		{
			if((mean[i].x!=centroids[i].x) && (mean[i].y!=centroids[i].y))
			{
				flag=1;
				centroids[i].x=mean[i].x;
				centroids[i].y=mean[i].y;
			}
		}

	}while(flag==1);

	for(int i=0;i<k;i++)
	{
		Iterator<Point> itr = arr1[i].iterator();
		System.out.print("Cluster "+(i+1)+": ");
		while(itr.hasNext())
		{
			Point s=(Point) itr.next();
			System.out.print("("+s.x+","+s.y+")\t");
		}
		System.out.println("\n");
	}

	}
}

public class KmeanCsv {

	public static void main(String[] args) {

		Kmeans k=new Kmeans();
		k.accept1();
		k.operation();
	}

}




/*

OUTPUT:

The points are: 
2,10
2,5
8,4
5,8
7,5
6,4
1,2
4,9
Enter the size of k: 
3
Enter the centroids: 
Enter the x co-ordinate of point 1: 
2
Enter the y co-ordinate of point 1: 
10
Enter the x co-ordinate of point 2: 
5
Enter the y co-ordinate of point 2: 
8
Enter the x co-ordinate of point 3: 
1
Enter the y co-ordinate of point 3: 
2
Cluster 1: (2,10)	(5,8)	(4,9)	

Cluster 2: (8,4)	(7,5)	(6,4)	

Cluster 3: (2,5)	(1,2)

*/
