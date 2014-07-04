import java.util.*;

public class Key4
{
	//fields
	private int key1;
	private int key2;
	private int key3;
	private int key4;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	
	//constructor
	public Key4(int key1, int key2, int key3, int key4, String value1, String value2, String value3, String value4)
	{
		this.key1 = key1;
		this.key2 = key2;
		this.key3 = key3;
		this.key4 = key4;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
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
	
	public int getKey4()
	{
		return key4;
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
	
	public String getValue4()
	{
		return value4;
	}
	
	//Override java.lang method "equals()"
    public boolean equals(Object obj) 
	{
        if(obj != null && obj instanceof Key4) 
		{
            Key4 s = (Key4)obj;
            boolean b1 = (key1 == s.key1);
			boolean b2 = (key2 == s.key2);
			boolean b3 = (key3 == s.key3);
			boolean b4 = (key4 == s.key4);
			if(b1 && b2 && b3 && b4)
			{
				boolean b5 = (value1.equals(s.value1));
				boolean b6 = (value2.equals(s.value2));
				boolean b7 = (value3.equals(s.value3));
				boolean b8 = (value4.equals(s.value4));
				return (b5 && b6 && b7 && b8);
			}	
			else 
				return false;
        }
		else
			return false;
    }

    //Override java.lang method "hashCode()"
    public int hashCode() {
        return value1.hashCode()*29791 + value2.hashCode()*961 + value3.hashCode()*31 + value4.hashCode() + key1*17 + key2*13 + key3*11 + key4*7;
    }
}
