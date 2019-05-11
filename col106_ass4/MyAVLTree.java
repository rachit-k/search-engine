import java.lang.Math;

public class MyAVLTree
{
	AVLnode root;
	int size;
	MyAVLTree()
	{
		root=new AVLnode();
		size=0;
	}

	MyAVLTree(Position pp, int ii)
	{
		root=new AVLnode(pp,ii,0);
		size=1;
	}

	MyAVLTree(AVLnode anode)
	{
		if(anode==null)
			root=new AVLnode();
		else
			root=anode;
		size=-1;// to check if we ues it
	}

	/*public MySet<Position> findd(int index)
	{
		MySet<Position> s=new MySet<Position>();
		if(root==null)
		{
			return s;
		}
		if((root.i<index)&&(root.left!=null))
		{
			s=root.left.findd(index);
		}
		else if((root.i>index)&&(root.right!=null))
		{
			s=root.right.findd(index);
		}
		else if(root.i==index)
		{
			s.addElement(root.p);
			if((root.left==null)|(root.left.i<index))//
				return s;
			else
				s=root.left.findd(index);
		}
		else
		{
			return s;
		}
	}*/

	/*private AVLnode findd(Position pp, int index)
	{
		AVLnode temp=new AVLnode(pp,index,0);
		if(root==null)
		{
			root=temp;
			//root.ht=0;
			return;
		}
		else if(index<root.i)
		{
			if(root.left!=null)
			{
				root.left.insertt(pp,index);
				return;
			}
			else
			{
				root.left=temp;
				root.left.ht=0;
				if(root.left.pe==null)
				{
					root.left.pe=new MySet<PageEntry>(pp.p);
				}
				else
				{
					root.left.pe.addElement(pp.p);
				}
			}
		}
		else if(index>root.i)
		{
			if(root.right!=null)
			{
				root.right.insertt(pp,index);
				return;
			}
			else
			{
				root.right=temp;
				root.right.ht=0;
				if(root.right.pe==null)
				{
					root.right.pe=new MySet<PageEntry>(pp.p);
				}
				else
				{
					root.right.pe.addElement(pp.p);
				}
			}
		}
		else
		{
			if(root.pe==null)
				{
					root.pe=new MySet<PageEntry>(pp.p);
				}
				else
				{
					root.pe.addElement(pp.p);
				}
		}
		
	}*/

	public AVLnode findd(int index)throws wordnotfoundexp
	{
		try
		{
			return finddd(index,root);
		}
		catch(wordnotfoundexp e)
		{
			throw new wordnotfoundexp();
		}
	}

	public AVLnode finddd(int index, AVLnode r) throws wordnotfoundexp
	{
		if(r==null)
		{
			throw new wordnotfoundexp();
		}
		else if(index<r.i)
		{
			return finddd(index,r.left);
		}
		else if(index>r.i)
		{
			return finddd(index,r.right);
		}
		else
		{
			if(r.pe==null)
				{
					throw new wordnotfoundexp();
				}
				else
				{
					return r;
				}

		}
	}

	public void insertt(Position pp, int index)
	{
		//System.out.println("incoming i "+index);

		if(size==0)
		{
			root=new AVLnode(pp,index,0);
			size++;
			//System.out.println("new node i,h "+root.i+" "+root.ht);
			return;
		}
		root=inserttt(pp,index,root);
		size++;		
	}
	//int flag=0;
	public AVLnode inserttt(Position pp, int index, AVLnode t)
	{
		//AVLnode temp=new AVLnode(pp,index,0);
		if(t==null)//t.pe.set_size()==0
		{
			t=new AVLnode(pp,index,0);
			//flag++;
			//System.out.println("new node i,h "+t.i+" "+t.ht);
			//root.ht=0;
			return t;

		}
		else if(index<t.i)
		{
			t.left=inserttt(pp,index,t.left);
			
			//System.out.println("ww");
			/*if(t.left!=null)
			{
				root.left.getTree().insertt(pp,index);
				return;
			}
			else
			{
				root.left=temp;
				root.left.ht=0;
				if(root.left.pe==null)
				{
					root.left.pe=new MySet<PageEntry>(pp.p);
				}
				else
				{
					root.left.pe.addElement(pp.p);
				}
			}*/
		}
		else if(index>t.i)
		{
			t.right=inserttt(pp,index,t.right);
			//flag++;
			//System.out.println("ee");
			/*if(root.right!=null)
			{
				root.right.getTree().insertt(pp,index);
				return;
			}
			else
			{
				root.right=temp;
				root.right.ht=0;
				if(root.right.pe==null)
				{
					root.right.pe=new MySet<PageEntry>(pp.p);
				}
				else
				{
					root.right.pe.addElement(pp.p);
				}
			}*/
		}
		else
		{
			//System.out.println("rr");
			if(t.pe==null)
				{
					t.pe=new MySet<PageEntry>(pp.p);
				}
				else
				{
					t.pe.addElement(pp.p);
				}
		}
		
		//root.ht=1+Math.max(root.left.ht,root.right.ht);
		t=updateht(t);

		int flag;
		if((t.left!=null)&&(t.right!=null))
			flag=t.left.ht-t.right.ht;
		else if((t.left==null)&&(t.right==null))
			flag=0;
		else if((t.left!=null)&&(t.right==null))
			flag=t.left.ht+1;
		else
			flag=-1-t.right.ht;
		//int flag=root.left.ht-root.right.ht;
														
		if((flag>1)&&(index<t.left.i))
		{
			AVLnode temp1=t.left;
			t.left=temp1.right;
			temp1.right=t;
			t=temp1;
			
			
			t.right=updateht(t.right);
			t=updateht(t);
			//root.right.getTree().updateht();
			//Updateht();
		}
		else if((flag<-1)&&(index>t.right.i))
		{
			AVLnode temp1=t.right;
			t.right=temp1.left;
			temp1.left=t;
			t=temp1;

			
			t.left=updateht(t.left);
			t=updateht(t);

			//Updateht();
		}
		else if((flag>1)&&(index>t.left.i))
		{
			//AVLnode temp1=root.left;
			AVLnode temp1=t.left.right;
			if(t.left.right==null)
				temp1=new AVLnode();
			t.left.right=temp1.left;
			temp1.left=t.left;
			t.left=temp1.right;
			temp1.right=t;
			t=temp1;

			
			t.left.right=updateht(t.left.right);
			t.left=updateht(t.left);
			t=updateht(t);
		}
		else if((flag<-1)&&(index<t.right.i))
		{
			AVLnode temp1=t.right.left;
			if(t.right.left==null)
				temp1=new AVLnode();
			t.right.left=temp1.right;
			temp1.right=t.right;
			t.right=temp1.left;
			temp1.left=t;
			t=temp1;
			t.right.left=updateht(t.right.left);
			t.right=updateht(t.right);
			t=updateht(t);

		}
		//System.out.println("final i,ht "+t.i+" "+t.ht);
		return t;
	}

	private AVLnode updateht(AVLnode tt)
	{
		//System.out.println("tt");
		if(tt==null)
		{
			return null;
		}
		else if((tt.left!=null)&(tt.right!=null))
			tt.ht=1+Math.max(tt.left.ht,tt.right.ht);
		else if((tt.left==null)&(tt.right==null))
			tt.ht=0;
		else if((tt.left!=null)&(tt.right==null))
			tt.ht=1+tt.left.ht;
		else
			tt.ht=1+tt.right.ht;
		return tt;
	}


	public MySet<PageEntry> getpageEntries(int ii)
	{
		AVLnode temp=root;
		MySet<PageEntry> s=new MySet<PageEntry>();
		postorder1(root,ii,s);//check
		return s;
	}

	 private void postorder1(AVLnode r,int ii, MySet<PageEntry> ss)
     {
         if (r != null)
         {
             postorder1(r.left,ii,ss);             
             postorder1(r.right,ii,ss);
             if(r.i==ii)
             {
             	ss.union(r.pe);
             	//return;
             }
         }
     }  


	int ff;
    public int postorder2(AVLnode r,PageEntry p)//not sure
    {
    	if(r==null)
    	{
    		ff=0;
    	}
        else
        {
            ff=ff+postorder2(r.left,p);             
            ff=ff+postorder2(r.right,p);
            if(r.pe.IsMember(p))
            {
            	ff++;
            }
        }
        return ff;
    }     

    public MyLinkedList<AVLnode> getAVLlist()
    {
     	MyLinkedList<AVLnode> lla=new MyLinkedList();
     	preorder(root,lla);
     	return lla;
    } 

    private void preorder(AVLnode r,MyLinkedList<AVLnode> ll)
     {
         if (r != null)
         {
         	ll.insert(r);
             preorder(r.left,ll);
             preorder(r.right,ll);
         }
     }

    public MyLinkedList<Position> getPositionlist()
    {
     	MyLinkedList<Position> ll=new MyLinkedList<Position>();
     	MyLinkedList<AVLnode> lla=getAVLlist();
     	node<AVLnode> temp=lla.head;
     	while(temp!=null)
     	{
     		ll.insert(temp.a.p);
     		temp=temp.next;
     	} 
     	return ll;
    }

}


