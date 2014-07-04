import java.util.*;

public class Key3
{
	//fields
	private int key1;
	private int key2;
	private int key3;
	private String value1;
	private String value2;
	private String value3;
	
	//constructor
	public Key3(int key1, int key2, int key3, String value1, String value2, String value3)
	{
		this.key1 = key1;
		this.key2 = key2;
		this.key3 = key3;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
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
	
	public int getKey3()
	{
		return key3;
	}
	
	public String getValue1()
	{
		return value1;
	}
	
	public String getValue2()
	{
		return value2;
	}
	
	public String getValue3()
	{
		return value3;
	}
	
	//Override java.lang method "equals()"
    public boolean equals(Object obj) 
	{
        if(obj != null && obj instanceof Key3) 
		{
            Key3 s = (Key3)obj;
            boolean b1 = (key1 == s.key1);
			boolean b2 = (key2 == s.key2);
			boolean b3 = (key3 == s.key3);
			if(b1 && b2 && b3)
			{
				boolean b4 = (value1.equals(s.value1));
				boolean b5 = (value2.equals(s.value2));
				boolean b6 = (value3.equals(s.value3));
				return (b4 && b5 && b6);
			}
			else 
				return false;
        }
		else
			return false;
    }

    //Override java.lang method "hashCode()"
    public int hashCode() {
        return value1.hashCode()*961 + value2.hashCode()*31 + value3.hashCode() + key1*13 + key2*11 + key3*7;
    }
}
