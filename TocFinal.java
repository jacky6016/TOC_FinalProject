/* Author: 林展翔  Dept: EE(junior) StudentID: E24019067 
   Filename: TocFinal.java  
   Associated files: Key2.java, Key3.java, Key4.java
   Output file: output.txt
   Program function: calculate & print top-k L-combinations
   Input command format: TocFinal URL top_k L_combinations   */

import java.io.*;
import java.net.*;	// for downloading file
import java.util.*;
import org.json.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TocFinal
{
	public static void main(String[] args)
    {
		try
		{
			if( !checkArgument(args) )
				System.exit(0); 
			
			String url = args[0];
			int top_k = Integer.parseInt(args[1]);
			int L_combination = Integer.parseInt(args[2]);
			
			String filename = "output.txt";
			File file = new File(filename);
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(filewriter);
			
			TocFinal Final = new TocFinal();
			Final.parseData(url);
			JSONArray jsonRealPrice = new JSONArray(new JSONTokener(new FileReader(new File("Data.txt"))));
			
			int num_keys = jsonRealPrice.getJSONObject(0).length();
			String [] keys = new String[num_keys];
			int [] types = new int[num_keys];
			keys = getKeys(num_keys,jsonRealPrice.getJSONObject(0),types);
			int numCom = combination(num_keys,L_combination);			
			
			if(L_combination == 2)
			{
				ArrayList<Map<Key2, Integer>> listOfMaps = new ArrayList<Map<Key2, Integer>>();
				for(int i=0; i<numCom; i++)
				{
					listOfMaps.add(new HashMap<Key2, Integer>() ); 			
				}
				
				for(int index=0; index<jsonRealPrice.length(); index++)
				{
					//JSONObject methods: isNull(),keys(),length(),has()  
					JSONObject object = jsonRealPrice.getJSONObject(index);
					String [] values = new String[num_keys];
					
					for(int i=0; i<num_keys; i++)
					{
						if(object.isNull(keys[i]))
							values[i] = "null";
						else
						{
							if(types[i] == 0)
								values[i] = String.valueOf(object.getInt(keys[i]));
							else if(types[i] == 1)
								values[i] = String.valueOf(object.getDouble(keys[i]));
							else if(types[i] == 2)
							{
								if(object.get("總樓層數").equals("Z"))
									values[i] = "Z";
								else
									values[i] = String.valueOf(object.getInt(keys[i]));
							}
							else						
								values[i] = object.getString(keys[i]);
						}
					}	
					int count = 0;
					for(int i= 0; i<num_keys; i++)
					{
						for(int j=i+1; j<num_keys; j++)
						{
							if( values[i].isEmpty() || values[j].isEmpty() )
							{
								count++;
								continue;
							}
							else
							{
								Key2 tempKey = new Key2(i,j,values[i],values[j]);
								if(  listOfMaps.get(count).containsKey(tempKey) )
								{
									int tempValue = listOfMaps.get(count).get(tempKey);
									listOfMaps.get(count).put(tempKey,tempValue+1);
								}
								else
									listOfMaps.get(count).put(tempKey,1);
								count++;
							}
						}
					}				
				}

				Map<Key2, Integer> finalMap = new HashMap<Key2, Integer>();
				for(int i=0; i<numCom; i++)
				{
					finalMap.putAll(listOfMaps.get(i));
				}
				
				List<Map.Entry<Key2, Integer>> comboList = new ArrayList<Map.Entry<Key2, Integer>>(finalMap.entrySet());
				
				Collections.sort(comboList, new Comparator<Map.Entry<Key2, Integer>>()
				{
					public int compare(Map.Entry<Key2, Integer> entry1, Map.Entry<Key2, Integer> entry2)
				{
						return (entry2.getValue() - entry1.getValue());
					}
				});
				
				int temp = 0;
				int count = 1;
				for (Map.Entry<Key2, Integer> entry:comboList) 
				{
					Key2 tempKey = entry.getKey();
					if(count == top_k)
						temp = finalMap.get(tempKey);						
					if(count > top_k)
					{
						if( temp != finalMap.get(tempKey) )
							break;
					}	
						
					int k1 = tempKey.getKey1();
					int k2 = tempKey.getKey2();
					String v1 = tempKey.getValue1();
					String v2 = tempKey.getValue2();
					System.out.println(keys[k1] + ":" + v1 + ", " + keys[k2] + ":" + v2 + "\t" + finalMap.get(tempKey));
					writer.write(keys[k1] + ":" + v1 + "," + keys[k2] + ":" + v2 + ";" + finalMap.get(tempKey));
					writer.newLine();
					
					count++;
				}
			}				
			
			else if(L_combination == 3)
			{
				ArrayList<Map<Key3, Integer>> listOfMaps = new ArrayList<Map<Key3, Integer>>();
				for(int i=0; i<numCom; i++)
				{
					listOfMaps.add(new HashMap<Key3, Integer>() ); 			
				}
				
				for(int index=0; index<jsonRealPrice.length(); index++)
				{
					JSONObject object = jsonRealPrice.getJSONObject(index);
					String [] values = new String[num_keys];
					
					for(int i=0; i<num_keys; i++)
					{
						if(object.isNull(keys[i]))
							values[i] = "null";
						else
						{
							if(types[i] == 0)
								values[i] = String.valueOf(object.getInt(keys[i]));
							else if(types[i] == 1)
								values[i] = String.valueOf(object.getDouble(keys[i]));
							else if(types[i] == 2)
							{
								if(object.get("總樓層數").equals("Z"))
									values[i] = "Z";
								else
									values[i] = String.valueOf(object.getInt(keys[i]));
							}
							else						
								values[i] = object.getString(keys[i]);
						}
					}	
					int count = 0;
					for(int i= 0; i<num_keys; i++)
					{
						for(int j=i+1; j<num_keys; j++)
						{
							for(int k=j+1; k<num_keys; k++)
							{
								if( values[i].isEmpty() || values[j].isEmpty() || values[k].isEmpty() )
								{
									count++;
									continue;
								}
								else
								{
									Key3 tempKey = new Key3(i,j,k,values[i],values[j],values[k]);
									if(  listOfMaps.get(count).containsKey(tempKey) )
									{
										int tempValue = listOfMaps.get(count).get(tempKey);
										listOfMaps.get(count).put(tempKey,tempValue+1);
									}
									else
										listOfMaps.get(count).put(tempKey,1);
									count++;
								}
							}	
						}
					}				
				}

				Map<Key3, Integer> finalMap = new HashMap<Key3, Integer>();
				for(int i=0; i<numCom; i++)
				{
					finalMap.putAll(listOfMaps.get(i));
				}
				
				List<Map.Entry<Key3, Integer>> comboList = new ArrayList<Map.Entry<Key3, Integer>>(finalMap.entrySet());
				
				Collections.sort(comboList, new Comparator<Map.Entry<Key3, Integer>>()
				{
					public int compare(Map.Entry<Key3, Integer> entry1, Map.Entry<Key3, Integer> entry2)
				{
						return (entry2.getValue() - entry1.getValue());
					}
				});
				
				int temp = 0;
				int count = 1;
				for (Map.Entry<Key3, Integer> entry:comboList) 
				{
					Key3 tempKey = entry.getKey();
					if(count == top_k)
						temp = finalMap.get(tempKey);						
					if(count > top_k)
					{
						if( temp != finalMap.get(tempKey) )
							break;
					}	
					int k1 = tempKey.getKey1();
					int k2 = tempKey.getKey2();
					int k3 = tempKey.getKey3();
					String v1 = tempKey.getValue1();
					String v2 = tempKey.getValue2();
					String v3 = tempKey.getValue3();
					System.out.println(keys[k1] + ":" + v1 + ", " + keys[k2] + ":" + v2 + ", " + keys[k3] + ":" + v3 + "\t" + finalMap.get(tempKey));
					writer.write(keys[k1] + ":" + v1 + "," + keys[k2] + ":" + v2 + "," + keys[k3] + ":" + v3 + ";" + finalMap.get(tempKey));
					writer.newLine();
					
					count++;
				}
			}
			else if(L_combination == 4)
			{
				ArrayList<Map<Key4, Integer>> listOfMaps = new ArrayList<Map<Key4, Integer>>();
				for(int i=0; i<numCom; i++)
				{
					listOfMaps.add(new HashMap<Key4, Integer>() ); 			
				}
				
				for(int index=0; index<jsonRealPrice.length(); index++)
				{
					JSONObject object = jsonRealPrice.getJSONObject(index);
					String [] values = new String[num_keys];
					
					for(int i=0; i<num_keys; i++)
					{
						if(object.isNull(keys[i]))
							values[i] = "null";
						else
						{
							if(types[i] == 0)
								values[i] = String.valueOf(object.getInt(keys[i]));
							else if(types[i] == 1)
								values[i] = String.valueOf(object.getDouble(keys[i]));
							else if(types[i] == 2)
							{
								if(object.get("總樓層數").equals("Z"))
									values[i] = "Z";
								else
									values[i] = String.valueOf(object.getInt(keys[i]));
							}
							else						
								values[i] = object.getString(keys[i]);
						}
					}	
					int count = 0;
					for(int i= 0; i<num_keys; i++)
					{
						for(int j=i+1; j<num_keys; j++)
						{
							for(int k=j+1; k<num_keys; k++)
							{
								for(int l=k+1; l<num_keys; l++)
								{
									if( values[i].isEmpty() || values[j].isEmpty() || values[k].isEmpty() || values[l].isEmpty() )
									{
										count++;
										continue;
									}
									else
									{
										Key4 tempKey = new Key4(i,j,k,l,values[i],values[j],values[k],values[l]);
										if(  listOfMaps.get(count).containsKey(tempKey) )
										{
											int tempValue = listOfMaps.get(count).get(tempKey);
											listOfMaps.get(count).put(tempKey,tempValue+1);
										}
										else
											listOfMaps.get(count).put(tempKey,1);
										count++;
									}	
								}
							}	
						}
					}				
				}

				Map<Key4, Integer> finalMap = new HashMap<Key4, Integer>();
				for(int i=0; i<numCom; i++)
				{
					finalMap.putAll(listOfMaps.get(i));
				}
				
				List<Map.Entry<Key4, Integer>> comboList = new ArrayList<Map.Entry<Key4, Integer>>(finalMap.entrySet());
				
				Collections.sort(comboList, new Comparator<Map.Entry<Key4, Integer>>()
				{
					public int compare(Map.Entry<Key4, Integer> entry1, Map.Entry<Key4, Integer> entry2)
				{
						return (entry2.getValue() - entry1.getValue());
					}
				});
				
				int temp = 0;
				int count = 1;
				for (Map.Entry<Key4, Integer> entry:comboList) 
				{
					Key4 tempKey = entry.getKey();
					if(count == top_k)
						temp = finalMap.get(tempKey);						
					if(count > top_k)
					{
						if( temp != finalMap.get(tempKey) )
							break;
					}
						
					int k1 = tempKey.getKey1();
					int k2 = tempKey.getKey2();
					int k3 = tempKey.getKey3();
					int k4 = tempKey.getKey4();
					String v1 = tempKey.getValue1();
					String v2 = tempKey.getValue2();
					String v3 = tempKey.getValue3();
					String v4 = tempKey.getValue4();
					System.out.println(keys[k1] + ":" + v1 + ", " + keys[k2] + ":" + v2 + ", " + keys[k3] + ":" + v3 + ", " + keys[k4] + ":" + v4 + "\t" + finalMap.get(tempKey));
					writer.write(keys[k1] + ":" + v1 + "," + keys[k2] + ":" + v2 + "," + keys[k3] + ":" + v3 + "," + keys[k4] + ":" + v4 + ";" + finalMap.get(tempKey));
					writer.newLine();
					
					count++;
				}				
			}
			writer.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Data File Not Found");
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(JSONException ex)
		{
			System.out.println("JSON Exception");
		}
	
	}
	
	public static boolean checkArgument(String [] args)
	{
		String re = "[0-9]+";
		
		Pattern pattern = Pattern.compile(re);
		
		Matcher matcher1 = pattern.matcher(args[1]);
		Matcher matcher2 = pattern.matcher(args[2]);
		
		boolean b1 = matcher1.find();
		boolean b2 = matcher2.find();
		boolean valid = true;
		
		if(args.length > 3)
		{
			System.out.println("Too many arguments, Arguments: URL(1st) top_k(2nd) L_combination(3rd)");
			return false;
		}
		
		else if(args.length < 3)
		{
			System.out.println("Too few arguments, Arguments: URL(1st) top_k(2nd) L_combination(3rd)");
			return false;
		}
		else
		{
			if(!b1)
			{	
				System.out.println("Argument 2(top_k) for TocFinal.java is not a valid number, it shoutld be a positive integer");
				valid = false;
			}
			if(!b2)
			{
				System.out.println("Argument 3(L_combination) for TocFinal.java is not a valid number, it shoutld be a positive integer(2~4)");	
				valid = false;
			}
			if(b1&b2)
			{
				int top_k = Integer.parseInt(args[1]);
				int L_combination = Integer.parseInt(args[2]);
			
				if(top_k < 1)
				{
					System.out.println("Argument 2(top_k) for TocFinal.java is not a valid number, it shoutld be a positive integer");
					valid = false;
				}
				if(L_combination < 2 || L_combination > 4)
				{
					System.out.println("Argument 3(L_combination) for TocFinal.java is not a valid number, 2 <= L-combination <= 4");
					valid = false;
				}
			}
		}		
		return valid;
	}	
	
	public void parseData(String url) 
	{
		try
		{
			URL pageUrl = new URL(url);
			// 讀入網頁(位元串流)
			BufferedReader br = new BufferedReader(new InputStreamReader(pageUrl.openStream(), "UTF-8"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("Data.txt", false));
			String oneLine = null ;
		
			while ((oneLine = br.readLine()) != null) 
			{
				bw.write(oneLine);
				bw.flush();
				//System.out.println(oneLine);
			}
			br.close();
			bw.close();
		//System.out.println("parse Done");
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public static int combination(int n, int k)
	{
		return permutation(n) / (permutation(k) * permutation(n - k));
	}

	public static int permutation(int i)
	{
		if (i == 1)
		{
			return 1;
		}
		return i * permutation(i - 1);
	}
	
	public static String[] getKeys(int num_keys,JSONObject object, int [] types)
	{
		String [] allKeys = {"鄉鎮市區","交易標的","土地區段位置或建物區門牌","土地移轉總面積平方公尺","都市土地使用分區",
		                     "非都市土地使用分區","非都市土地使用編定","交易年月","交易筆棟數","移轉層次",
						          	 "總樓層數","建物型態","主要用途","主要建材","建築完成年月",
					           		 "建物移轉總面積平方公尺","建物現況格局-房","建物現況格局-廳","建物現況格局-衛","建物現況格局-隔間",
							           "有無管理組織","總價元","單價每平方公尺","車位類別","車位移轉總面積平方公尺","車位總價元"};
		String [] keys = new String [num_keys];
		int index = 0;
		for(int i=0; i<26; i++)
		{
			if(object.has(allKeys[i]))
			{
				keys[index] = allKeys[i];		
				if(i==7||i==14||i==16||i==17||i==18||i==21||i==22||i==24||i==25)
					types[index] = 0; 	//int
				else if(i==3||i==15)
					types[index] = 1;	//double
				else if(i==10)
					types[index] = 2;	//總樓層數
				else
					types[index] = 3;	//String
				index++;
			}	
		}
		return keys;
	}
	
}

	
