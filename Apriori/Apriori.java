/* Apriori Algorithm */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Transaction {
	int id;
	ArrayList<String> ele;

	Transaction(int id, ArrayList<String> ele) {
		this.id = id;
		this.ele = ele;
	}
}

public class Apriori {

	static void apriori(ArrayList<Transaction> trans, int minsupport) {
		ArrayList<String> itemsets[] = new ArrayList[10];
		ArrayList<Integer> count[] = new ArrayList[10];
		int level = 0;
		itemsets[level] = new ArrayList<String>();
		count[level] = new ArrayList<Integer>();
		for (int i = 0; i < trans.size(); i++) {
			Transaction curr = trans.get(i);
			int sz = curr.ele.size();
			for (int j = 0; j < sz; j++) {
				if (itemsets[level].contains(curr.ele.get(j)) == false) {
					itemsets[level].add(curr.ele.get(j));
					count[level].add(1);
				} else {
					count[level].set(itemsets[level].indexOf(curr.ele.get(j)),
							count[level].get(itemsets[level].indexOf(curr.ele.get(j))) + 1);
				}
			}
		}
		for (int i = 0; i < count[level].size(); i++) {
			if (count[level].get(i) < minsupport) {
				itemsets[level].remove(i);
				count[level].remove(i);
			}
		}
		level++;
		
		while(true)
		{
			itemsets[level] = new ArrayList<String>();
			count[level] = new ArrayList<Integer>();
			for(int i=0; i<itemsets[level-1].size(); i++)
			{
				for(int j=i+1; j<itemsets[level-1].size(); j++)
				{
					String temp=itemsets[level-1].get(i)+itemsets[level-1].get(j);
					String[] res=check_temp_in_db(trans, temp, level);
					int cnt=Integer.parseInt(res[1]);
					if(cnt>=minsupport && itemsets[level].contains(res[0])==false)
					{
						itemsets[level].add(res[0]);
						count[level].add(cnt);
					}
				}
			}
			if(itemsets[level].size()<=1)
				break;
			level++;
		}
		
		System.out.println("Patterns observed");
		for (int i = 0; i <= level; i++) {
			for (int j = 0; j < itemsets[i].size(); j++) {
				System.out.println(itemsets[i].get(j) + "\t" +count[i].get(j) /*((double)count[i].get(j)/trans.size())*100+"%"*/);
			}
		}

	}

	static String[] check_temp_in_db(ArrayList<Transaction> trans, String temp, int level) {
		
		String p="";
		String arr[]=new String[2];
		ArrayList<Transaction> item=new ArrayList<Transaction>();
		item.addAll(trans);
		for(int i=0; i<temp.length(); i++)
		{
			if(p.contains(String.valueOf(temp.charAt(i)))==false)
			{
				p+=String.valueOf(temp.charAt(i));
				int j=0;
				while(j<item.size())
				{
					if(item.get(j).ele.contains(String.valueOf(temp.charAt(i)))==false)
						item.remove(j);
					else
						j++;
				}
				if(item.size()==0)
				{
					arr[0]=p;
					arr[1]="0";
					return arr;
				}
			}
		}
		char[] p1=p.toCharArray();
		Arrays.sort(p1);
		p=new String(p1);
		arr[0]=p;
		arr[1]=String.valueOf(item.size());
		if(p.length()!=level+1)
			arr[1]="0";
		return arr;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of transactions to input:");
		int n = sc.nextInt();
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		for (int i = 0; i < n; i++) {
			System.out.println("Enter Transaction ID:");
			int id = sc.nextInt();
			System.out.println("Enter No of Items Purchased:");
			int no = sc.nextInt();
			ArrayList<String> ele = new ArrayList<String>();
			System.out.println("Enter Items purchased for transaction " + id + ":");
			for (int j = 0; j < no; j++) {
				ele.add(sc.next());
			}
			Transaction t = new Transaction(id, ele);
			trans.add(t);
		}
		System.out.println("Enter Min Support value:");
		int minsupport = sc.nextInt();

		apriori(trans, minsupport);

	}

}


/*

OUTPUT:

Enter no of transactions to input:
4
Enter Transaction ID:
10
Enter No of Items Purchased:
3
Enter Items purchased for transaction 10:
A
C
D
Enter Transaction ID:
200
Enter No of Items Purchased:
3
Enter Items purchased for transaction 200:
B
C
E
Enter Transaction ID:
30
Enter No of Items Purchased:
4
Enter Items purchased for transaction 30:
B
C
A
E
Enter Transaction ID:
40
Enter No of Items Purchased:
2
Enter Items purchased for transaction 40:
B
E
Enter Min Support value:
2
Patterns observed
A	2
C	3
B	3
E	3
AC	2
BC	2
CE	2
BE	3
BCE	2

*/
