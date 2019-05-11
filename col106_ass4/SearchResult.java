
public class SearchResult implements Comparable<SearchResult>
{
	PageEntry pEntry;
	float rev;

	public SearchResult(PageEntry p, float r)
	{
		pEntry=p;
		rev=r;
	}

	public PageEntry getPageEntry()
	{
		return pEntry;
	}

	public float getRelevance()
	{
		return rev;
	}
	@Override
	public int compareTo(SearchResult otherObject)
	{
		if(rev>otherObject.rev)
		{
			return 1;
		}
		else if(rev>otherObject.rev)
		{
			return -1;
		}
		else
			return 0;
	}
	


}