
public class WordEntryList extends MyLinkedList<WordEntry>
{
	WordEntryList(WordEntry w)
	{
		head=new node<WordEntry>(w);	
	}
	
	WordEntryList()
	{
		head=new node<WordEntry>();
		
	}
}