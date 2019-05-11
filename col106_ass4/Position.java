
public class Position
{
	public PageEntry p;
	public int wordIndex;	//wordIndex

	Position(PageEntry pp, int i)
	{
		wordIndex=i;
		p=pp;
	}

	public PageEntry getPageEntry()
	{
		return p;
	}

	public int getWordIndex()
	{
		return wordIndex;
	}

}