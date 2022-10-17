/* DB-Scan Algorithm on single dimensional data */

import java.util.*;

public class DBScan {

	static void dbs(int n, ArrayList<Integer> input, double erad, int minpts) {
		int visited[] = new int[n];
		int vis = 0;
		int no = 0;
		int nclu = 0;
		int inc = 0;
		Set<Integer>[] finalclu = new LinkedHashSet[n];
		finalclu[nclu] = new LinkedHashSet<Integer>();
		ArrayList<Integer> outlier=new ArrayList<Integer>();
		while (vis < n) {
			ArrayList<Integer> cluster = new ArrayList<Integer>();
			int pt = input.get(no);
			for (int i = 0; i < n; i++) {
				int p1 = input.get(i);
				double d = Math.abs(pt - p1);
				if (d <= erad) {
					cluster.add(p1);
				}
			}
			if (cluster.size() < minpts && finalclu[nclu].size() == 0) {
				no++;
				if (no == n) {
					for (int i = 0; i < n; i++) {
						if (visited[i] == 0) {
							outlier.add(input.get(i));
							visited[i] = 1;
							vis++;
						}
					}
				}
				while (no < n - 1 && visited[no] == 1) {
					no++;
				}
			} else if (cluster.size() < minpts && finalclu[nclu].size() != 0) {
				visited[no] = 1;
				vis++;
				int flag = 0;
				Iterator<Integer> j = finalclu[nclu].iterator();
				while (j.hasNext()) {
					no = input.indexOf(j.next());
					if (visited[no] == 0) {
						flag = 1;
						pt = input.get(no);
						break;
					}
				}
				int f1 = 0;
				if (flag == 0) {
					for (int i = 0; i < n; i++) {
						if (visited[i] == 0) {
							f1 = 1;
							no = i;
							pt = input.get(no);
							break;
						}
					}
					nclu++;
					finalclu[nclu] = new LinkedHashSet<Integer>();
				}
				if (f1 == 0 && flag == 0)
					break;
			} else if (cluster.size() >= minpts) {
				visited[no] = 1;
				vis++;
				finalclu[nclu].addAll(cluster);
				int flag = 0;
				Iterator<Integer> j = finalclu[nclu].iterator();
				while (j.hasNext()) {
					no = input.indexOf(j.next());
					if (visited[no] == 0) {
						flag = 1;
						pt = input.get(no);
						break;
					}
				}
				int f1 = 0;
				if (flag == 0) {
					for (int i = 0; i < n; i++) {
						if (visited[i] == 0) {
							f1 = 1;
							no = i;
							pt = input.get(no);
							break;
						}
					}
					nclu++;
					finalclu[nclu] = new LinkedHashSet<Integer>();
				}
				if (f1 == 0 && flag == 0)
					break;
			}
		}

		for (int i = 0; i < nclu; i++) {
			System.out.println("Cluster " + (i + 1) + ":");
			Iterator<Integer> j = finalclu[i].iterator();
			while (j.hasNext()) {
				System.out.print(j.next() + " ");
			}
			System.out.println();
		}
		System.out.print("\nOutliers:\t");
		for(int i=0; i<outlier.size(); i++)
		{
			System.out.print(outlier.get(i)+" ");
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of values to input:");
		int n = sc.nextInt();
		ArrayList<Integer> input = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			System.out.println("For input " + (i + 1) + ":");
			System.out.println("Enter value:");
			int x = sc.nextInt();
			input.add(x);
		}

		System.out.println("Enter E radius:");
		double erad = sc.nextDouble();

		System.out.println("Enter MinPoints:");
		int minpts = sc.nextInt();

		dbs(n, input, erad, minpts);
	}

}


/*

OUTPUT:

Enter no of values to input:
14
For input 1:
Enter value:
1
For input 2:
Enter value:
2
For input 3:
Enter value:
3
For input 4:
Enter value:
4
For input 5:
Enter value:
5
For input 6:
Enter value:
6
For input 7:
Enter value:
8
For input 8:
Enter value:
13
For input 9:
Enter value:
19
For input 10:
Enter value:
80
For input 11:
Enter value:
81
For input 12:
Enter value:
82
For input 13:
Enter value:
83
For input 14:
Enter value:
84
Enter E radius:
2
Enter MinPoints:
4
Cluster 1:
1 2 3 4 5 6 8 
Cluster 2:
80 81 82 83 84 

Outliers:	13 19 

*/
