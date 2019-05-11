
public class WordEntry
{
	public String word;
	public MyAVLTree positions;

	public WordEntry()
	{
		positions = new MyAVLTree();
		word="";
	}

	public WordEntry(String w)
	{
		positions = new MyAVLTree();
		word=w;
	}

	public void addPosition(Position p,int k)
	{
		positions.insertt(p,k);
	}

	public void addPositions(MyAVLTree ps)
	{
		MyLinkedList<Position> qq=ps.getPositionlist();
		node<Position> temp=qq.head;
		while(temp!=null)
		{	
			positions.insertt(qq.head.a,qq.head.a.wordIndex);
			temp=temp.next;
		}
	}

	public MyLinkedList<Position> getAllPositionsForThisWord()
	{
		MyLinkedList<Position> qq=positions.getPositionlist();
		return qq;
	}

	public float getTermFrequency(String w)
	{
		if(w==word)
		{
			MyLinkedList<Position> qq=positions.getPositionlist();
			return qq.getfreq();
		}
		else
			return 0;
	}
}