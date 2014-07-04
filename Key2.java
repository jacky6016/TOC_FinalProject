import java.util.*;

public class Key2
{
	//fields
	private int key1;
	private int key2;
	private String value1;
	private String value2;
	
	//constructor
	public Key2(int key1, int key2, String value1, String value2)
	{
		this.key1 = key1;
		this.key2 = key2;
		this.value1 = value1;
		this.value2 = value2;
	}
	
	//methods
	public int getKey1()
	{
		return key1;
	}
	
	public int getKey2()
	{
		return key2;
	}
	
	public String getValue1()
	{
		return value1;
	}
	
	public String getValue2()
	{
		return value2;
	}
	
	//Override java.lang method "equals()"
    public boolean equals(Object obj) 
	{
        if(obj != null && obj instanceof Key2) 
		{
            Key2 s = (Key2)obj;
            if( (key1 == s.key1) && (key2 == s.key2) )			
				return value1.equals(s.value1) && value2.equals(s.value2);
			else
				return false;
        }
		else
			return false;
    }

    //Override java.lang method "hashCode()"
    public int hashCode() 
	{
        return value1.hashCode()*31 + value2.hashCode() + key1*11 + key2*7;
    }
}
