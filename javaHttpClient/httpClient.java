
import java.io.*;
import java.net.*;
import java.util.*;


public class httpClient{
	// true でデバッグモード
	private final static boolean DEBUG = true;
	
	private static String surl;
	
	
	// ほぼデバッグ用
	public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException{
		Scanner scan = new Scanner(System.in);
		System.out.print("URL :");
		String str = scan.next();
		
		httpClient hc = new httpClient(str);
		hc.executeGet(null, null);
	}
	
	// URLを指定してあげる
	public httpClient(String url){
		surl = url;
	}
	
	// GETのパラメータを指定する
	public static String executeGet(HashMap<String, String> value, List<String> keys) throws UnsupportedEncodingException, MalformedURLException{
		String result = "";
		HttpURLConnection cnct = null;
		
		surl += "?";
		boolean first = true;
		for(String key : keys){
			if(!first){
				surl += "&";
			}
			surl += key + "=";
			surl += URLEncoder.encode(value.get(key), "UTF-8");
			first = false;
		}
		
		URL url = new URL(surl);
		try{

			cnct = (HttpURLConnection)url.openConnection();
			cnct.setRequestMethod("GET");
			
			if(cnct.getResponseCode() == HttpURLConnection.HTTP_OK){
				InputStreamReader isr = new InputStreamReader(cnct.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				
				String line;
				while((line = br.readLine())!=null){
					result += line;
				}
			}else{
				result += "ERROR CODE : " + cnct.getResponseCode();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(cnct!=null){
				cnct.disconnect();
			}
		}
		if(DEBUG){
			System.out.println(result);
		}
		
		return result;
	}
	
	public static String executePost() throws MalformedURLException{
		String result = "";
		HttpURLConnection cnct = null;
		
		URL url = new URL(surl);
		
		try{
			
			cnct = (HttpURLConnection)url.openConnection();
			cnct.setDoOutput(true);
			cnct.setRequestMethod("POST");
			
			if(cnct.getResponseCode() == HttpURLConnection.HTTP_OK){
				InputStreamReader isr = new InputStreamReader(cnct.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				
				String line;
				while((line = br.readLine()) != null){
					result += line;
				}
			}else{
				result += "ERROR CODE : " + cnct.getResponseCode();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(cnct!=null){
				cnct.disconnect();
			}
		}
		if(DEBUG){
			System.out.println(result);
		}
		
		return result;
	}
	
}