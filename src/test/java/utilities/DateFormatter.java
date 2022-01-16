package utilities;

public class DateFormatter {

	public int[] format_Date(String date)
	{
		int start=0,count=0;
		char[] ch=date.toCharArray();
		int[] d=new int[3];
		for(int i=0;i<ch.length;i++)
		{
			if(ch[i]=='/')
			{
				d[count]=Integer.parseInt(date.substring(start, i));
				start=i+1;
				count++;
			}
		}
		d[count]=Integer.parseInt(date.substring(start));
		return d;
		
	}
}
