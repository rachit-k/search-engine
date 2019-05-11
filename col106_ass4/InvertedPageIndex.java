import java.lang.Math;

public class InvertedPageIndex
{
	public MySet<PageEntry> pageEntryset;
	public MyHashTable hashtable;

	InvertedPageIndex()
	{
		pageEntryset= new MySet<PageEntry>();
		hashtable=new MyHashTable();
	}

	void addPage(PageEntry p)
	{
		pageEntryset.addElement(p);
		hashupdated(p);

	}
	private void hashupdated(PageEntry p)
	{
		MyLinkedList<WordEntry> tt=p.pageindex.getWordEntries();
		//System.out.println("Size of linked list is "+tt.getfreq());
		node<WordEntry> temp=tt.head;
		while(temp!=null)
		{
			//System.out.println("Size of linked list is "+temp.a.positions.getfreq());
			hashtable.addPositionsForWord(temp.a);
			//System.out.println("Size of linked list is "+temp.a.positions.getfreq());
			//System.out.println(temp.a.word+" adding line");
			temp=temp.next;
		}
	}

	MySet<PageEntry> getPagesWhichContainWord(String str) throws wordnotfoundexp
	{
		try
		{
			WordEntry w=hashtable.searchword(str);
			//System.out.println(w.word);

			//hashtable.getPages(str);
			MySet<PageEntry> s =new MySet<PageEntry>();
			
			MyLinkedList<Position> aaaa=w.getAllPositionsForThisWord();
			//System.out.println(aaaa.getfreq()+" is the no. of elements in linked list of position for "+str);
			node<Position> temp=aaaa.head;
			while(temp!=null)
			{
				//System.out.println("hh");
				if(temp.a!=null)
				{
					//System.out.println("hh");

					s.addElement(temp.a.p);
					//System.out.println(s.set_size());
				}

				//c++;
				//System.out.println(c+" No. of pages added");
				temp=temp.next;
			}
			return s;		
		}
		catch(wordnotfoundexp e)
		{
			//System.out.println("11");
			throw new wordnotfoundexp();
		}
	}

	public float getRelevancesum(String[] str, PageEntry pe) throws wordnotfoundexp
	{
		try
		{
			float ans=0.0f;
			for(int i=0;i<str.length;i++)
			{
				String[] strr=new String[1];
				strr[0]=str[i];
				float x=pe.getRelevanceOfPage(strr,false);
				float y=pageEntryset.set_size();
				MySet<PageEntry> qq=getPagesWhichContainWord(str[i]);
				float z=qq.set_size();
				ans=ans+x*(float)Math.log(y/z);
			}
			return ans;
		}
		catch(wordnotfoundexp e)
		{
			throw new wordnotfoundexp();
		}
	}

	public float getRelevancephrase(String[] str, PageEntry pe) throws wordnotfoundexp
	{
		try
		{
			float x=pe.getRelevanceOfPage(str,true);
			float y=pageEntryset.set_size();
			MySet<PageEntry> qq=getPagesWhichContainPhrase(str);
			float z=qq.set_size();
			float ans=x*(float)Math.log(y/z);
			return ans;
		
		}
		catch(wordnotfoundexp e)
		{
			throw new wordnotfoundexp();
		}

	}

	public MySet<PageEntry> getPagesWhichContainPhrase(String[] str) throws wordnotfoundexp
	{
		try
		{
			MySet<PageEntry> s =new MySet<PageEntry>();
			int l=str.length;
			//WordEntry[] w=new WordEntry[l];
			MyLinkedList<WordEntry> wl=new MyLinkedList<WordEntry>();
			for(int i=0;i<l;i++)
			{
				wl.insert(hashtable.searchword(str[i]));
			}

			getPagesWhichContainPhraseHelper(wl,s);
			return s;
		}
		catch(wordnotfoundexp e)
		{
			//System.out.println("11");
			throw new wordnotfoundexp();
		}	
	}

	void getPagesWhichContainPhraseHelper(MyLinkedList<WordEntry> wl,MySet<PageEntry> s) throws wordnotfoundexp
	{
		
			int l=(int)wl.getfreq();
			MyLinkedList<AVLnode> aa=wl.head.a.positions.getAVLlist();
			//MyLinkedList<Position> aaa=wl.head.next.a.getAllPositionsForThisWord();
			node<AVLnode> temp=aa.head;
			//node<Position> temp1=aaa.head;
			while((temp!=null)&&(wl.head.next!=null))
			{
				AVLnode temp1;
				//System.out.println("Come");
				try
				{
				 temp1 = wl.head.next.a.positions.findd(temp.a.i+1);
				// System.out.println(wl.head.next.a.word+"Word was found");
				}
				catch(wordnotfoundexp e)
				{
					//System.out.println(wl.head.next.a.word+"Word was never found");
					temp=temp.next;
					continue;
				}

				MySet<PageEntry> sss=temp.a.pe.intersection(temp1.pe);
				//System.out.println("Size of sss is "+sss.set_size()+sss.ll.head.a.pagename);
					if((int)sss.set_size()!=0)
					{
						if(l<3)
						{
							//System.out.println("Size of sss is "+sss.set_size());
							s=s.union(sss);
							//System.out.println("Size of s is "+s.set_size());
						}
						else
						{
							wl.head=wl.head.next;
							getPagesWhichContainPhraseHelper(wl,s);
						}
					}	
					
				temp=temp.next;
			}
			//throw new wordnotfoundexp();	
		
			

	}
}
