
public class MySet<E>
{
	public MyLinkedList<E> ll;

	MySet()
	{
		ll=new MyLinkedList<E>();
	}

	MySet(E a)
	{
		ll=new MyLinkedList(a);
	}

	public Boolean IsEmpty()
	{
		return ll.isempty();
	}

	public int numChildren()
	{
		return ll.numchildren();
	}

	public Boolean IsMember(E a)
	{
		if(ll==null)
			return false;
		return ll.ismember(a);	
	}
	public float set_size()
	{
		return ll.getfreq();
	}
	public void addElement(E a)
	{
		if(!IsMember(a))
			ll.insert(a);
		
	}

	public void removeElement(E a)
	{
		ll.delete(a);
	}

	public MySet<E> union(MySet<E> a)
	{
		MySet<E> b=this;
		node<E> t=a.ll.head;
		if(t==null)
		{
			return b;
		}
		if((t!=null)&&(!b.IsMember(t.a)))
		{
			b.addElement(t.a);
		}
		while(t.next!=null)
		{
			t=t.next;
			if(!b.IsMember(t.a))
			{
				b.addElement(t.a);
			}
		}
		return b;
	}

	public MySet<E> intersection(MySet<E> a)
	{
		MySet<E> b = this;
		node<E> t=a.ll.head;
		if(t==null)
		{
			return b;
		}
		if((!t.equals(null))&&(!b.IsMember(t.a)))
		{
			b.removeElement(t.a);
		}
		while(t.next!=null)
		{
			t=t.next;
			if(!b.IsMember(t.a))
			{
				b.removeElement(t.a);
			}
		}
		return b;
	}
}