import java.util.ArrayList;
import java.io.*;

public class SearchEngine
{
	public InvertedPageIndex ipi;
	//public String[] pagenames;

	SearchEngine()
	{
		ipi=new InvertedPageIndex();
	}

	void performAction(String actionMessage)
	{
		String[] s=actionMessage.split(" ");
		String[] x=new String[s.length-1];
		for(int i=0;i<x.length;i++)
		{
			x[i]=s[i+1];
		}
		if(!(s[0].equals("addPage")))
		{
		for(int i=1;i<s.length;i++)
			{
				s[i]=s[i].toLowerCase();
		   		s[i]=s[i].replaceAll("[<>=().{},;+’”?#!-:]","");
				s[i]=s[i].replaceAll("\\[","");
				s[i]=s[i].replaceAll("\\]","");
				if(s[i].equals("applications"))
					s[i]="application";
				if(s[i].equals("stacks"))
					s[i]="stack";
				if(s[i].equals("structures"))
					s[i]="structure";
			}
		}
		//System.out.println(actionMessage);
		if(s[0].equals("addPage"))
		{		
			PageEntry t;	
			try
			{
				t=new PageEntry(s[1].toLowerCase());
			}
			catch(FileNotFoundException e)
			{
				return;
			}
			ipi.addPage(t);
		}

		else if(s[0].equals("queryFindPagesWhichContainWord"))
		{
			try
			{
				String ans="";
				MySet<PageEntry> t=new MySet<PageEntry>();
				t=ipi.getPagesWhichContainWord(s[1].toLowerCase());
				//System.out.println("Size of set is "+t.set_size());

				MySet<SearchResult>t2=new MySet<SearchResult>();
				node<PageEntry> tt1=t.ll.head;
				//System.out.println(" asdf "+tt1.a.pagename+" "+t.ll.getfreq());
				//node<SearchResult> tt2=t2.ll.head;
				while(tt1!=null)
				{
					float rr=ipi.getRelevancesum(x,tt1.a);
					SearchResult tt2=new SearchResult(tt1.a,rr);
					t2.addElement(tt2);	
					tt1=tt1.next;		
					//System.out.println("aa");							
				}

				MySort qq=new MySort();
				ArrayList<SearchResult> tt=qq.sortThisList(t2);

				for(SearchResult ii:tt)
				{					
					ans=ans+", "+ii.pEntry.pagename;				
				}
				//node<PageEntry> tt=t.ll.head;

				/*if(tt==null)
					System.out.println("y");*/
				/*while(tt!=null)
				{
					//System.out.println("435");
					ans=ans+", "+tt.a.pagename;	
					tt=tt.next;
				}*/
				if(ans.length()>2)
				{	
					System.out.println(ans.substring(2));
				}
				else
				{
					//System.out.println(ans);
					System.out.println("No webpage contains word "+x[0]);

				}
			}
			catch(wordnotfoundexp e)
			{
				//System.out.println("sddwbcmncsn");
				System.out.println("No webpage contains word "+x[0]);
			}
			
		}

		else if(s[0].equals("queryFindPositionsOfWordInAPage"))
		{
			try
			{
				String ans="" ;
				
				MyHashTable q=ipi.hashtable;
				if(q==null)
					System.out.println("Webpage " + s[2]+" does not contain word "+x[0]);
				WordEntry tt=q.searchword(s[1].toLowerCase());
				//System.out.println(tt.word);
				MyLinkedList<Position> ll=tt.getAllPositionsForThisWord();
				//System.out.println(ll.getfreq()+" is the no. of elements in linked list of position");
				node<Position> temp=ll.head;
				while((temp!=null)&(temp.a!=null))
				{
					if((temp.a.p.pagename).equals(s[2]))
					{
						ans=ans+", "+String.valueOf(temp.a.getWordIndex());
					}
					temp=temp.next;
				}
				//System.out.println(ans.length());
				if(ans.length()>2)
				{
					System.out.println(ans.substring(2));
				}
				else
				{
					System.out.println("Webpage "+ s[2]+" does not exist");
				}
			}
			catch(wordnotfoundexp e)
			{
				System.out.println("Webpage " + s[2]+" does not contain word "+x[0]);
			}
		}

		else if(s[0].equals("queryFindPagesWhichContainAllWords"))
		{
			try
			{
				String ans="";
				MySet<PageEntry> t=new MySet<PageEntry>();
				MySet<PageEntry> t1=new MySet<PageEntry>();
				for(int i=1;i<s.length;i++)
				{
					t1=ipi.getPagesWhichContainWord(s[i].toLowerCase());
					//System.out.println("Size of set is "+t.set_size());
					t=t.intersection(t1);
				}
				//System.out.println("Size of set is "+t.set_size());
				MySet<SearchResult>t2=new MySet<SearchResult>();
				node<PageEntry> tt1=t.ll.head;
				//node<SearchResult> tt2=t2.ll.head;
				while(tt1!=null)
				{
					float rr=ipi.getRelevancesum(x,tt1.a);
					SearchResult tt2=new SearchResult(tt1.a,rr);
					t2.addElement(tt2);	
					tt1=tt1.next;									
				}

				MySort qq=new MySort();
				ArrayList<SearchResult> tt=qq.sortThisList(t2);

				//node<PageEntry> tt=t.ll.head;
				/*if(tt==null)
					System.out.println("y");*/
				for(SearchResult ii:tt)
				{
					//System.out.println("435");
					ans=ans+", "+ii.pEntry.pagename;				
				}
				if(ans.length()>2)
				{	
					System.out.println(ans.substring(2));
				}
				else
				{
					//System.out.println(ans);
					String jj="No webpage contains all words";
					for(int i=0;i<x.length;i++)
					{
						jj=jj+" "+x[i];
					}
					System.out.println(jj);

				}
			}
			catch(wordnotfoundexp e)
			{
				//System.out.println("sddwbcmncsn");
				String jj="No webpage contains all words";
					for(int i=0;i<x.length;i++)
					{
						jj=jj+" "+x[i];
					}
					System.out.println(jj);
			}
		}

		else if(s[0].equals("queryFindPagesWhichContainAnyOfTheseWords"))
		{
			try
			{
				String ans="";
				MySet<PageEntry> t=new MySet<PageEntry>();
				MySet<PageEntry> t1=new MySet<PageEntry>();
				for(int i=1;i<s.length;i++)
				{
					t1=ipi.getPagesWhichContainWord(s[i].toLowerCase());
					//System.out.println("Size of set t1 is "+t1.set_size());
					t=t.union(t1);
					//System.out.println("Size of set is "+t.set_size());
				}
				MySet<SearchResult>t2=new MySet<SearchResult>();
				node<PageEntry> tt1=t.ll.head;
				//node<SearchResult> tt2=t2.ll.head;
				while(tt1!=null)
				{
					float rr=ipi.getRelevancesum(x,tt1.a);
					SearchResult tt2=new SearchResult(tt1.a,rr);
					t2.addElement(tt2);	
					tt1=tt1.next;									
				}

				MySort qq=new MySort();
				ArrayList<SearchResult> tt=qq.sortThisList(t2);

				//node<PageEntry> tt=t.ll.head;
				/*if(tt==null)
					System.out.println("y");*/
				for(SearchResult ii:tt)
				{
					//System.out.println("435");
					ans=ans+", "+ii.pEntry.pagename;				
				}
				if(ans.length()>2)
				{	
					System.out.println(ans.substring(2));
				}
				else
				{
					//System.out.println(ans);
					String jj="No webpage contains any of the words";
					for(int i=0;i<x.length;i++)
					{
						jj=jj+" "+x[i];
					}
					System.out.println(jj);
				}
			}
			catch(wordnotfoundexp e)
			{
				//System.out.println("sddwbcmncsn");
				String jj="No webpage contains any of the words";
					for(int i=0;i<x.length;i++)
					{
						jj=jj+" "+x[i];
					}
					System.out.println(jj);
			}

		}

		else if(s[0].equals("queryFindPagesWhichContainPhrase"))
		{
			try
			{
				String ans="";
				MySet<PageEntry> t=new MySet<PageEntry>();
				String[] ss=new String[s.length-1];
				for(int i=1;i<s.length;i++)
				{
					ss[i-1]=s[i];
				}
				t=ipi.getPagesWhichContainPhrase(ss);
				//System.out.println("Size of set is "+t.set_size());
				MySet<SearchResult>t2=new MySet<SearchResult>();
				node<PageEntry> tt1=t.ll.head;
				//node<SearchResult> tt2=t2.ll.head;
				while(tt1!=null)
				{
					float rr=ipi.getRelevancesum(x,tt1.a);
					SearchResult tt2=new SearchResult(tt1.a,rr);
					t2.addElement(tt2);	
					tt1=tt1.next;									
				}

				MySort qq=new MySort();
				ArrayList<SearchResult> tt=qq.sortThisList(t2);

				node<PageEntry> tempt=t.ll.head;
				//if(tt==null)
				//	System.out.println("y");
				while(tempt!=null)
				{
					//System.out.println("435");
					ans=ans+", "+tempt.a.pagename;	
					tempt=tempt.next;
				}
				if(ans.length()>2)
				{	
					System.out.println(ans.substring(2));
				}
				else
				{
					//System.out.println(ans);
					String jj="No webpage contains phrase";
					for(int i=0;i<x.length;i++)
					{
						jj=jj+" "+x[i];
					}
					System.out.println(jj);

				}

			}
			catch(wordnotfoundexp e)
			{
				String jj="No webpage contains phrase";
				for(int i=0;i<x.length;i++)
				{
					jj=jj+" "+x[i];
				}
				System.out.println(jj);
			}
		}

		else
		{
			System.out.println("Error- No such query can be handled");
		}
	}
	
}