
public class PageIndex
{
	public MyLinkedList<WordEntry> wes;

	PageIndex()
	{
		wes = new MyLinkedList<WordEntry>();
	}

	public void addPositionForWord(String str, Position p, int k)
	{
		WordEntry x= new WordEntry(str);
		if(this.Ismember(str))
		{			
			//if(str.equals("stack"))
			//	;

			getthatword(str).addPosition(p,k);
		}
		else
		{
			wes.insert(x);
			x.addPosition(p,k);
		}
	}

	public float getwordfreq()
	{
		float flag=0;
		node<WordEntry> temp=wes.head;
		if(wes.head==null)
		{
			;
		}
		else
		{
			while(!(temp==null))
			{
				flag++;
				temp=temp.next;	
			}
		}
		return flag;
	}

	public float gettotalfreqinpage(PageEntry pagename)
	{
		float flag=0.0f;
		node<WordEntry> temp=wes.head;
		if(wes.head==null)
		{
			;
		}
		else
		{
			while(!(temp==null))
			{
				float t=getwordfreqinpage(pagename,temp.a);//
				flag=flag+t;
				temp=temp.next;	
			}
		}
		return flag;
	}

	public float getwordfreqinpage(PageEntry page,WordEntry ww)
	{
		float flag=0;
		node<WordEntry> temp=wes.head;
		if(wes.head==null)
		{
			;
		}
		else
		{
			while(!(temp==null))
			{
				if(temp.a==ww)
				{
					flag=flag+temp.a.positions.postorder2(temp.a.positions.root,page);//not sure if flag changes this way
				}
				temp=temp.next;	
			}
		}
		return flag;
	}

	private Boolean Ismember(String str)
	{
		node<WordEntry> temp=wes.head;

		while(temp!=null)
		{
			if(temp.a.word.equals(str))
			{
				return true;
			}
			temp=temp.next;			
		}		
		return false;
	}

	public WordEntry getthatword(String str)
	{
		node<WordEntry> temp=wes.head;
		WordEntry ccc=new WordEntry();
		while(temp!=null)
		{
			if(temp.a.word.equals(str))
				return temp.a;
			temp=temp.next;
		}
		return ccc;
	}


	MyLinkedList<WordEntry> getWordEntries()
	{
		return wes;
	}
	
}