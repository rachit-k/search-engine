
public class MyLinkedList<E>
{
	
	node<E> head;

	MyLinkedList()
	{
		head=null;
	}

	MyLinkedList(E a)
	{
		head=new node(a);
	}

	public Boolean isempty()
	{
		if (head.equals(null))
			return true;
		else
			return false;
	} 

	public Boolean ismember(E x)
	{	
		node<E> temp;
		temp=head;

		while(temp!=null)
		{
			if(temp.a.equals(x))
			{
				return true;
			}
			temp=temp.next;
			
		}		
		return false;		
	}

	

	public void insert(E x)
	{
		node<E> temp=head;		
		if(temp==null)
		{
			head=new node(x);
		}	
		else
		{
			while(temp.next!=null)
			{
				temp=temp.next;		
			}
		node<E> t= new node<E>();
		t.a=x;	
		temp.next=t;
		t.next=null;
		}
	}

	public void insertll(MyLinkedList<E> x)
	{
		node<E> temp=head;		
		if(temp==null)
		{
			head=new node(x.head);
		}	
		else if(x.head==null)
		{
			;
		}
		else
		{
			while(temp.next!=null)
			{
				temp=temp.next;		
			}
		//node<E> t= new node<E>();
		temp.next=x.head;	
		}
	}

	public void delete(E x)	//exception
	{
		node<E> temp=head;
		node<E> t=head;
		if(temp==null)
		{
			return;
		}
		if(temp.a==x)
		{
			head=head.next;
		}
		else
		{
			while(temp.next!=null)
			{
				temp=temp.next;
				if(temp.a==x)
				{
					t.next=temp.next;
					break;
				}
				t=t.next;			
			}
		}			
	}

	public float getfreq()
	{
		float flag=0.0f;
		node<E> temp=head;
		if(head==null)
		{
			return flag;
		}
		else
		{
			while(!(temp==null))
			{
				flag=flag + 1.0f;
				temp=temp.next;
				
			}
		}
		return flag;
	}

	public int numchildren()
	{
		node<E> temp= head;
		int flag=0;
		if(temp==null)
		{
			return 0;
		}
		if(!temp.equals(null))
		{
			flag++;
		}
		while(!(temp.next==null))
		{
			temp=temp.next;
			flag++;
		}
		return flag;
	}

}