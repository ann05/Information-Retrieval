import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.LeafReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.PostingsEnum;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;



public class IndexReade{
	LeafReader lr;
	
	static Map<String,LinkedList<Integer>> PostingsMap = new HashMap<String,LinkedList<Integer>>();
	static int count10=0;
	static int count20=0;
	static int count30=0;
	static int count40=0;
	
	public static void main(String args[]) throws IOException{
		
		IndexReade lir =new IndexReade();
		BufferedReader filereader=null;
		String currentline;
		filereader=new BufferedReader(new FileReader(args[2]));
		File output=new File(args[1]);
		FileOutputStream fs1=new FileOutputStream(output);
		PrintStream print=new PrintStream(fs1);
		System.setOut(print);
		
		//String path="index";
		String path=args[0];
		FileSystem fs=FileSystems.getDefault();
		Path path1=(fs.getPath(path));
		IndexReader reader=DirectoryReader.open(FSDirectory.open(path1));
		//System.out.println(reader.numDocs());
		//System.out.println(MultiFields.getIndexedFields(reader));
		int count1 = 0;
		
		
		getPostingList(reader);
		/*for(LeafReaderContext atomicreadercontext : reader.leaves()) {
			lir.lr = atomicreadercontext.reader();
			//lir.lr
		Fields F =MultiFields.getFields(lir.lr);
		
		for(String f : F)
		{
			if(f.equals("id"))
				continue;
			if(f.equals("_version_"))
				continue;
			Terms term=F.terms(f);
			TermsEnum termenum=term.iterator();
			int count=0;
			while(termenum.next()!=null)
			{
				BytesRef byteref=termenum.term();
				String Bytestr = byteref.utf8ToString();
				//System.out.println(Bytestr);
				double freq=termenum.docFreq();
				//System.out.println("Doc frequency"+freq);
				count++;
				Term term1=new Term(f,byteref);
				//lir.getPostingList(term1,lir.lr);//lir.lr
			}
			count1=count1+count;
			//System.out.println(count);			
		}	
	}
		System.out.println("Final Count:  "+count1);*/
		
		
		while((currentline = filereader.readLine()) !=null){
			LinkedList<String> QueryTerms= new LinkedList<String>();
			ArrayList<Integer> taatAnd=null;//taatOR=null,Taator=null;
			ArrayList<Integer> taatOR=new ArrayList<Integer>();
			ArrayList<Integer> daatAnd=null;
			ArrayList<Integer> daatOR=null;
			String ipterms[]= currentline.split(" ");
			for(String st : ipterms){
				LinkedList l;
				l=PostingsMap.get(st);
				System.out.println("Get Postings");
				System.out.println(st);
				System.out.println("Postings List ");
				for(int k=0;k<l.size();k++){
					System.out.print(l.get(k)+" ");}
				System.out.println();
				//System.out.println();			
			}
			//TaaatAND
			int flag=0;
			//int count=0;
			for(String st:ipterms){
				taatAnd=TermAtATimeAnd(taatAnd,st,flag);
				flag=1;
				QueryTerms.add(st);
			}
				System.out.println("TaatAnd");
				for(String i:QueryTerms){
				System.out.print(i+" ");}
				System.out.println();
				Collections.sort(taatAnd);
				System.out.print("Results: ");
				if(taatAnd.size()==0)
			    	System.out.print("Empty");
				else
					for(int j:taatAnd){
					System.out.print(j+" ");}
				System.out.println();
				System.out.println("Number of Results: "+ taatAnd.size());
				System.out.println("Number of comparisons: "+count10);
				
				
			//TaatOR
		    flag=0;
		    //count=0;
		    for(String st:ipterms){
		    	taatOR=TermAtATimeOr(taatOR,st,flag);
		    	flag=1;
		    	//QueryTerms.add(st);
		    }
		    System.out.println("TaatOr");
		    for(String i:QueryTerms){
				System.out.print(i+" ");}
			System.out.println();
			Collections.sort(taatOR);
		    System.out.print("Results: ");
		    if(taatOR.size()==0)
		    	System.out.print("Empty");
		    else
		    	for(int x:taatOR){
				System.out.print(x+" ");}
		    System.out.println();
		    System.out.println("Number of Results: "+ taatOR.size());
		    System.out.println("Number of comparisons: "+count20);
		    
		    
		    //DaatAnd
		    daatAnd=DocAtATimeAnd(ipterms);
		    //System.out.println(daatAnd);
		    System.out.println("DaatAnd ");
		    for(String i:QueryTerms){
				System.out.print(i+" ");}
			System.out.println();
			Collections.sort(daatAnd);
		    System.out.print("Results: ");
		    if(daatAnd.size()==0)
		    	System.out.print("Empty");
		    else
		    	for(int l6:daatAnd){
				System.out.print(l6+" ");}
		    System.out.println();
		    System.out.println("Number of Results: "+ daatAnd.size());
		    System.out.println("Number of comparisons: "+count30);
		    
		    
		    //DaatOr
		    daatOR=DocAtATimeOr(ipterms);
		    System.out.println("DaatOR ");
		    for(String i:QueryTerms){
		    	System.out.print(i+" ");
		    }
		    System.out.println();
		    System.out.print("Results: ");
		    if(daatOR.size()==0)
		    	System.out.print("Empty");
		    else
		    for( int a : daatOR){
				System.out.print(a+" ");}
		    System.out.println();
		    System.out.println("Number of Results: "+ daatOR.size());
		    System.out.println("Number of comparisions: "+count40);
		    
		    
		    
				}	}
	
	
	public static ArrayList<Integer> TermAtATimeAnd(ArrayList<Integer> taatAnd,String st,int flag){
		ArrayList<Integer> temp=new ArrayList<Integer>();
		if(PostingsMap.get(st)!=null)
		{
			LinkedList<Integer> posts;
			posts=PostingsMap.get(st);
			if(flag==0)
			{
				for(Integer freq: posts)
				{
					//count10++;
					
					temp.add(freq);
					//System.out.println("Temp1 And: "+temp);
				}
			}
			else
			{
				for(int a:taatAnd)
				{
					count10++;
					for(Integer freq:posts)
					{
						//count++;
						if(a==freq)
						{
							temp.add(a);
							//System.out.println("TempAnd "+temp);
							break;
						}
					}
				}
			}
		}
		return temp;
		
	}
	
	public static ArrayList<Integer> TermAtATimeOr(ArrayList<Integer> taatOR,String st,int flag) throws IOException{
		ArrayList<Integer> temp1=new ArrayList<Integer>();
		if(PostingsMap.get(st)!=null){
			LinkedList<Integer> posts;
			posts=PostingsMap.get(st);
			//System.out.println("TaatOR1: "+taatOR);
			//System.out.println("Postings: "+freqposts);	
			if(flag==0){
				for(Integer freq:posts)
				{
					taatOR.add(freq);
					//System.out.println("taatOR");
				}
			}
			else
			{
				int flag1=0;
				for(int freq:posts){
					count20++;
					for(Integer a :taatOR)
					{
						//count++;
						if(a==freq){
							flag1=1;
						}
					}
					if(flag1==0)
					{
						taatOR.add(freq);
						//System.out.println(taatOR);
					}
					flag1=0;
				}
			}
		}
		return taatOR;
	}
	
	public static ArrayList<Integer> DocAtATimeAnd(String[] input){
		ArrayList<Integer> daat=new ArrayList<Integer>();
		ArrayList<Integer> df=new ArrayList<Integer>();
		int ldf12=0,i,j;
		int count;
		for(i=0;i<input.length;i++){
			LinkedList<Integer> posts=PostingsMap.get(input[i]);
			df.add(posts.size());
		}
			for(i=0;i<df.size();i++){
				for(j=i+1;j<df.size();j++){
					if(df.get(j)<df.get(i))
						ldf12=j;
				}
			}
			//System.out.println("Least Doc Freq "+ldf);
			//System.out.println("DF: "+df);
			LinkedList<Integer> m1=new LinkedList<Integer>();
			LinkedList<Integer> m2=new LinkedList<Integer>();
			m1=PostingsMap.get(input[ldf12]);
			//System.out.println("m1 "+m1);
			for(int a: m1){
				count=0;
				//count30++;
				for(j=0;j<ldf12;j++){
					//count30++;
					if(count==j){
						count30++;
						m2=PostingsMap.get(input[j]);
						for(int b: m2){
							if(a==b)
								count++;
							if(b>a)
								break;
						}
						//System.out.println("Count: "+count);
					}
				}
				for(j=ldf12+1;j<input.length;j++){
					if(count==j-1){
						count30++;
						m2=PostingsMap.get(input[j]);
						for(int b:m2){
							if(a==b)
								count++;
							if(b>a)
								break;
						}
					}
				}
				if(count==(input.length -1))
					daat.add(a);
			}
			return(daat);
		
	}
	
	public static ArrayList<Integer> DocAtATimeOr(String[] input){
		ArrayList<Integer> daat2 =new ArrayList<Integer>();
		ArrayList<Integer> df=new ArrayList<Integer>();
		//int count=0;
		int gdf12=0,i,j;
		for(i=0;i<input.length;i++){
			LinkedList<Integer> posts=PostingsMap.get(input[i]);
			df.add(posts.size());
			}
		for(i=0;i<df.size();i++){
			for(j=i+1;j<df.size();j++){
				if(df.get(j)>df.get(i))
					gdf12=j;
			}
		}
		//System.out.println("Highest Docfrequency:"+gdf);
		//System.out.println("DF:"+df);
		LinkedList<Integer> l1=new LinkedList<Integer>();
		LinkedList<Integer> l2=new LinkedList<Integer>();
		l1=PostingsMap.get(input[gdf12]);
		//System.out.println("L1: "+l1);
		for(int a:l1){
			daat2.add(a);
			}
		for(int a=0;a<l1.size();a++){
			//count40++;
			for(j=0;j<gdf12;j++){
				l2=PostingsMap.get(input[j]);
				for(int b=0;b<l2.size();b++){
					if(a==b){
						if(l1.get(a)==l2.get(b))
							break;
						else
							daat2.add(l2.get(b));
					}
				}
			}
			for(j= gdf12+1;j<input.length;j++){
				l2=PostingsMap.get(input[j]);
				for(int b=0;b<l2.size();b++){
					if(a==b){
						if(l1.get(a)==l2.get(b))
							break;
						else
							daat2.add(l2.get(b));
					}
				}
			}
		}
		ArrayList<Integer> temparr=new ArrayList<Integer>();
		for(i=0;i<daat2.size();i++){
			count40++;
			int flag=0;
			for(j=i+1;j<daat2.size();j++){
				//count40++;
				if(daat2.get(i)==daat2.get(j))
					flag=1;
				}
			if(flag==0)
				temparr.add(daat2.get(i));
		}
		Collections.sort(temparr);
		return temparr;
	}
    /*public void getPostingList(Term t,LeafReader l) throws IOException{ //LeafReader
    	PostingsEnum postinglist= l.postings(t);
    	while(postinglist.nextDoc()!=postinglist.NO_MORE_DOCS){
    		DocList.add(postinglist.docID());
    	}
    		PostingsMap.put(t.bytes().utf8ToString(),DocList);
    		//System.out.println(PostingsMap);
    		DocList = new LinkedList<Integer>();
    		
    		
    	}*/
	public static void getPostingList(IndexReader rd) throws IOException{
		Fields f1=MultiFields.getFields(rd);
		Collection<String> col=MultiFields.getIndexedFields(rd);
		Iterator <String> sr=col.iterator();
		while(sr.hasNext()){
			String ss=sr.next();
			if(ss.charAt(0)=='t'){
				Terms tt=f1.terms(ss);
				TermsEnum t1=tt.iterator();
				PostingsEnum pr=null;
				while(t1.next()!=null){
					pr=t1.postings(pr,PostingsEnum.ALL);
					String tr=t1.term().utf8ToString();
					LinkedList<Integer> DocList=new LinkedList<Integer>();
					while(pr.nextDoc()!=PostingsEnum.NO_MORE_DOCS){
						DocList.add(pr.docID());
					}
					PostingsMap.put(tr,DocList);
				}
				
				
			}
		}
	}
    	
    	
    }
