
public class MyHashTable
{
	public WordEntryList[] wlistarray;

	MyHashTable(/*WordEntry x*/)
	{
		wlistarray=new WordEntryList[1019];

		//wlistarray[0]=x;
	} 

	public int getHashIndex(String str)
	{
		int t=(str.hashCode())%1019;
		if(t<0)
        	t=t+1019;
        return t;
	}

	public void addPositionsForWord(WordEntry w)
	{
		int x =getHashIndex(w.word);
		//System.out.println("3");
		if(wlistarray[x]==null)
		{
			wlistarray[x] =new WordEntryList(w);
			//wlistarray[x].insert(w);
			//if(wlistarray[x].ismember(w))
				//System.out.println("9");
			//WordEntryList temp=new WordEntryList(w);
			//wlistarray[x]=temp;
			//System.out.println(wlistarray[x].head.a.word);
			//System.out.println("4");
		}
		
		else
		{
			try
			{
				WordEntry xxx=searchword(w.word);
				/*if(w.word.equals("stack"))
				{
					System.out.println("Found again");
				}*/
				xxx.addPositions(w.positions);//
			}
			catch(wordnotfoundexp e)
			{
				wlistarray[x].insert(w);

			}
			
		}
		//System.out.println(wlistarray[x].head.a.word);
	}

	public WordEntry searchword(String str) throws wordnotfoundexp
	{
		int x=getHashIndex(str);
		if(wlistarray[x]==null)
		{
			//System.out.println("22");
			throw new wordnotfoundexp();
		}
		else
		{
			node<WordEntry> temp=wlistarray[x].head;
			while(temp!=null)
			{
				if((temp.a.word).equals(str))
				{
					//System.out.println("55");
					return temp.a;
				}
				temp=temp.next;
				//System.out.println("44");
			}
			//System.out.println("33");
			throw new wordnotfoundexp();
		}
	}

	public Boolean containsword(String str) 
	{
		int x=getHashIndex(str);
		if(wlistarray[x]==null)
		{
			//System.out.println("22");
			return false;
		}
		else
		{
			node<WordEntry> temp=wlistarray[x].head;
			while(temp!=null)
			{
				if((temp.a.word).equals(str))
				{
					//System.out.println("55");
					return true;
				}
				temp=temp.next;
				//System.out.println("44");
			}
			//System.out.println("33");
			return false;
		}
	}
}
