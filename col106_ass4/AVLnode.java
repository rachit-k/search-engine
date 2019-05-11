
public class AVLnode
{
	Position p;
	int i;
	int ht;
	public AVLnode left;
	public AVLnode right;
	MySet<PageEntry> pe;
	AVLnode()
	{
		left=null;
		right=null;
		pe=new MySet<PageEntry>();
	}

	AVLnode(Position pp, int ii, int h)
	{
		p=pp;
		i=ii;
		ht=h;//
		left=null;
		right=null;
		pe=new MySet<PageEntry>(pp.p);
	}

	public MyAVLTree getTree()
	{
		MyAVLTree qq=new MyAVLTree(this);
		return qq;
	}
}
