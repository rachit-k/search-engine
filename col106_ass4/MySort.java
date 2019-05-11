import java.util.ArrayList;

public class MySort<Sortable extends Comparable<Sortable>>
{
	MySort()
	{

	}
	
	ArrayList<Sortable> sortThisList(MySet<Sortable> listOfSortableEntries)
	{
		int l=(int)listOfSortableEntries.set_size();
		ArrayList<Sortable> arr = new ArrayList<Sortable>(l);
		node<Sortable> temp= listOfSortableEntries.ll.head;
		for(int i=0;i<l;i++)
		{
			arr.add(temp.a);
			temp=temp.next;
		}
		for(int i=0;i<l;i++)
		{
			for(int j=i;j<l;j++)
			{
				int f=arr.get(i).compareTo(arr.get(j));
				if(f<0)//(i)<(j)
				{
					Sortable arrrr=arr.get(i);
					arr.set(i,arr.get(j));
					arr.set(j,arrrr);
				}
			}
		}
		return arr;
	}
}