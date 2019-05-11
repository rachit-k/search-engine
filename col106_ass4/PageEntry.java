import java.util.*;
import java.io.*;

public class PageEntry
{
	public String pagename;
	public PageIndex pageindex;

	PageEntry(String pageName) throws FileNotFoundException
	{
		try
		{
			pagename = pageName;
			FileInputStream fstream = new FileInputStream(pagename);
			Scanner sc =new Scanner(fstream);
			int temp=0;
			//int c=0;
			String[] aa={ "a", "an", "the", "they", "these", "this", "for", "is", "are", "was", "of", "or", "and", "does", "will", "whose" };
			pageindex=new PageIndex();
			int c=0;
			while(sc.hasNextLine())
			{
				Scanner sc2 = new Scanner(sc.nextLine());
       			while (sc2.hasNext()) 
       			{
           			String s = sc2.next();
           			s=s.toLowerCase();
           			s=s.replaceAll("[<>=().{},;’”?#!-:]","");
					s=s.replaceAll("\\[","");
					s=s.replaceAll("\\]","");
					s=s.replaceAll("applications","application");// change this
					s=s.replaceAll("stacks","stack");
					s=s.replaceAll("structures","structure");
           			temp++;
           			int j=0;
           			for(int i=0;i<16;i++)
           			{
           				if(aa[i].equals(s))
           					{
           						j++;
           						c++;
           					}
           			}
           			Position p=new Position(this,temp);
           			//int j=0;

           			if(j==0)	
           			{	
           				pageindex.addPositionForWord(s,p,temp-c);

           			}
 				}
			}		
		}
		catch (FileNotFoundException e) 
    	{
			System.out.println("File not found");
			throw new FileNotFoundException();
		}
	}

	public float getRelevanceOfPage(String[] str, boolean doTheseWordsRepresentAPhrase)
	{
		//float ans=0.0;
		int l=str.length;

		if(doTheseWordsRepresentAPhrase)
		{
			WordEntry w=pageindex.getthatword(str[0]);
			MyLinkedList<AVLnode> aa=w.positions.getAVLlist();//do it
			float m=0.0f;
			node<AVLnode> temp=aa.head;
			while(temp!=null)
			{
				if(temp.a.pe.equals(this))
				{
					int ii=temp.a.i; 
					for(int i=1;i<l;i++)
					{
						ii++;
						WordEntry ww=pageindex.getthatword(str[i]);
						MySet<PageEntry> s=ww.positions.getpageEntries(ii);//
						if(!s.IsMember(this))
						{
							break;
						}
						else if(i==l-1)
						{
							m=m+1.0f;
						}
					}
				}
				temp=temp.next;
			}
			float b=pageindex.gettotalfreqinpage(this);//
			float ans= m/(b-(l-1)*m);
			return ans;
		}
		else
		{
			
			//for(int i=0;i<l;i++)
			//{
				WordEntry w=pageindex.getthatword(str[0]);
				float a=pageindex.getwordfreqinpage(this,w);//
				float b=pageindex.gettotalfreqinpage(this);//
				return a/b;
				//float c=w.getTermFrequency(str[i]);

			//}
		}
	}

	public boolean equals(PageEntry xx)

	{
		if(xx.pagename.equals(pagename))
			return true;
		else 
			return false;

	}

	public PageIndex getPageIndex()
	{
		return pageindex;
	}

}