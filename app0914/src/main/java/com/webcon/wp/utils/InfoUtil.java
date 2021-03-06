package com.webcon.wp.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InfoUtil {
	private final static String kCpuInfoCurFreqFilePath =
			"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
    private final static String kCpuInfoMinFreqFilePath = 
    		"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
	private final static String kCpuInfoMaxFreqFilePath = 
			"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";

	/* 获取CPU最大频率（单位KHZ） */
	public static int getMaxCpuFreq(){
		int result = 0;
		FileReader fr = null;
		BufferedReader br = null;

		try{
			fr = new FileReader(kCpuInfoMaxFreqFilePath);
			br = new BufferedReader(fr);
			String text = br.readLine();
			result = Integer.parseInt(text.trim());
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(fr != null){
				try{
					fr.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(br != null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}	
			}
		}
		return result;
	}


	/* 获取CPU最小频率（单位KHZ） */
	public static int getMinCpuFreq(){
		int result = 0;
		FileReader fr = null;
		BufferedReader br = null;
		
		try{
			fr = new FileReader(kCpuInfoMinFreqFilePath);
			br = new BufferedReader(fr);
			String text = br.readLine();
			result = Integer.parseInt(text.trim());
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if (fr != null)
				try{
					fr.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			if (br != null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
    /* 获取CPU名字 */
	public static String getCpuName(){
		FileReader fr = null;
		BufferedReader br = null;
		try{
			fr = new FileReader("/proc/cpuinfo");
			br = new BufferedReader(fr);
			String text = br.readLine();
			String[] array = text.split(":\\s+", 2);
//			for(int i = 0; i < array.length; i++){
//			}
			return array[1];
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(fr != null){
				try{
					fr.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
			if(br != null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}
    
    /* 实时获取CPU当前频率（单位KHZ） */
    public static int getCurCpuFreq(){
        int result = 0;
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader(kCpuInfoCurFreqFilePath);
            br = new BufferedReader(fr);
            String text = br.readLine();
            result = Integer.parseInt(text.trim());
        }catch(FileNotFoundException e){
        	e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fr != null){
            	try{
                    fr.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
                
            if(br != null){
            	try{
                    br.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
