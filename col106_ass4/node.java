public class node<E>
	{
		E a;
		public node<E> next;
		node()
		{
			next=null;
		}
		node(E x)
		{
			a=x;
			next=null;
		}

		public node<E> retnext(){
			return next;
		}
		
	}
