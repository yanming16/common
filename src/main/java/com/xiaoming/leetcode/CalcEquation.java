package com.xiaoming.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoming
 * @date 2020-01-14
 */
public class CalcEquation {

    //child parent
    Map<String,String> parents=new HashMap<>();
    //child mutiply of parent
    Map<String,Double> values=new HashMap<>();

    public void add(String x){
        if(!parents.containsKey(x)){
            parents.put(x,x);
            values.put(x,1.0);
        }
    }
    public void union(String parent,String child, double value){

        add(parent);
        add(child);
        String p1=find(parent);
        String p2=find(child);
        if(p1==p2){
            return;
        }else{
            parents.put(p2,p1);
            values.put(p2,value*(values.get(parent)/values.get(child)));
        }
    }

    public String find(String x){
        while(parents.get(x)!=x){
            x=parents.get(x);
        }
        return x;
    }


    public double cal(String x){
        // System.out.println("cal x"+x);
        double v=values.get(x);
        while(parents.get(x)!=x){
            x=parents.get(x);
            v*=values.get(x);
        }

        return v;
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        //union
        for(int i=0;i<equations.size();i++){
            //union parent child value
            union(equations.get(i).get(0),equations.get(i).get(1),values[i]);

        }

        //find

        double[] ans=new double[queries.size()];

        for(int i=0;i<queries.size();i++){

            String c1=queries.get(i).get(0);
            String c2=queries.get(i).get(1);
            if(!parents.containsKey(c1)||!parents.containsKey(c2)){
                ans[i]=-1;
                continue;
            }
            if(c1.equals(c2)){
                ans[i]=1;
                continue;
            }
            String p1=find(c1);
            String p2=find(c2);
            if(!p1.equals(p2)){
                ans[i]=-1;
                continue;
            }
            ans[i]=cal(c2)/cal(c1);

        }

        return ans;


    }

}
